package depot.system.core;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Log {
    private final StringBuilder logEntries; // To store log messages
    private static Log instance; // Singleton instance

    // Private constructor for singleton
    private Log() {
        this.logEntries = new StringBuilder();
    }

    // Public method to get the single instance of Log
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Method to add log entries
    public void addLogEntry(String entry) {
        if (entry == null || entry.trim().isEmpty()){
            throw new IllegalArgumentException("Log entry cannot be null or empty.");
        }
        logEntries.append(entry).append("\n");
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(logEntries.toString());
            System.out.println("Log entries successfully saved to: " + filePath);
        } catch (IOException e){
            System.out.println("Error saving log to file " + e.getMessage());
        }
    }

    // Override toString to display logs
    @Override
    public String toString() {
        return logEntries.toString();
    }
}
