/*
Log.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 22/11/2024
Updated: 03/12/2024
*/

package depot.system.core;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.stream.Collectors;

/**
 * Singleton class for managing log entries in the Depot System.
 * The log stores messages and provides functionality to save them to a file.
 */
public class Log {
    private final StringBuilder logEntries; // To store log messages
    private static Log instance; // Singleton instance

    /**
     * Private constructor to ensure only one instance of the Log class is created.
     */
    private Log() {
        this.logEntries = new StringBuilder();
    }

    /**
     * Retrieves the singleton instance of the Log class.
     *
     * @return the single instance of Log.
     */
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    /**
     * Adds a log entry to the log.
     *
     * @param entry the log message to add; cannot be null or empty.
     * @throws IllegalArgumentException if the log entry is null or empty.
     */
    public void addLogEntry(String logType, String entry){
        if (logType == null || logType.trim().isEmpty()){
            throw new IllegalArgumentException("Log type cannot be null or empty");
        }
        if (entry == null || entry.trim().isEmpty()) {
            throw new IllegalArgumentException("Log entry cannot be null or empty.");
        }
        logEntries.append(String.format("[%s] %s%n", logType.toUpperCase(), entry));
    }

    /**
     * Saves the log entries to a file.
     *
     * @param baseFilePath the file path to save the log; must be valid.
     */
    public void saveToFile(String baseFilePath) {
        try {
            // Save general logs
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseFilePath + "_general.txt"))) {
                writer.write("=== General Logs ===\n");
                writer.write(logEntries.toString());
            }

            // Save parcel logs
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseFilePath + "_parcel.txt"))) {
                writer.write("=== Parcel Logs ===\n");
                writer.write(filterLogs("ADD_PARCEL") + filterLogs("PROCESS_PARCEL"));
            }

            // Save customer logs
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(baseFilePath + "_customer.txt"))) {
                writer.write("=== Customer Logs ===\n");
                writer.write(filterLogs("ADD_CUSTOMER"));
            }

            System.out.println("Logs successfully saved to multiple files.");
        } catch (IOException e) {
            System.out.println("Error saving logs to file: " + e.getMessage());
        }
    }

    // Helper method to filter logs by type
    private String filterLogs(String logType) {
        return logEntries.toString().lines() // Convert StringBuilder to String first
                .filter(line -> line.contains(logType)) // Filter lines containing the log type
                .collect(Collectors.joining("\n")) + "\n"; // Join the filtered lines with newlines
    }

    /**
     * Returns a string representation of all log entries.
     *
     * @return the log entries as a single string.
     */
    @Override
    public String toString() {
        return logEntries.toString();
    }
}