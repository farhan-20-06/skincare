package com.skincare.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a routine completion record
 * Demonstrates enums and timestamp handling
 */
public class RoutineCompletion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum RoutineType {
        MORNING("Morning", "🌅"),
        NIGHT("Night", "🌙");
        
        private final String displayName;
        private final String emoji;
        
        RoutineType(String displayName, String emoji) {
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
    private LocalDate date;
    private RoutineType type;
    private boolean completed;
    private LocalDateTime completedAt;
    
    // Default constructor
    public RoutineCompletion() {
        this.date = LocalDate.now();
        this.completed = false;
    }
    
    // Parameterized constructor
    public RoutineCompletion(String id, LocalDate date, RoutineType type, boolean completed) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.completed = completed;
        if (completed) {
            this.completedAt = LocalDateTime.now();
        }
    }
    
    // Constructor with string date
    public RoutineCompletion(String id, String dateString, RoutineType type, boolean completed) {
        this.id = id;
        this.date = LocalDate.parse(dateString);
        this.type = type;
        this.completed = completed;
        if (completed) {
            this.completedAt = LocalDateTime.now();
        }
    }
    
    // Getters and Setters
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
    
    public RoutineType getType() {
        return type;
    }
    
    public void setType(RoutineType type) {
        this.type = type;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed && completedAt == null) {
            this.completedAt = LocalDateTime.now();
        } else if (!completed) {
            this.completedAt = null;
        }
    }
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
    public String getCompletedAtString() {
        return completedAt != null ? completedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "";
    }
    
    // Business logic methods
    public boolean isToday() {
        return date != null && date.equals(LocalDate.now());
    }
    
    public boolean isYesterday() {
        return date != null && date.equals(LocalDate.now().minusDays(1));
    }
    
    public boolean isThisWeek() {
        if (date == null) return false;
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - 1);
        return !date.isBefore(startOfWeek) && !date.isAfter(now);
    }
    
    public String getTimeOfCompletion() {
        if (completedAt == null) return "Not completed";
        return completedAt.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
    public String getRelativeDateDescription() {
        if (isToday()) return "Today";
        if (isYesterday()) return "Yesterday";
        if (isThisWeek()) return date.getDayOfWeek().toString();
        return date.format(DateTimeFormatter.ofPattern("MMM dd"));
    }
    
    public boolean isValidCompletion() {
        return id != null && !id.trim().isEmpty() &&
               date != null &&
               type != null;
    }
    
    public void markCompleted() {
        setCompleted(true);
    }
    
    public void markUncompleted() {
        setCompleted(false);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoutineCompletion that = (RoutineCompletion) obj;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "RoutineCompletion{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", type=" + type.getDisplayName() + " " + type.getEmoji() +
                ", completed=" + completed +
                ", completedAt=" + (completedAt != null ? getTimeOfCompletion() : "N/A") +
                ", relativeDateDescription='" + getRelativeDateDescription() + '\'' +
                '}';
    }
}
