package com.skincare.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Utility class for generating unique IDs
 * Demonstrates utility class pattern and thread safety
 */
public class IdGenerator {
    private static final AtomicLong counter = new AtomicLong(0);
    private static final String PREFIX = "SC"; // SkinCare prefix
    
    // Private constructor to prevent instantiation
    private IdGenerator() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Generate a unique ID with timestamp and counter
     */
    public static String generateId() {
        long timestamp = System.currentTimeMillis();
        long count = counter.incrementAndGet();
        return PREFIX + timestamp + "_" + count;
    }
    
    /**
     * Generate a simple numeric ID
     */
    public static String generateSimpleId() {
        return String.valueOf(counter.incrementAndGet());
    }
    
    /**
     * Generate ID with custom prefix
     */
    public static String generateId(String prefix) {
        long timestamp = System.currentTimeMillis();
        long count = counter.incrementAndGet();
        return prefix + timestamp + "_" + count;
    }
    
    /**
     * Generate readable ID with date
     */
    public static String generateReadableId() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        long count = counter.incrementAndGet();
        return PREFIX + "_" + dateStr + "_" + count;
    }
    
    /**
     * Reset counter (for testing purposes)
     */
    public static void resetCounter() {
        counter.set(0);
    }
    
    /**
     * Get current counter value
     */
    public static long getCurrentCounter() {
        return counter.get();
    }
}
