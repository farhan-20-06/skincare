package com.skincare.services;

import com.skincare.models.*;
import com.skincare.utils.DataManager;
import com.skincare.utils.IdGenerator;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing streaks and routine completions
 * Demonstrates service layer pattern and business logic separation
 */
public class StreakService {
    private final DataManager dataManager;
    
    public StreakService() {
        this.dataManager = DataManager.getInstance();
    }
    
    /**
     * Complete a routine for today
     */
    public boolean completeRoutine(RoutineCompletion.RoutineType type) {
        User user = dataManager.getCurrentUser();
        LocalDate today = LocalDate.now();
        
        // Check if routine already completed today
        boolean alreadyCompleted = user.getRoutineCompletions().stream()
            .anyMatch(completion -> completion.getDate().equals(today) && 
                     completion.getType() == type && completion.isCompleted());
        
        if (alreadyCompleted) {
            System.out.println("⚠️ " + type.getDisplayName() + " routine already completed today!");
            return false;
        }
        
        // Create new completion record
        RoutineCompletion completion = new RoutineCompletion(
            IdGenerator.generateId("COMP"),
            today,
            type,
            true
        );
        
        user.addRoutineCompletion(completion);
        updateStreaks();
        dataManager.saveUserData();
        
        System.out.println("✅ " + type.getDisplayName() + " routine completed! " + type.getEmoji());
        return true;
    }
    
    /**
     * Undo routine completion for today
     */
    public boolean undoRoutineCompletion(RoutineCompletion.RoutineType type) {
        User user = dataManager.getCurrentUser();
        LocalDate today = LocalDate.now();
        
        // Find and remove today's completion
        List<RoutineCompletion> completions = user.getRoutineCompletions();
        boolean removed = completions.removeIf(completion -> 
            completion.getDate().equals(today) && 
            completion.getType() == type && 
            completion.isCompleted()
        );
        
        if (removed) {
            updateStreaks();
            dataManager.saveUserData();
            System.out.println("↩️ " + type.getDisplayName() + " routine completion undone.");
            return true;
        } else {
            System.out.println("⚠️ No " + type.getDisplayName() + " routine completion found for today!");
            return false;
        }
    }
    
    /**
     * Update streak calculations
     */
    public void updateStreaks() {
        User user = dataManager.getCurrentUser();
        Streak streaks = user.getStreaks();
        List<RoutineCompletion> completions = user.getRoutineCompletions().stream()
            .filter(RoutineCompletion::isCompleted)
            .sorted((a, b) -> b.getDate().compareTo(a.getDate())) // Sort by date descending
            .collect(Collectors.toList());
        
        // Calculate morning and night streaks
        int morningStreak = calculateStreakForType(completions, RoutineCompletion.RoutineType.MORNING);
        int nightStreak = calculateStreakForType(completions, RoutineCompletion.RoutineType.NIGHT);
        
        // Update streak object
        streaks.setMorningStreak(morningStreak);
        streaks.setNightStreak(nightStreak);
        streaks.updateOverallStreak();
        
        // Update last completed date
        if (!completions.isEmpty()) {
            streaks.setLastCompletedDate(completions.get(0).getDate());
        }
        
        user.setStreaks(streaks);
    }
    
    /**
     * Calculate streak for a specific routine type
     */
    private int calculateStreakForType(List<RoutineCompletion> completions, RoutineCompletion.RoutineType type) {
        int streak = 0;
        LocalDate currentDate = LocalDate.now();
        
        // Check consecutive days from today backwards
        for (int i = 0; i < 365; i++) { // Max 365 days check
            final LocalDate checkDate = currentDate.minusDays(i);
            
            boolean completedOnDate = completions.stream()
                .anyMatch(completion -> 
                    completion.getDate().equals(checkDate) && 
                    completion.getType() == type
                );
            
            if (completedOnDate) {
                streak++;
            } else if (i > 0) { // Don't break on first day (today) if not completed
                break;
            }
        }
        
        return streak;
    }
    
    /**
     * Get today's completion status
     */
    public CompletionStatus getTodayCompletionStatus() {
        User user = dataManager.getCurrentUser();
        LocalDate today = LocalDate.now();
        
        boolean morningCompleted = user.getRoutineCompletions().stream()
            .anyMatch(completion -> 
                completion.getDate().equals(today) && 
                completion.getType() == RoutineCompletion.RoutineType.MORNING && 
                completion.isCompleted()
            );
        
        boolean nightCompleted = user.getRoutineCompletions().stream()
            .anyMatch(completion -> 
                completion.getDate().equals(today) && 
                completion.getType() == RoutineCompletion.RoutineType.NIGHT && 
                completion.isCompleted()
            );
        
        return new CompletionStatus(morningCompleted, nightCompleted);
    }
    
