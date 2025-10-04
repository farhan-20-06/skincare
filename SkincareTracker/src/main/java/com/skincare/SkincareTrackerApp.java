package com.skincare;

import com.skincare.models.*;
import com.skincare.services.*;
import com.skincare.ui.ConsoleUI;
import com.skincare.utils.DataManager;
import com.skincare.utils.DateUtils;
import com.skincare.utils.IdGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Main application class - Entry point for the Skincare Tracker
 * Demonstrates main application flow and menu-driven interface
 */
public class SkincareTrackerApp {
    private final DataManager dataManager;
    private final StreakService streakService;
    private final GoalService goalService;
    private boolean running;
    
    public SkincareTrackerApp() {
        this.dataManager = DataManager.getInstance();
        this.streakService = new StreakService();
        this.goalService = new GoalService();
        this.running = true;
        
        // Enable auto-save
        dataManager.enableAutoSave();
    }
    
    /**
     * Main application entry point
     */
    public static void main(String[] args) {
        SkincareTrackerApp app = new SkincareTrackerApp();
        app.run();
    }
    
    /**
     * Main application loop
     */
    public void run() {
        ConsoleUI.clearScreen();
        ConsoleUI.printBanner();
        
        ConsoleUI.printInfo("Welcome to Skincare Tracker!");
        ConsoleUI.printInfo("Your personal skincare journey companion.");
        
        User user = dataManager.getCurrentUser();
        if (user.getName() != null && !user.getName().equals("Demo User")) {
            ConsoleUI.printSuccess("Welcome back, " + user.getName() + "!");
        } else {
            setupUserProfile();
        }
        
        while (running) {
            showMainMenu();
        }
        
        ConsoleUI.printSuccess("Thank you for using Skincare Tracker! 🌟");
        ConsoleUI.close();
    }
    
    /**
     * Display main menu and handle selection
     */
    private void showMainMenu() {
        String[] options = {
            "🏠 Dashboard - View Overview",
            "🔥 Streaks - Track Daily Routines", 
            "🎯 Goals - Manage Skin Goals",
            "📊 Progress - Log Daily Progress",
            "🌅 Morning Routine - Manage Products",
            "🌙 Night Routine - Manage Products",
            "⏰ Reminders - Settings",
            "👤 Profile - User Settings",
            "💾 Data Management",
            "❌ Exit Application"
        };
        
        int choice = ConsoleUI.displayMenu("SKINCARE TRACKER - MAIN MENU", options);
        
        switch (choice) {
            case 1 -> showDashboard();
            case 2 -> showStreaksMenu();
            case 3 -> showGoalsMenu();
            case 4 -> showProgressMenu();
            case 5 -> showRoutineMenu(true);  // Morning routine
            case 6 -> showRoutineMenu(false); // Night routine
            case 7 -> showRemindersMenu();
            case 8 -> showProfileMenu();
            case 9 -> showDataManagementMenu();
            case 10, 0 -> {
                if (ConsoleUI.getConfirmation("Are you sure you want to exit?")) {
                    running = false;
                }
            }
            default -> ConsoleUI.printError("Invalid option. Please try again.");
        }
    }
    
