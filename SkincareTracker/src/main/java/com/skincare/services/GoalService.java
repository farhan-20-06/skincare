package com.skincare.services;

import com.skincare.models.SkinGoal;
import com.skincare.models.User;
import com.skincare.utils.DataManager;
import com.skincare.utils.IdGenerator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing skin goals
 * Demonstrates service layer pattern and goal management logic
 */
public class GoalService {
    private final DataManager dataManager;
    
    public GoalService() {
        this.dataManager = DataManager.getInstance();
    }
    
    /**
     * Create a new skin goal
     */
    public boolean createGoal(String title, String description, double targetValue, 
                             double currentValue, String unit, SkinGoal.Category category, 
                             LocalDate targetDate) {
        
        // Validation
        if (title == null || title.trim().isEmpty()) {
            System.out.println("❌ Goal title cannot be empty!");
            return false;
        }
        
        if (description == null || description.trim().isEmpty()) {
            System.out.println("❌ Goal description cannot be empty!");
            return false;
        }
        
        if (targetValue <= 0) {
            System.out.println("❌ Target value must be positive!");
            return false;
        }
        
        if (unit == null || unit.trim().isEmpty()) {
            System.out.println("❌ Unit cannot be empty!");
            return false;
        }
        
        if (targetDate == null || targetDate.isBefore(LocalDate.now())) {
            System.out.println("❌ Target date must be in the future!");
            return false;
        }
        
        // Create new goal
        SkinGoal goal = new SkinGoal(
            IdGenerator.generateId("GOAL"),
            title.trim(),
            description.trim(),
            targetValue,
            currentValue,
            unit.trim(),
            category != null ? category : SkinGoal.Category.CUSTOM,
            targetDate
        );
        
        User user = dataManager.getCurrentUser();
        user.addSkinGoal(goal);
        dataManager.saveUserData();
        
        System.out.println("✅ Goal '" + title + "' created successfully! " + category.getEmoji());
        return true;
    }
    
    /**
     * Update goal progress
     */
    public boolean updateGoalProgress(String goalId, double newValue) {
        User user = dataManager.getCurrentUser();
        Optional<SkinGoal> goalOpt = user.getSkinGoals().stream()
            .filter(goal -> goal.getId().equals(goalId))
            .findFirst();
        
        if (goalOpt.isEmpty()) {
            System.out.println("❌ Goal not found!");
            return false;
        }
        
        SkinGoal goal = goalOpt.get();
        double oldValue = goal.getCurrentValue();
        goal.setCurrentValue(newValue);
        
        dataManager.saveUserData();
        
        // Check if goal was just completed
        if (!goal.isCompleted() && isGoalCompleted(goal)) {
            goal.setCompleted(true);
            System.out.println("🎉 Congratulations! Goal '" + goal.getTitle() + "' completed!");
        }
        
        System.out.println("📊 Progress updated: " + oldValue + " → " + newValue + " " + goal.getUnit());
        return true;
    }
    
    /**
     * Delete a goal
     */
    public boolean deleteGoal(String goalId) {
        User user = dataManager.getCurrentUser();
        boolean removed = user.removeSkinGoal(goalId);
        
        if (removed) {
            dataManager.saveUserData();
            System.out.println("🗑️ Goal deleted successfully!");
            return true;
        } else {
            System.out.println("❌ Goal not found!");
            return false;
        }
    }
    
    /**
     * Get all goals
     */
    public List<SkinGoal> getAllGoals() {
        return dataManager.getCurrentUser().getSkinGoals();
    }
    
    /**
     * Get active goals (not completed)
     */
    public List<SkinGoal> getActiveGoals() {
        return dataManager.getCurrentUser().getSkinGoals().stream()
            .filter(goal -> !goal.isCompleted())
            .collect(Collectors.toList());
    }
    
    /**
     * Get completed goals
     */
    public List<SkinGoal> getCompletedGoals() {
        return dataManager.getCurrentUser().getSkinGoals().stream()
            .filter(SkinGoal::isCompleted)
            .collect(Collectors.toList());
    }
    
    /**
     * Get overdue goals
     */
    public List<SkinGoal> getOverdueGoals() {
        return dataManager.getCurrentUser().getSkinGoals().stream()
            .filter(SkinGoal::isOverdue)
            .collect(Collectors.toList());
    }
    
    /**
     * Get goals by category
     */
    public List<SkinGoal> getGoalsByCategory(SkinGoal.Category category) {
        return dataManager.getCurrentUser().getSkinGoals().stream()
            .filter(goal -> goal.getCategory() == category)
            .collect(Collectors.toList());
    }
    
    /**
     * Get goal by ID
     */
    public Optional<SkinGoal> getGoalById(String goalId) {
        return dataManager.getCurrentUser().getSkinGoals().stream()
            .filter(goal -> goal.getId().equals(goalId))
            .findFirst();
    }
    