    /**
     * Get completion status for a specific date
     */
    public CompletionStatus getCompletionStatusForDate(LocalDate date) {
        User user = dataManager.getCurrentUser();
        
        boolean morningCompleted = user.getRoutineCompletions().stream()
            .anyMatch(completion -> 
                completion.getDate().equals(date) && 
                completion.getType() == RoutineCompletion.RoutineType.MORNING && 
                completion.isCompleted()
            );
        
        boolean nightCompleted = user.getRoutineCompletions().stream()
            .anyMatch(completion -> 
                completion.getDate().equals(date) && 
                completion.getType() == RoutineCompletion.RoutineType.NIGHT && 
                completion.isCompleted()
            );
        
        return new CompletionStatus(morningCompleted, nightCompleted);
    }
    
    /**
     * Get streak statistics
     */
    public StreakStats getStreakStats() {
        User user = dataManager.getCurrentUser();
        Streak streaks = user.getStreaks();
        List<RoutineCompletion> completions = user.getRoutineCompletions();
        
        int totalCompletions = completions.size();
        long daysSinceFirstCompletion = completions.stream()
            .map(RoutineCompletion::getDate)
            .min(LocalDate::compareTo)
            .map(firstDate -> java.time.temporal.ChronoUnit.DAYS.between(firstDate, LocalDate.now()) + 1)
            .orElse(0L);
        
        double completionRate = daysSinceFirstCompletion > 0 ? 
            (double) totalCompletions / (daysSinceFirstCompletion * 2) * 100 : 0.0; // *2 for morning and night
        
        return new StreakStats(
            streaks.getCurrent(),
            streaks.getLongest(),
            streaks.getMorningStreak(),
            streaks.getNightStreak(),
            streaks.getLongestMorningStreak(),
            streaks.getLongestNightStreak(),
            totalCompletions,
            completionRate,
            streaks.isStreakActive()
        );
    }
    
    /**
     * Reset all streaks (for testing or fresh start)
     */
    public void resetAllStreaks() {
        User user = dataManager.getCurrentUser();
        user.getStreaks().resetStreak();
        user.getRoutineCompletions().clear();
        dataManager.saveUserData();
        System.out.println("🔄 All streaks have been reset!");
    }
    
    // Helper classes
    public static class CompletionStatus {
        private final boolean morningCompleted;
        private final boolean nightCompleted;
        
        public CompletionStatus(boolean morningCompleted, boolean nightCompleted) {
            this.morningCompleted = morningCompleted;
            this.nightCompleted = nightCompleted;
        }
        
        public boolean isMorningCompleted() { return morningCompleted; }
        public boolean isNightCompleted() { return nightCompleted; }
        public boolean isBothCompleted() { return morningCompleted && nightCompleted; }
        public boolean isAnyCompleted() { return morningCompleted || nightCompleted; }
        
        @Override
        public String toString() {
            return "CompletionStatus{morning=" + morningCompleted + ", night=" + nightCompleted + "}";
        }
    }
    
    public static class StreakStats {
        private final int currentStreak;
        private final int longestStreak;
        private final int morningStreak;
        private final int nightStreak;
        private final int longestMorningStreak;
        private final int longestNightStreak;
        private final int totalCompletions;
        private final double completionRate;
        private final boolean isActive;
        
        public StreakStats(int currentStreak, int longestStreak, int morningStreak, int nightStreak,
                          int longestMorningStreak, int longestNightStreak, int totalCompletions,
                          double completionRate, boolean isActive) {
            this.currentStreak = currentStreak;
            this.longestStreak = longestStreak;
            this.morningStreak = morningStreak;
            this.nightStreak = nightStreak;
            this.longestMorningStreak = longestMorningStreak;
            this.longestNightStreak = longestNightStreak;
            this.totalCompletions = totalCompletions;
            this.completionRate = completionRate;
            this.isActive = isActive;
        }
        
        // Getters
        public int getCurrentStreak() { return currentStreak; }
        public int getLongestStreak() { return longestStreak; }
        public int getMorningStreak() { return morningStreak; }
        public int getNightStreak() { return nightStreak; }
        public int getLongestMorningStreak() { return longestMorningStreak; }
        public int getLongestNightStreak() { return longestNightStreak; }
        public int getTotalCompletions() { return totalCompletions; }
        public double getCompletionRate() { return completionRate; }
        public boolean isActive() { return isActive; }
        
        @Override
        public String toString() {
            return "StreakStats{" +
                    "current=" + currentStreak +
                    ", longest=" + longestStreak +
                    ", morning=" + morningStreak +
                    ", night=" + nightStreak +
                    ", totalCompletions=" + totalCompletions +
                    ", completionRate=" + String.format("%.1f", completionRate) + "%" +
                    ", active=" + isActive +
                    '}';
        }
    }
}
