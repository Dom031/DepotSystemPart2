/*
Log.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 22/11/2024
Updated: 29/11/2024
*/

package depot.system.core;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
    public void addLogEntry(String entry) {
        if (entry == null || entry.trim().isEmpty()) {
            throw new IllegalArgumentException("Log entry cannot be null or empty.");
        }
        logEntries.append(entry).append("\n");
    }

    /**
     * Saves the log entries to a file.
     *
     * @param filePath the file path to save the log; must be valid.
     */
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(logEntries.toString());
            System.out.println("Log entries successfully saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving log to file: " + e.getMessage());
        }
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