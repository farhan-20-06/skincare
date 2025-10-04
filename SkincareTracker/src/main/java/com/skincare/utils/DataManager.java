package com.skincare.utils;

import com.skincare.models.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles data persistence using file I/O
 * Demonstrates file handling, serialization, and singleton pattern
 */
public class DataManager {
    private static final String DATA_FILE = "skincare_data.ser";
    private static final String BACKUP_FILE = "skincare_data_backup.ser";
    private static DataManager instance;
    private User currentUser;
    
    // Singleton pattern
    private DataManager() {
        loadUserData();
    }
    
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
    
    /**
     * Load user data from file or create default data
     */
    public void loadUserData() {
        try {
            File dataFile = new File(DATA_FILE);
            if (dataFile.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
                    currentUser = (User) ois.readObject();
                    System.out.println("✅ User data loaded successfully!");
                }
            } else {
                createDefaultUser();
                System.out.println("📁 No existing data found. Created new user profile.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ Error loading user data: " + e.getMessage());
            System.out.println("Creating new user profile...");
            createDefaultUser();
        }
    }
    
    /**
     * Save user data to file with backup
     */
    public boolean saveUserData() {
        try {
            // Create backup first
            createBackup();
            
            // Save current data
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                oos.writeObject(currentUser);
                System.out.println("💾 Data saved successfully!");
                return true;
            }
        } catch (IOException e) {
            System.err.println("❌ Error saving user data: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Create backup of existing data
     */
    private void createBackup() {
        File dataFile = new File(DATA_FILE);
        File backupFile = new File(BACKUP_FILE);
        
        if (dataFile.exists()) {
            try {
                copyFile(dataFile, backupFile);
                System.out.println("🔄 Backup created successfully!");
            } catch (IOException e) {
                System.err.println("⚠️ Warning: Could not create backup: " + e.getMessage());
            }
        }
    }
    
    /**
     * Restore from backup
     */
    public boolean restoreFromBackup() {
        File backupFile = new File(BACKUP_FILE);
        if (backupFile.exists()) {
            try {
                copyFile(backupFile, new File(DATA_FILE));
                loadUserData();
                System.out.println("🔄 Data restored from backup successfully!");
                return true;
            } catch (IOException e) {
                System.err.println("❌ Error restoring from backup: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("❌ No backup file found!");
            return false;
        }
    }
    
    /**
     * Copy file utility method
     */
    private void copyFile(File source, File destination) throws IOException {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
    
    /**
     * Create default user with sample data
     */
    private void createDefaultUser() {
        currentUser = new User("Demo User", "Normal");
        
        // Add sample morning routine
        currentUser.addMorningProduct(new Product("1", "Gentle Cleanser", "CeraVe", "Cleanser"));
        currentUser.addMorningProduct(new Product("2", "Vitamin C Serum", "The Ordinary", "Serum"));
        currentUser.addMorningProduct(new Product("3", "Moisturizer", "Neutrogena", "Moisturizer"));
        currentUser.addMorningProduct(new Product("4", "Sunscreen SPF 30", "EltaMD", "Sunscreen"));
        
        // Add sample night routine
        currentUser.addNightProduct(new Product("5", "Oil Cleanser", "DHC", "Cleanser"));
        currentUser.addNightProduct(new Product("6", "Retinol Serum", "The Ordinary", "Treatment"));
        currentUser.addNightProduct(new Product("7", "Night Cream", "Olay", "Moisturizer"));
        
        // Add sample progress log
        ProgressLog sampleLog = new ProgressLog(
            "1", 
            LocalDate.now().toString(), 
            3, 
            7, 
            "Skin feels smooth today, slight redness around nose"
        );
        currentUser.addProgressLog(sampleLog);
        
        // Add sample goal
        SkinGoal sampleGoal = new SkinGoal(
            "1",
            "Reduce Acne",
            "Achieve clearer skin by reducing acne breakouts",
            2.0,
            3.0,
            "severity level",
            SkinGoal.Category.ACNE,
            LocalDate.now().plusDays(30)
        );
        currentUser.addSkinGoal(sampleGoal);
        
        saveUserData();
    }
    
    /**
     * Export data to text file for backup/sharing
     */
    public boolean exportToTextFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("=== SKINCARE TRACKER DATA EXPORT ===");
            writer.println("Export Date: " + LocalDate.now());
            writer.println();
            
            // User profile
            writer.println("USER PROFILE:");
            writer.println("Name: " + currentUser.getName());
            writer.println("Skin Type: " + currentUser.getSkinType());
            writer.println();
            
            // Morning routine
            writer.println("MORNING ROUTINE:");
            for (Product product : currentUser.getMorningRoutine()) {
                writer.println("- " + product.getDisplayName());
            }
            writer.println();
            
            // Night routine
            writer.println("NIGHT ROUTINE:");
            for (Product product : currentUser.getNightRoutine()) {
                writer.println("- " + product.getDisplayName());
            }
            writer.println();
            
            // Progress logs
            writer.println("PROGRESS LOGS:");
            for (ProgressLog log : currentUser.getProgressLogs()) {
                writer.println(log.getDateString() + " - Acne: " + log.getAcneLevel() + 
                             ", Glow: " + log.getGlowLevel() + " - " + log.getNotes());
            }
            writer.println();
            
            // Goals
            writer.println("SKIN GOALS:");
            for (SkinGoal goal : currentUser.getSkinGoals()) {
                writer.println("- " + goal.getTitle() + " (" + goal.getCategory().getDisplayName() + ")");
                writer.println("  Progress: " + String.format("%.1f", goal.getProgressPercentage()) + "%");
                writer.println("  " + goal.getDescription());
            }
            writer.println();
            
            // Streaks
            writer.println("STREAKS:");
            Streak streaks = currentUser.getStreaks();
            writer.println("Current Streak: " + streaks.getCurrent() + " days");
            writer.println("Longest Streak: " + streaks.getLongest() + " days");
            writer.println("Morning Streak: " + streaks.getMorningStreak() + " days");
            writer.println("Night Streak: " + streaks.getNightStreak() + " days");
            
            System.out.println("📄 Data exported to " + filename + " successfully!");
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error exporting data: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Clear all data and reset to defaults
     */
    public void clearAllData() {
        File dataFile = new File(DATA_FILE);
        if (dataFile.exists()) {
            dataFile.delete();
        }
        createDefaultUser();
        System.out.println("🗑️ All data cleared and reset to defaults!");
    }
    
    /**
     * Get file size information
     */
    public String getDataFileInfo() {
        File dataFile = new File(DATA_FILE);
        if (dataFile.exists()) {
            long sizeInBytes = dataFile.length();
            String size = sizeInBytes < 1024 ? sizeInBytes + " bytes" : 
                         sizeInBytes < 1024 * 1024 ? (sizeInBytes / 1024) + " KB" :
                         (sizeInBytes / (1024 * 1024)) + " MB";
            return "Data file: " + DATA_FILE + " (" + size + ")";
        } else {
            return "No data file exists";
        }
    }
    
    // Getters
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    
    /**
     * Auto-save functionality
     */
    public void enableAutoSave() {
        // In a real application, this could set up a timer for periodic saves
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("💾 Auto-saving data before exit...");
            saveUserData();
        }));
    }
}
