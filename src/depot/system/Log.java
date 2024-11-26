package depot.system;


public class Log {
    private StringBuilder logEntries; // To store log messages
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

    // Getter for log entries
    public String getLogEntries() {
        return logEntries.toString();
    }

    // Override toString to display logs
    @Override
    public String toString() {
        return logEntries.toString();
    }
}