    /**
     * Show dashboard with overview
     */
    private void showDashboard() {
        ConsoleUI.printHeader("DASHBOARD");
        
        User user = dataManager.getCurrentUser();
        
        // User info
        ConsoleUI.printInfo("User: " + user.getName() + " | Skin Type: " + user.getSkinType());
        System.out.println();
        
        // Today's status
        StreakService.CompletionStatus todayStatus = streakService.getTodayCompletionStatus();
        ConsoleUI.printColored("📅 TODAY'S STATUS:", ConsoleUI.BOLD + ConsoleUI.CYAN);
        System.out.println("🌅 Morning Routine: " + 
            (todayStatus.isMorningCompleted() ? ConsoleUI.GREEN + "✅ Completed" : ConsoleUI.RED + "❌ Not Done") + ConsoleUI.RESET);
        System.out.println("🌙 Night Routine: " + 
            (todayStatus.isNightCompleted() ? ConsoleUI.GREEN + "✅ Completed" : ConsoleUI.RED + "❌ Not Done") + ConsoleUI.RESET);
        System.out.println();
        
        // Streak info
        Streak streaks = user.getStreaks();
        ConsoleUI.printColored("🔥 STREAK INFORMATION:", ConsoleUI.BOLD + ConsoleUI.CYAN);
        System.out.println("Current Streak: " + ConsoleUI.YELLOW + streaks.getCurrent() + " days " + 
                          streaks.getStreakEmoji() + ConsoleUI.RESET);
        System.out.println("Longest Streak: " + ConsoleUI.GREEN + streaks.getLongest() + " days" + ConsoleUI.RESET);
        System.out.println("Morning Streak: " + streaks.getMorningStreak() + " days");
        System.out.println("Night Streak: " + streaks.getNightStreak() + " days");
        System.out.println();
        
        // Goals summary
        GoalService.GoalStats goalStats = goalService.getGoalStats();
        ConsoleUI.printColored("🎯 GOALS SUMMARY:", ConsoleUI.BOLD + ConsoleUI.CYAN);
        System.out.println("Total Goals: " + goalStats.getTotalGoals());
        System.out.println("Active Goals: " + ConsoleUI.YELLOW + goalStats.getActiveGoals() + ConsoleUI.RESET);
        System.out.println("Completed Goals: " + ConsoleUI.GREEN + goalStats.getCompletedGoals() + ConsoleUI.RESET);
        if (goalStats.getOverdueGoals() > 0) {
            System.out.println("Overdue Goals: " + ConsoleUI.RED + goalStats.getOverdueGoals() + ConsoleUI.RESET);
        }
        if (goalStats.getActiveGoals() > 0) {
            ConsoleUI.printProgressBar("Average Progress", goalStats.getAverageProgress());
        }
        System.out.println();
        
        // Recent progress
        List<ProgressLog> recentLogs = user.getProgressLogs();
        if (!recentLogs.isEmpty()) {
            ConsoleUI.printColored("📈 RECENT PROGRESS:", ConsoleUI.BOLD + ConsoleUI.CYAN);
            ProgressLog latest = recentLogs.get(0);
            System.out.println("Latest Entry: " + DateUtils.formatForDisplay(latest.getDate()));
            System.out.println("Acne Level: " + latest.getAcneLevel() + "/10 (" + latest.getAcneLevelDescription() + ")");
            System.out.println("Glow Level: " + latest.getGlowLevel() + "/10 (" + latest.getGlowLevelDescription() + ")");
            System.out.println("Overall Score: " + String.format("%.1f/10", latest.getOverallSkinScore()));
        }
        
        ConsoleUI.waitForEnter();
    }
    
    /**
     * Show streaks menu
     */
    private void showStreaksMenu() {
        while (true) {
            String[] options = {
                "✅ Complete Morning Routine",
                "✅ Complete Night Routine", 
                "↩️ Undo Morning Completion",
                "↩️ Undo Night Completion",
                "📊 View Streak Statistics",
                "📅 View Weekly Progress",
                "🔄 Reset All Streaks"
            };
            
            int choice = ConsoleUI.displayMenu("STREAKS MANAGEMENT", options);
            
            switch (choice) {
                case 1 -> streakService.completeRoutine(RoutineCompletion.RoutineType.MORNING);
                case 2 -> streakService.completeRoutine(RoutineCompletion.RoutineType.NIGHT);
                case 3 -> streakService.undoRoutineCompletion(RoutineCompletion.RoutineType.MORNING);
                case 4 -> streakService.undoRoutineCompletion(RoutineCompletion.RoutineType.NIGHT);
                case 5 -> showStreakStatistics();
                case 6 -> showWeeklyProgress();
                case 7 -> {
                    if (ConsoleUI.getConfirmation("Are you sure you want to reset all streaks? This cannot be undone!")) {
                        streakService.resetAllStreaks();
                    }
                }
                case 0 -> { return; }
                default -> ConsoleUI.printError("Invalid option. Please try again.");
            }
            
            if (choice != 5 && choice != 6) {
                ConsoleUI.waitForEnter();
            }
        }
    }
    
    /**
     * Show streak statistics
     */
    private void showStreakStatistics() {
        ConsoleUI.printHeader("STREAK STATISTICS");
        
        StreakService.StreakStats stats = streakService.getStreakStats();
        Streak streaks = dataManager.getCurrentUser().getStreaks();
        
        ConsoleUI.printTableHeader("Metric", "Current", "Best");
        ConsoleUI.printTableRow("Overall Streak", stats.getCurrentStreak() + " days", stats.getLongestStreak() + " days");
        ConsoleUI.printTableRow("Morning Streak", stats.getMorningStreak() + " days", stats.getLongestMorningStreak() + " days");
        ConsoleUI.printTableRow("Night Streak", stats.getNightStreak() + " days", stats.getLongestNightStreak() + " days");
        ConsoleUI.printSeparator();
        
        System.out.println();
        System.out.println("📊 Total Completions: " + stats.getTotalCompletions());
        System.out.println("📈 Completion Rate: " + String.format("%.1f%%", stats.getCompletionRate()));
        System.out.println("🎯 Streak Status: " + (stats.isActive() ? ConsoleUI.GREEN + "Active" : ConsoleUI.RED + "Inactive") + ConsoleUI.RESET);
        System.out.println("💫 Motivational Message: " + streaks.getMotivationalMessage());
        
        ConsoleUI.waitForEnter();
    }
    
