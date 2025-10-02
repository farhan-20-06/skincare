package com.skincare.ui;

import java.util.Scanner;

/**
 * Console-based user interface utility class
 * Demonstrates input validation and user interaction patterns
 */
public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);
    
    // ANSI color codes for better console output
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // Background colors
    public static final String BG_BLACK = "\u001B[40m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_PURPLE = "\u001B[45m";
    public static final String BG_CYAN = "\u001B[46m";
    public static final String BG_WHITE = "\u001B[47m";
    
    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    /**
     * Print a colored message
     */
    public static void printColored(String message, String color) {
        System.out.println(color + message + RESET);
    }
    
    /**
     * Print success message
     */
    public static void printSuccess(String message) {
        printColored("✅ " + message, GREEN);
    }
    
    /**
     * Print error message
     */
    public static void printError(String message) {
        printColored("❌ " + message, RED);
    }
    
    /**
     * Print warning message
     */
    public static void printWarning(String message) {
        printColored("⚠️ " + message, YELLOW);
    }
    
    /**
     * Print info message
     */
    public static void printInfo(String message) {
        printColored("ℹ️ " + message, BLUE);
    }
    
    /**
     * Print header with decoration
     */
    public static void printHeader(String title) {
        String border = "═".repeat(Math.max(50, title.length() + 10));
        System.out.println();
        printColored(border, CYAN);
        printColored("    " + title.toUpperCase(), BOLD + CYAN);
        printColored(border, CYAN);
        System.out.println();
    }
    
    /**
     * Print section separator
     */
    public static void printSeparator() {
        printColored("─".repeat(50), BLUE);
    }
    
    /**
     * Get string input with prompt
     */
    public static String getStringInput(String prompt) {
        System.out.print(CYAN + prompt + RESET + ": ");
        return scanner.nextLine().trim();
    }
    
    /**
     * Get string input with validation
     */
    public static String getStringInput(String prompt, boolean required) {
        String input;
        do {
            input = getStringInput(prompt);
            if (required && input.isEmpty()) {
                printError("This field is required. Please enter a value.");
            }
        } while (required && input.isEmpty());
        return input;
    }
    
    /**
     * Get integer input with prompt
     */
    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(CYAN + prompt + RESET + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                printError("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Get integer input with range validation
     */
    public static int getIntInput(String prompt, int min, int max) {
        int value;
        do {
            value = getIntInput(prompt + " (" + min + "-" + max + ")");
            if (value < min || value > max) {
                printError("Please enter a number between " + min + " and " + max + ".");
            }
        } while (value < min || value > max);
        return value;
    }
    
    /**
     * Get double input with prompt
     */
    public static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(CYAN + prompt + RESET + ": ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                printError("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Get double input with range validation
     */
    public static double getDoubleInput(String prompt, double min, double max) {
        double value;
        do {
            value = getDoubleInput(prompt + " (" + min + "-" + max + ")");
            if (value < min || value > max) {
                printError("Please enter a number between " + min + " and " + max + ".");
            }
        } while (value < min || value > max);
        return value;
    }
    
    /**
     * Get yes/no confirmation
     */
    public static boolean getConfirmation(String prompt) {
        while (true) {
            String input = getStringInput(prompt + " (y/n)").toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                printError("Please enter 'y' for yes or 'n' for no.");
            }
        }
    }
    
    /**
     * Display menu options and get selection
     */
    public static int displayMenu(String title, String[] options) {
        printHeader(title);
        
        for (int i = 0; i < options.length; i++) {
            System.out.println(YELLOW + (i + 1) + ". " + RESET + options[i]);
        }
        
        System.out.println(YELLOW + "0. " + RESET + "Back/Exit");
        System.out.println();
        
        return getIntInput("Select an option", 0, options.length);
    }
    
    /**
     * Wait for user to press Enter
     */
    public static void waitForEnter() {
        waitForEnter("Press Enter to continue...");
    }
    
    /**
     * Wait for user to press Enter with custom message
     */
    public static void waitForEnter(String message) {
        System.out.print(YELLOW + message + RESET);
        scanner.nextLine();
    }
    
    /**
     * Clear console (works on most terminals)
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
            }
        } catch (Exception e) {
            // If clearing fails, just print some newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    /**
     * Print a progress bar
     */
    public static void printProgressBar(String label, double percentage) {
        int barLength = 30;
        int filledLength = (int) (barLength * percentage / 100);
        
        StringBuilder bar = new StringBuilder();
        bar.append("[");
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        
        bar.append("] ");
        bar.append(String.format("%.1f%%", percentage));
        
        System.out.println(label + ": " + GREEN + bar.toString() + RESET);
    }
    
    /**
     * Print a table header
     */
    public static void printTableHeader(String... headers) {
        printSeparator();
        StringBuilder header = new StringBuilder();
        for (int i = 0; i < headers.length; i++) {
            header.append(String.format("%-20s", headers[i]));
            if (i < headers.length - 1) {
                header.append(" | ");
            }
        }
        printColored(header.toString(), BOLD + CYAN);
        printSeparator();
    }
    
    /**
     * Print a table row
     */
    public static void printTableRow(String... values) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            row.append(String.format("%-20s", values[i]));
            if (i < values.length - 1) {
                row.append(" | ");
            }
        }
        System.out.println(row.toString());
    }
    
    /**
     * Print application banner
     */
    public static void printBanner() {
        String[] banner = {
            "  ███████╗██╗  ██╗██╗███╗   ██╗ ██████╗ █████╗ ██████╗ ███████╗",
            "  ██╔════╝██║ ██╔╝██║████╗  ██║██╔════╝██╔══██╗██╔══██╗██╔════╝",
            "  ███████╗█████╔╝ ██║██╔██╗ ██║██║     ███████║██████╔╝█████╗  ",
            "  ╚════██║██╔═██╗ ██║██║╚██╗██║██║     ██╔══██║██╔══██╗██╔══╝  ",
            "  ███████║██║  ██╗██║██║ ╚████║╚██████╗██║  ██║██║  ██║███████╗",
            "  ╚══════╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝",
            "",
            "            🌟 TRACKER - Your Personal Skincare Journey 🌟"
        };
        
        System.out.println();
        for (String line : banner) {
            printColored(line, PURPLE);
        }
        System.out.println();
        printColored("                    Track • Progress • Achieve", CYAN);
        System.out.println();
    }
    
    /**
     * Close the scanner (call this when application exits)
     */
    public static void close() {
        scanner.close();
    }
}
