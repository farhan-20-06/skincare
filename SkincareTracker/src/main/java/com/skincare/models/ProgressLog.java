package com.skincare.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a daily progress log entry
 * Demonstrates data validation and date handling
 */
public class ProgressLog implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private LocalDate date;
    private int acneLevel; // 0-10 scale
    private int glowLevel; // 0-10 scale
    private String notes;
    
    // Default constructor
    public ProgressLog() {
        this.date = LocalDate.now();
    }
    
    // Parameterized constructor
    public ProgressLog(String id, LocalDate date, int acneLevel, int glowLevel, String notes) {
        this.id = id;
        this.date = date;
        setAcneLevel(acneLevel); // Use setter for validation
        setGlowLevel(glowLevel); // Use setter for validation
        this.notes = notes;
    }
    
    // Constructor with string date
    public ProgressLog(String id, String dateString, int acneLevel, int glowLevel, String notes) {
        this.id = id;
        this.date = LocalDate.parse(dateString);
        setAcneLevel(acneLevel);
        setGlowLevel(glowLevel);
        this.notes = notes;
    }
    
    // Getters and Setters with validation
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getDateString() {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
    }
    
    public int getAcneLevel() {
        return acneLevel;
    }
    
    public void setAcneLevel(int acneLevel) {
        if (acneLevel < 0 || acneLevel > 10) {
            throw new IllegalArgumentException("Acne level must be between 0 and 10");
        }
        this.acneLevel = acneLevel;
    }
    
    public int getGlowLevel() {
        return glowLevel;
    }
    
    public void setGlowLevel(int glowLevel) {
        if (glowLevel < 0 || glowLevel > 10) {
            throw new IllegalArgumentException("Glow level must be between 0 and 10");
        }
        this.glowLevel = glowLevel;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Business logic methods
    public boolean isValidEntry() {
        return id != null && !id.trim().isEmpty() &&
               date != null &&
               notes != null && !notes.trim().isEmpty();
    }
    
    public String getAcneLevelDescription() {
        if (acneLevel <= 2) return "Clear";
        if (acneLevel <= 4) return "Mild";
        if (acneLevel <= 6) return "Moderate";
        if (acneLevel <= 8) return "Significant";
        return "Severe";
    }
    
    public String getGlowLevelDescription() {
        if (glowLevel <= 2) return "Dull";
        if (glowLevel <= 4) return "Fair";
        if (glowLevel <= 6) return "Good";
        if (glowLevel <= 8) return "Radiant";
        return "Glowing";
    }
    
    public double getOverallSkinScore() {
        // Higher glow is better, lower acne is better
        // Convert acne to positive scale (10 - acne) and average with glow
        return ((10 - acneLevel) + glowLevel) / 2.0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProgressLog that = (ProgressLog) obj;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ProgressLog{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", acneLevel=" + acneLevel + " (" + getAcneLevelDescription() + ")" +
                ", glowLevel=" + glowLevel + " (" + getGlowLevelDescription() + ")" +
                ", notes='" + notes + '\'' +
                ", overallScore=" + String.format("%.1f", getOverallSkinScore()) +
                '}';
    }
}