    /**
     * Show weekly progress
     */
    private void showWeeklyProgress() {
        ConsoleUI.printHeader("WEEKLY PROGRESS");
        
        List<LocalDate> weekDates = DateUtils.getCurrentWeekDates();
        
        ConsoleUI.printTableHeader("Date", "Day", "Morning", "Night", "Status");
        
        for (LocalDate date : weekDates) {
            StreakService.CompletionStatus status = streakService.getCompletionStatusForDate(date);
            String dayName = date.getDayOfWeek().toString().substring(0, 3);
            String morningStatus = status.isMorningCompleted() ? "✅" : "❌";
            String nightStatus = status.isNightCompleted() ? "✅" : "❌";
            String overallStatus = status.isBothCompleted() ? "🔥 Complete" : 
                                 status.isAnyCompleted() ? "⚡ Partial" : "○ None";
            
            String dateStr = DateUtils.isToday(date) ? "TODAY" : DateUtils.formatForDisplay(date);
            ConsoleUI.printTableRow(dateStr, dayName, morningStatus, nightStatus, overallStatus);
        }
        
        ConsoleUI.waitForEnter();
    }
    
    /**
     * Show goals menu
     */
    private void showGoalsMenu() {
        while (true) {
            String[] options = {
                "➕ Create New Goal",
                "📊 Update Goal Progress",
                "📋 View All Goals",
                "🎯 View Active Goals",
                "🏆 View Completed Goals",
                "⏰ View Overdue Goals",
                "✏️ Edit Goal Details",
                "✅ Mark Goal as Completed",
                "🗑️ Delete Goal"
            };
            
            int choice = ConsoleUI.displayMenu("GOALS MANAGEMENT", options);
            
            switch (choice) {
                case 1 -> createNewGoal();
                case 2 -> updateGoalProgress();
                case 3 -> viewAllGoals();
                case 4 -> viewActiveGoals();
                case 5 -> viewCompletedGoals();
                case 6 -> viewOverdueGoals();
                case 7 -> editGoalDetails();
                case 8 -> markGoalCompleted();
                case 9 -> deleteGoal();
                case 0 -> { return; }
                default -> ConsoleUI.printError("Invalid option. Please try again.");
            }
        }
    }
    
    /**
     * Create a new goal
     */
    private void createNewGoal() {
        ConsoleUI.printHeader("CREATE NEW GOAL");
        
        String title = ConsoleUI.getStringInput("Goal Title", true);
        String description = ConsoleUI.getStringInput("Goal Description", true);
        
        // Category selection
        SkinGoal.Category[] categories = SkinGoal.Category.values();
        String[] categoryOptions = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            categoryOptions[i] = categories[i].getEmoji() + " " + categories[i].getDisplayName();
        }
        
        int categoryChoice = ConsoleUI.displayMenu("SELECT CATEGORY", categoryOptions);
        if (categoryChoice == 0) return;
        
        SkinGoal.Category category = categories[categoryChoice - 1];
        
        double currentValue = ConsoleUI.getDoubleInput("Current Value", 0, 100);
        double targetValue = ConsoleUI.getDoubleInput("Target Value", 0, 100);
        String unit = ConsoleUI.getStringInput("Unit (e.g., level, %, score)", true);
        
