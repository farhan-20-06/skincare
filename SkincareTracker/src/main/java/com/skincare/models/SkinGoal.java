package com.skincare.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Represents a skin improvement goal
 * Demonstrates enums, date calculations, and business logic
 */
public class SkinGoal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Category {
        ACNE("Acne Reduction", "🎯"),
        HYDRATION("Hydration", "💧"),
        BRIGHTNESS("Brightness", "✨"),
        TEXTURE("Texture", "🤲"),
        CUSTOM("Custom", "📋");
        
        private final String displayName;
        private final String emoji;
        
        Category(String displayName, String emoji) {
            this.displayName = displayName;
            this.emoji = emoji;
        }
        
        public String getDisplayName() {
            return displayName;
        }
        
        public String getEmoji() {
            return emoji;
        }
    }
    
    private String id;
    private String title;
    private String description;
    private double targetValue;
    private double currentValue;
    private String unit;
    private Category category;
    private LocalDate createdDate;
    private LocalDate targetDate;
    private boolean isCompleted;
    
    // Default constructor
    public SkinGoal() {
        this.createdDate = LocalDate.now();
        this.targetDate = LocalDate.now().plusDays(30); // Default 30 days
        this.category = Category.CUSTOM;
        this.isCompleted = false;
    }
    
    // Parameterized constructor
    public SkinGoal(String id, String title, String description, double targetValue, 
                   double currentValue, String unit, Category category, LocalDate targetDate) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        this.unit = unit;
        this.category = category;
        this.targetDate = targetDate;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getTargetValue() {
        return targetValue;
    }
    
    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }
    
    public double getCurrentValue() {
        return currentValue;
    }
    
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
        updateCompletionStatus();
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDate getTargetDate() {
        return targetDate;
    }
    
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
    
    public String getTargetDateString() {
        return targetDate != null ? targetDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    
    // Business logic methods
    private void updateCompletionStatus() {
        if (category == Category.ACNE) {
            // For acne, lower values are better
            isCompleted = currentValue <= targetValue;
        } else {
            // For other categories, higher values are better
            isCompleted = currentValue >= targetValue;
        }
    }
    
    public double getProgressPercentage() {
        if (category == Category.ACNE) {
            // For acne, calculate improvement from starting point
            double improvement = Math.max(0, currentValue - targetValue);
            double maxImprovement = Math.max(1, currentValue);
            return Math.min(100, ((maxImprovement - improvement) / maxImprovement) * 100);
        } else {
            // For other categories, standard percentage
            return Math.min(100, (currentValue / targetValue) * 100);
        }
    }
    
    public long getDaysRemaining() {
        return ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
    }
    
    public long getDaysSinceCreated() {
        return ChronoUnit.DAYS.between(createdDate, LocalDate.now());
    }
    
    public boolean isOverdue() {
        return LocalDate.now().isAfter(targetDate) && !isCompleted;
    }
    
    public String getStatusDescription() {
        if (isCompleted) {
            return "✅ Completed";
        } else if (isOverdue()) {
            return "⏰ Overdue";
        } else {
            long daysLeft = getDaysRemaining();
            if (daysLeft <= 0) {
                return "📅 Due today";
            } else if (daysLeft <= 7) {
                return "🔔 Due in " + daysLeft + " days";
            } else {
                return "📊 In progress";
            }
        }
    }
    
    public boolean isValidGoal() {
        return id != null && !id.trim().isEmpty() &&
               title != null && !title.trim().isEmpty() &&
               description != null && !description.trim().isEmpty() &&
               targetValue > 0 &&
               unit != null && !unit.trim().isEmpty() &&
               category != null &&
               targetDate != null;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SkinGoal skinGoal = (SkinGoal) obj;
        return Objects.equals(id, skinGoal.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "SkinGoal{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category.getDisplayName() +
                ", progress=" + String.format("%.1f", getProgressPercentage()) + "%" +
                ", currentValue=" + currentValue +
                ", targetValue=" + targetValue +
                ", unit='" + unit + '\'' +
                ", status='" + getStatusDescription() + '\'' +
                ", daysRemaining=" + getDaysRemaining() +
                '}';
    }
}
