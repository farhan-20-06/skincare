package com.skincare.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the skincare tracking system
 * Demonstrates encapsulation and data modeling
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String skinType;
    private List<Product> morningRoutine;
    private List<Product> nightRoutine;
    private List<ProgressLog> progressLogs;
    private List<SkinGoal> skinGoals;
    private List<RoutineCompletion> routineCompletions;
    private Streak streaks;
    private ReminderSettings reminderSettings;
    
    // Default constructor
    public User() {
        this.morningRoutine = new ArrayList<>();
        this.nightRoutine = new ArrayList<>();
        this.progressLogs = new ArrayList<>();
        this.skinGoals = new ArrayList<>();
        this.routineCompletions = new ArrayList<>();
        this.streaks = new Streak();
        this.reminderSettings = new ReminderSettings();
    }
    
    // Parameterized constructor
    public User(String name, String skinType) {
        this();
        this.name = name;
        this.skinType = skinType;
    }
    
    // Getters and Setters (Encapsulation)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSkinType() {
        return skinType;
    }
    
    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }
    
    public List<Product> getMorningRoutine() {
        return new ArrayList<>(morningRoutine); // Defensive copy
    }
    
    public void setMorningRoutine(List<Product> morningRoutine) {
        this.morningRoutine = new ArrayList<>(morningRoutine);
    }
    
    public List<Product> getNightRoutine() {
        return new ArrayList<>(nightRoutine); // Defensive copy
    }
    
    public void setNightRoutine(List<Product> nightRoutine) {
        this.nightRoutine = new ArrayList<>(nightRoutine);
    }
    
    public List<ProgressLog> getProgressLogs() {
        return new ArrayList<>(progressLogs);
    }
    
    public void setProgressLogs(List<ProgressLog> progressLogs) {
        this.progressLogs = new ArrayList<>(progressLogs);
    }
    
    public List<SkinGoal> getSkinGoals() {
        return new ArrayList<>(skinGoals);
    }
    
    public void setSkinGoals(List<SkinGoal> skinGoals) {
        this.skinGoals = new ArrayList<>(skinGoals);
    }
    
    public List<RoutineCompletion> getRoutineCompletions() {
        return new ArrayList<>(routineCompletions);
    }
    
    public void setRoutineCompletions(List<RoutineCompletion> routineCompletions) {
        this.routineCompletions = new ArrayList<>(routineCompletions);
    }
    
    public Streak getStreaks() {
        return streaks;
    }
    
    public void setStreaks(Streak streaks) {
        this.streaks = streaks;
    }
    
    public ReminderSettings getReminderSettings() {
        return reminderSettings;
    }
    
    public void setReminderSettings(ReminderSettings reminderSettings) {
        this.reminderSettings = reminderSettings;
    }
    
    // Business logic methods
    public void addMorningProduct(Product product) {
        if (product != null && !morningRoutine.contains(product)) {
            morningRoutine.add(product);
        }
    }
    
    public void addNightProduct(Product product) {
        if (product != null && !nightRoutine.contains(product)) {
            nightRoutine.add(product);
        }
    }
    
    public boolean removeMorningProduct(String productId) {
        return morningRoutine.removeIf(product -> product.getId().equals(productId));
    }
    
    public boolean removeNightProduct(String productId) {
        return nightRoutine.removeIf(product -> product.getId().equals(productId));
    }
    
    public void addProgressLog(ProgressLog log) {
        if (log != null) {
            progressLogs.add(0, log); // Add to beginning for newest first
        }
    }
    
    public boolean removeProgressLog(String logId) {
        return progressLogs.removeIf(log -> log.getId().equals(logId));
    }
    
    public void addSkinGoal(SkinGoal goal) {
        if (goal != null) {
            skinGoals.add(goal);
        }
    }
    
    public boolean removeSkinGoal(String goalId) {
        return skinGoals.removeIf(goal -> goal.getId().equals(goalId));
    }
    
    public void addRoutineCompletion(RoutineCompletion completion) {
        if (completion != null) {
            routineCompletions.add(completion);
        }
    }
    
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", skinType='" + skinType + '\'' +
                ", morningRoutine=" + morningRoutine.size() + " products" +
                ", nightRoutine=" + nightRoutine.size() + " products" +
                ", progressLogs=" + progressLogs.size() + " logs" +
                ", skinGoals=" + skinGoals.size() + " goals" +
                '}';
    }
}