        // Target date
        LocalDate targetDate = null;
        while (targetDate == null) {
            String dateInput = ConsoleUI.getStringInput("Target Date (YYYY-MM-DD)", true);
            try {
                targetDate = LocalDate.parse(dateInput);
                if (targetDate.isBefore(LocalDate.now())) {
                    ConsoleUI.printError("Target date must be in the future!");
                    targetDate = null;
                }
            } catch (DateTimeParseException e) {
                ConsoleUI.printError("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        
        boolean success = goalService.createGoal(title, description, targetValue, currentValue, unit, category, targetDate);
        if (success) {
            ConsoleUI.waitForEnter("Goal created successfully! Press Enter to continue...");
        }
    }
    
    /**
     * Update goal progress
     */
    private void updateGoalProgress() {
        List<SkinGoal> activeGoals = goalService.getActiveGoals();
        if (activeGoals.isEmpty()) {
            ConsoleUI.printWarning("No active goals found!");
            ConsoleUI.waitForEnter();
            return;
        }
        
        ConsoleUI.printHeader("UPDATE GOAL PROGRESS");
        
        // Display active goals
        for (int i = 0; i < activeGoals.size(); i++) {
            SkinGoal goal = activeGoals.get(i);
            System.out.println((i + 1) + ". " + goal.getTitle() + 
                             " (Current: " + goal.getCurrentValue() + " " + goal.getUnit() + 
                             ", Target: " + goal.getTargetValue() + " " + goal.getUnit() + ")");
        }
        
        int choice = ConsoleUI.getIntInput("Select goal to update", 1, activeGoals.size());
        SkinGoal selectedGoal = activeGoals.get(choice - 1);
        
        System.out.println("Current progress: " + selectedGoal.getCurrentValue() + " " + selectedGoal.getUnit());
        double newValue = ConsoleUI.getDoubleInput("Enter new value", 0, 1000);
        
        goalService.updateGoalProgress(selectedGoal.getId(), newValue);
        ConsoleUI.waitForEnter();
    }
    
    /**
     * View all goals
     */
    private void viewAllGoals() {
        List<SkinGoal> allGoals = goalService.getAllGoals();
        displayGoalsList("ALL GOALS", allGoals);
    }
    
    /**
     * View active goals
     */
    private void viewActiveGoals() {
        List<SkinGoal> activeGoals = goalService.getActiveGoals();
        displayGoalsList("ACTIVE GOALS", activeGoals);
    }
    
    /**
     * View completed goals
     */
    private void viewCompletedGoals() {
        List<SkinGoal> completedGoals = goalService.getCompletedGoals();
        displayGoalsList("COMPLETED GOALS", completedGoals);
    }
    
    /**
     * View overdue goals
     */
    private void viewOverdueGoals() {
        List<SkinGoal> overdueGoals = goalService.getOverdueGoals();
        displayGoalsList("OVERDUE GOALS", overdueGoals);
    }
    
    /**
     * Display a list of goals
     */
    private void displayGoalsList(String title, List<SkinGoal> goals) {
        ConsoleUI.printHeader(title);
        
        if (goals.isEmpty()) {
            ConsoleUI.printInfo("No goals found in this category.");
        } else {
            for (SkinGoal goal : goals) {
                System.out.println();
                ConsoleUI.printColored(goal.getCategory().getEmoji() + " " + goal.getTitle(), ConsoleUI.BOLD + ConsoleUI.CYAN);
                System.out.println("Description: " + goal.getDescription());
                System.out.println("Category: " + goal.getCategory().getDisplayName());
                ConsoleUI.printProgressBar("Progress", goal.getProgressPercentage());
                System.out.println("Current: " + goal.getCurrentValue() + " " + goal.getUnit() + 
                                 " | Target: " + goal.getTargetValue() + " " + goal.getUnit());
                System.out.println("Target Date: " + DateUtils.formatForDisplay(goal.getTargetDate()) + 
                                 " (" + goal.getDaysRemaining() + " days remaining)");
                System.out.println("Status: " + goal.getStatusDescription());
                ConsoleUI.printSeparator();
            }
        }
        
        ConsoleUI.waitForEnter();
    }
    
    // Additional methods for other menu options...
    private void editGoalDetails() {
        // Implementation for editing goal details
        ConsoleUI.printInfo("Edit goal details feature - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void markGoalCompleted() {
        // Implementation for marking goal as completed
        ConsoleUI.printInfo("Mark goal completed feature - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void deleteGoal() {
        // Implementation for deleting goals
        ConsoleUI.printInfo("Delete goal feature - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void showProgressMenu() {
        ConsoleUI.printInfo("Progress logging feature - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void showRoutineMenu(boolean isMorning) {
        String routineType = isMorning ? "Morning" : "Night";
        ConsoleUI.printInfo(routineType + " routine management - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void showRemindersMenu() {
        ConsoleUI.printInfo("Reminders management - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void showProfileMenu() {
        ConsoleUI.printInfo("Profile management - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    private void showDataManagementMenu() {
        ConsoleUI.printInfo("Data management - Implementation pending");
        ConsoleUI.waitForEnter();
    }
    
    /**
     * Setup user profile for first-time users
     */
    private void setupUserProfile() {
        ConsoleUI.printHeader("WELCOME TO SKINCARE TRACKER!");
        ConsoleUI.printInfo("Let's set up your profile to get started.");
        
        String name = ConsoleUI.getStringInput("Enter your name", true);
        
        String[] skinTypes = {"Normal", "Dry", "Oily", "Combination", "Sensitive"};
        int skinTypeChoice = ConsoleUI.displayMenu("SELECT YOUR SKIN TYPE", skinTypes);
        
        if (skinTypeChoice > 0) {
            String skinType = skinTypes[skinTypeChoice - 1];
            
            User user = dataManager.getCurrentUser();
            user.setName(name);
            user.setSkinType(skinType);
            dataManager.saveUserData();
            
            ConsoleUI.printSuccess("Profile created successfully!");
            ConsoleUI.printInfo("You can now start tracking your skincare journey!");
        }
        
        ConsoleUI.waitForEnter();
    }
}
