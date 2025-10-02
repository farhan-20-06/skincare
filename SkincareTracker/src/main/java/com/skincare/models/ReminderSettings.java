package com.skincare.models;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents reminder settings for routines
 * Demonstrates time handling and validation
 */
public class ReminderSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private boolean morningEnabled;
    private boolean nightEnabled;
    private LocalTime morningTime;
    private LocalTime nightTime;
    
    // Default constructor with sensible defaults
    public ReminderSettings() {
        this.morningEnabled = true;
        this.nightEnabled = true;
        this.morningTime = LocalTime.of(8, 0); // 8:00 AM
        this.nightTime = LocalTime.of(22, 0);  // 10:00 PM
    }
    
    // Parameterized constructor
    public ReminderSettings(boolean morningEnabled, boolean nightEnabled, 
                          LocalTime morningTime, LocalTime nightTime) {
        this.morningEnabled = morningEnabled;
        this.nightEnabled = nightEnabled;
        this.morningTime = morningTime;
        this.nightTime = nightTime;
    }
    
    // Constructor with string times
    public ReminderSettings(boolean morningEnabled, boolean nightEnabled, 
                          String morningTimeString, String nightTimeString) {
        this.morningEnabled = morningEnabled;
        this.nightEnabled = nightEnabled;
        setMorningTimeFromString(morningTimeString);
        setNightTimeFromString(nightTimeString);
    }
    
    // Getters and Setters
    public boolean isMorningEnabled() {
        return morningEnabled;
    }
    
    public void setMorningEnabled(boolean morningEnabled) {
        this.morningEnabled = morningEnabled;
    }
    
    public boolean isNightEnabled() {
        return nightEnabled;
    }
    
    public void setNightEnabled(boolean nightEnabled) {
        this.nightEnabled = nightEnabled;
    }
    
    public LocalTime getMorningTime() {
        return morningTime;
    }
    
    public void setMorningTime(LocalTime morningTime) {
        this.morningTime = morningTime;
    }
    
    public LocalTime getNightTime() {
        return nightTime;
    }
    
    public void setNightTime(LocalTime nightTime) {
        this.nightTime = nightTime;
    }
    
    // String representations for time
    public String getMorningTimeString() {
        return morningTime != null ? morningTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "08:00";
    }
    
    public String getNightTimeString() {
        return nightTime != null ? nightTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "22:00";
    }
    
    public void setMorningTimeFromString(String timeString) {
        try {
            this.morningTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            // Default to 8:00 AM if parsing fails
            this.morningTime = LocalTime.of(8, 0);
        }
    }
    
    public void setNightTimeFromString(String timeString) {
        try {
            this.nightTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            // Default to 10:00 PM if parsing fails
            this.nightTime = LocalTime.of(22, 0);
        }
    }
    
    // Business logic methods
    public boolean isAnyReminderEnabled() {
        return morningEnabled || nightEnabled;
    }
    
    public boolean areBothRemindersEnabled() {
        return morningEnabled && nightEnabled;
    }
    
    public String getMorningTimeDisplay() {
        return morningTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }
    
    public String getNightTimeDisplay() {
        return nightTime.format(DateTimeFormatter.ofPattern("h:mm a"));
    }
    
    public boolean isValidMorningTime() {
        return morningTime != null && 
               morningTime.isAfter(LocalTime.of(5, 0)) && 
               morningTime.isBefore(LocalTime.of(12, 0));
    }
    
    public boolean isValidNightTime() {
        return nightTime != null && 
               (nightTime.isAfter(LocalTime.of(18, 0)) || 
                nightTime.isBefore(LocalTime.of(2, 0)));
    }
    
    public boolean hasValidSettings() {
        return (morningEnabled ? isValidMorningTime() : true) &&
               (nightEnabled ? isValidNightTime() : true);
    }
    
    public void enableMorningReminder(LocalTime time) {
        this.morningEnabled = true;
        this.morningTime = time;
    }
    
    public void enableNightReminder(LocalTime time) {
        this.nightEnabled = true;
        this.nightTime = time;
    }
    
    public void disableMorningReminder() {
        this.morningEnabled = false;
    }
    
    public void disableNightReminder() {
        this.nightEnabled = false;
    }
    
    public void disableAllReminders() {
        this.morningEnabled = false;
        this.nightEnabled = false;
    }
    
    public void enableAllReminders() {
        this.morningEnabled = true;
        this.nightEnabled = true;
    }
    
    public String getSettingsSummary() {
        StringBuilder summary = new StringBuilder();
        
        if (morningEnabled) {
            summary.append("🌅 Morning: ").append(getMorningTimeDisplay());
        } else {
            summary.append("🌅 Morning: Disabled");
        }
        
        summary.append(" | ");
        
        if (nightEnabled) {
            summary.append("🌙 Night: ").append(getNightTimeDisplay());
        } else {
            summary.append("🌙 Night: Disabled");
        }
        
        return summary.toString();
    }
    
    @Override
    public String toString() {
        return "ReminderSettings{" +
                "morningEnabled=" + morningEnabled +
                ", nightEnabled=" + nightEnabled +
                ", morningTime=" + getMorningTimeDisplay() +
                ", nightTime=" + getNightTimeDisplay() +
                ", summary='" + getSettingsSummary() + '\'' +
                '}';
    }
}