    /**
     * Check if a goal is completed based on its category and values
     */
    private boolean isGoalCompleted(SkinGoal goal) {
        if (goal.getCategory() == SkinGoal.Category.ACNE) {
            // For acne, lower values are better
            return goal.getCurrentValue() <= goal.getTargetValue();
        } else {
            // For other categories, higher values are better
            return goal.getCurrentValue() >= goal.getTargetValue();
        }
    }
    
    /**
     * Get goal statistics
     */
    public GoalStats getGoalStats() {
        List<SkinGoal> allGoals = getAllGoals();
        List<SkinGoal> activeGoals = getActiveGoals();
        List<SkinGoal> completedGoals = getCompletedGoals();
        List<SkinGoal> overdueGoals = getOverdueGoals();
        
        double averageProgress = activeGoals.stream()
            .mapToDouble(SkinGoal::getProgressPercentage)
            .average()
            .orElse(0.0);
        
        return new GoalStats(
            allGoals.size(),
            activeGoals.size(),
            completedGoals.size(),
            overdueGoals.size(),
            averageProgress
        );
    }
    
    /**
     * Update goal details (except progress)
     */
    public boolean updateGoalDetails(String goalId, String title, String description, 
                                   double targetValue, String unit, LocalDate targetDate) {
        Optional<SkinGoal> goalOpt = getGoalById(goalId);
        
        if (goalOpt.isEmpty()) {
            System.out.println("❌ Goal not found!");
            return false;
        }
        
        SkinGoal goal = goalOpt.get();
        
        // Validation
        if (title != null && !title.trim().isEmpty()) {
            goal.setTitle(title.trim());
        }
        
        if (description != null && !description.trim().isEmpty()) {
            goal.setDescription(description.trim());
        }
        
        if (targetValue > 0) {
            goal.setTargetValue(targetValue);
        }
        
        if (unit != null && !unit.trim().isEmpty()) {
            goal.setUnit(unit.trim());
        }
        
        if (targetDate != null && !targetDate.isBefore(LocalDate.now())) {
            goal.setTargetDate(targetDate);
        }
        
        dataManager.saveUserData();
        System.out.println("✅ Goal details updated successfully!");
        return true;
    }
    
    /**
     * Mark goal as completed manually
     */
    public boolean markGoalCompleted(String goalId) {
        Optional<SkinGoal> goalOpt = getGoalById(goalId);
        
        if (goalOpt.isEmpty()) {
            System.out.println("❌ Goal not found!");
            return false;
        }
        
        SkinGoal goal = goalOpt.get();
        goal.setCompleted(true);
        dataManager.saveUserData();
        
        System.out.println("🎉 Goal '" + goal.getTitle() + "' marked as completed!");
        return true;
    }
    
    /**
     * Reopen a completed goal
     */
    public boolean reopenGoal(String goalId) {
        Optional<SkinGoal> goalOpt = getGoalById(goalId);
        
        if (goalOpt.isEmpty()) {
            System.out.println("❌ Goal not found!");
            return false;
        }
        
        SkinGoal goal = goalOpt.get();
        goal.setCompleted(false);
        dataManager.saveUserData();
        
        System.out.println("🔄 Goal '" + goal.getTitle() + "' reopened!");
        return true;
    }
    
    // Helper class for goal statistics
    public static class GoalStats {
        private final int totalGoals;
        private final int activeGoals;
        private final int completedGoals;
        private final int overdueGoals;
        private final double averageProgress;
        
        public GoalStats(int totalGoals, int activeGoals, int completedGoals, 
                        int overdueGoals, double averageProgress) {
            this.totalGoals = totalGoals;
            this.activeGoals = activeGoals;
            this.completedGoals = completedGoals;
            this.overdueGoals = overdueGoals;
            this.averageProgress = averageProgress;
        }
        
        // Getters
        public int getTotalGoals() { return totalGoals; }
        public int getActiveGoals() { return activeGoals; }
        public int getCompletedGoals() { return completedGoals; }
        public int getOverdueGoals() { return overdueGoals; }
        public double getAverageProgress() { return averageProgress; }
        
        public double getCompletionRate() {
            return totalGoals > 0 ? (double) completedGoals / totalGoals * 100 : 0.0;
        }
        
        @Override
        public String toString() {
            return "GoalStats{" +
                    "total=" + totalGoals +
                    ", active=" + activeGoals +
                    ", completed=" + completedGoals +
                    ", overdue=" + overdueGoals +
                    ", avgProgress=" + String.format("%.1f", averageProgress) + "%" +
                    ", completionRate=" + String.format("%.1f", getCompletionRate()) + "%" +
                    '}';
        }
    }
}
