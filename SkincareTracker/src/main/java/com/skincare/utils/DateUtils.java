package com.skincare.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for date operations
 * Demonstrates utility methods and date calculations
 */
public class DateUtils {
    
    // Common date formatters
    public static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter DISPLAY_DATE = DateTimeFormatter.ofPattern("MMM dd, yyyy");
    public static final DateTimeFormatter SHORT_DATE = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter READABLE_DATE = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
    
    // Private constructor to prevent instantiation
    private DateUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Get today's date as string
     */
    public static String getTodayString() {
        return LocalDate.now().format(ISO_DATE);
    }
    
    /**
     * Get yesterday's date as string
     */
    public static String getYesterdayString() {
        return LocalDate.now().minusDays(1).format(ISO_DATE);
    }
    
    /**
     * Parse date string safely
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, ISO_DATE);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + dateString);
            return LocalDate.now();
        }
    }
    
    /**
     * Format date for display
     */
    public static String formatForDisplay(LocalDate date) {
        if (date == null) return "N/A";
        return date.format(DISPLAY_DATE);
    }
    
    /**
     * Get relative date description
     */
    public static String getRelativeDescription(LocalDate date) {
        if (date == null) return "Unknown";
        
        LocalDate today = LocalDate.now();
        long daysDiff = ChronoUnit.DAYS.between(date, today);
        
        if (daysDiff == 0) return "Today";
        if (daysDiff == 1) return "Yesterday";
        if (daysDiff == -1) return "Tomorrow";
        if (daysDiff > 1 && daysDiff <= 7) return daysDiff + " days ago";
        if (daysDiff < -1 && daysDiff >= -7) return "In " + Math.abs(daysDiff) + " days";
        
        return formatForDisplay(date);
    }
    
    /**
     * Check if date is today
     */
    public static boolean isToday(LocalDate date) {
        return date != null && date.equals(LocalDate.now());
    }
    
    /**
     * Check if date is yesterday
     */
    public static boolean isYesterday(LocalDate date) {
        return date != null && date.equals(LocalDate.now().minusDays(1));
    }
    
    /**
     * Check if date is this week
     */
    public static boolean isThisWeek(LocalDate date) {
        if (date == null) return false;
        
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        
        return !date.isBefore(startOfWeek) && !date.isAfter(endOfWeek);
    }
    
    /**
     * Get days between two dates
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        return ChronoUnit.DAYS.between(start, end);
    }
    
    /**
     * Get list of dates for the current week
     */
    public static List<LocalDate> getCurrentWeekDates() {
        List<LocalDate> weekDates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        
        for (int i = 0; i < 7; i++) {
            weekDates.add(startOfWeek.plusDays(i));
        }
        
        return weekDates;
    }
    
    /**
     * Get list of dates for the past N days (including today)
     */
    public static List<LocalDate> getPastDays(int numberOfDays) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        
        for (int i = numberOfDays - 1; i >= 0; i--) {
            dates.add(today.minusDays(i));
        }
        
        return dates;
    }
    
    /**
     * Check if a date is in the past
     */
    public static boolean isPast(LocalDate date) {
        return date != null && date.isBefore(LocalDate.now());
    }
    
    /**
     * Check if a date is in the future
     */
    public static boolean isFuture(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }
    
    /**
     * Get the start of current month
     */
    public static LocalDate getStartOfCurrentMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }
    
    /**
     * Get the end of current month
     */
    public static LocalDate getEndOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(now.lengthOfMonth());
    }
    
    /**
     * Format duration in a readable way
     */
    public static String formatDuration(long days) {
        if (days == 0) return "0 days";
        if (days == 1) return "1 day";
        if (days < 7) return days + " days";
        if (days < 30) {
            long weeks = days / 7;
            long remainingDays = days % 7;
            String result = weeks + (weeks == 1 ? " week" : " weeks");
            if (remainingDays > 0) {
                result += " and " + remainingDays + (remainingDays == 1 ? " day" : " days");
            }
            return result;
        }
        if (days < 365) {
            long months = days / 30;
            return months + (months == 1 ? " month" : " months");
        }
        
        long years = days / 365;
        return years + (years == 1 ? " year" : " years");
    }
    
    /**
     * Get current timestamp as string
     */
    public static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Validate date string format
     */
    public static boolean isValidDateString(String dateString) {
        try {
            LocalDate.parse(dateString, ISO_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
