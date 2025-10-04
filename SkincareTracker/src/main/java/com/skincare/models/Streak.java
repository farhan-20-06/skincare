package com.skincare.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents streak tracking data
 * Demonstrates data aggregation and streak calculations
 */
public class Streak implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int current;
    private int longest;
    private LocalDate lastCompletedDate;
    private int morningStreak;
    private int nightStreak;
    private int longestMorningStreak;
    private int longestNightStreak;
    
    // Default constructor
    public Streak() {
        this.current = 0;
        this.longest = 0;
        this.morningStreak = 0;
        this.nightStreak = 0;
        this.longestMorningStreak = 0;
        this.longestNightStreak = 0;
        this.lastCompletedDate = null;
    }
    
    // Parameterized constructor
    public Streak(int current, int longest, LocalDate lastCompletedDate,
                 int morningStreak, int nightStreak, int longestMorningStreak, int longestNightStreak) {
        this.current = current;
        this.longest = longest;
        this.lastCompletedDate = lastCompletedDate;
        this.morningStreak = morningStreak;
        this.nightStreak = nightStreak;
        this.longestMorningStreak = longestMorningStreak;
        this.longestNightStreak = longestNightStreak;
    }
    
    // Getters and Setters
    public int getCurrent() {
        return current;
    }
    
    public void setCurrent(int current) {
        this.current = Math.max(0, current);
        updateLongestIfNeeded();
    }
    
    public int getLongest() {
        return longest;
    }
    
    public void setLongest(int longest) {
        this.longest = Math.max(0, longest);
    }
    
    public LocalDate getLastCompletedDate() {
        return lastCompletedDate;
    }
    
    public void setLastCompletedDate(LocalDate lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }
    
    public String getLastCompletedDateString() {
        return lastCompletedDate != null ? lastCompletedDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
    }
    
    public int getMorningStreak() {
        return morningStreak;
    }
    
    public void setMorningStreak(int morningStreak) {
        this.morningStreak = Math.max(0, morningStreak);
        updateLongestMorningIfNeeded();
    }
    
    public int getNightStreak() {
        return nightStreak;
    }
    
    public void setNightStreak(int nightStreak) {
        this.nightStreak = Math.max(0, nightStreak);
        updateLongestNightIfNeeded();
    }
    
    public int getLongestMorningStreak() {
        return longestMorningStreak;
    }
    
    public void setLongestMorningStreak(int longestMorningStreak) {
        this.longestMorningStreak = Math.max(0, longestMorningStreak);
    }
    
    public int getLongestNightStreak() {
        return longestNightStreak;
    }
    
    public void setLongestNightStreak(int longestNightStreak) {
        this.longestNightStreak = Math.max(0, longestNightStreak);
    }
    
    // Business logic methods
    private void updateLongestIfNeeded() {
        if (current > longest) {
            longest = current;
        }
    }
    
    private void updateLongestMorningIfNeeded() {
        if (morningStreak > longestMorningStreak) {
            longestMorningStreak = morningStreak;
        }
    }
    
    private void updateLongestNightIfNeeded() {
        if (nightStreak > longestNightStreak) {
            longestNightStreak = nightStreak;
        }
    }
    
    public void updateOverallStreak() {
        // Overall streak is the minimum of morning and night streaks
        // (both routines needed for a complete day)
        current = Math.min(morningStreak, nightStreak);
        updateLongestIfNeeded();
    }
    
    public String getStreakEmoji() {
        if (current == 0) return "🌱";
        if (current < 7) return "🔥";
        if (current < 30) return "⚡";
        if (current < 100) return "💎";
        return "👑";
    }
    
    public String getMotivationalMessage() {
        if (current == 0) return "Start your skincare journey today!";
        if (current == 1) return "Great start! Keep it going!";
        if (current < 7) return "You're building a habit! 🎯";
        if (current < 30) return "Amazing consistency! 🌟";
        if (current < 100) return "You're a skincare champion! 💪";
        return "Legendary streak! You're unstoppable! 🏆";
    }
    
    public boolean isStreakActive() {
        if (lastCompletedDate == null) return false;
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        
        // Streak is active if last completion was today or yesterday
        return lastCompletedDate.equals(today) || lastCompletedDate.equals(yesterday);
    }
    
    public int getDaysSinceLastCompletion() {
        if (lastCompletedDate == null) return -1;
        return (int) java.time.temporal.ChronoUnit.DAYS.between(lastCompletedDate, LocalDate.now());
    }
    
    public void resetStreak() {
        current = 0;
        morningStreak = 0;
        nightStreak = 0;
        lastCompletedDate = null;
    }
    
    public double getConsistencyScore() {
        // Calculate consistency based on current streaks vs longest streaks
        if (longestMorningStreak == 0 && longestNightStreak == 0) return 0.0;
        
        double morningConsistency = longestMorningStreak > 0 ? (double) morningStreak / longestMorningStreak : 0.0;
        double nightConsistency = longestNightStreak > 0 ? (double) nightStreak / longestNightStreak : 0.0;
        
        return (morningConsistency + nightConsistency) / 2.0 * 100;
    }
    
    @Override
    public String toString() {
        return "Streak{" +
                "current=" + current +
                ", longest=" + longest +
                ", morningStreak=" + morningStreak +
                ", nightStreak=" + nightStreak +
                ", longestMorningStreak=" + longestMorningStreak +
                ", longestNightStreak=" + longestNightStreak +
                ", lastCompletedDate=" + lastCompletedDate +
                ", emoji='" + getStreakEmoji() + '\'' +
                ", active=" + isStreakActive() +
                ", consistencyScore=" + String.format("%.1f", getConsistencyScore()) + "%" +
                '}';
    }
}
