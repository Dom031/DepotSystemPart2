package depot.system;

public class Log {
    private  StringBuilder logEntries; //To store log messages
    private static Log instance; // singleton instance as requested on the assignment brief

    //private constructor for singleton
    private Log(){
        this.logEntries = new StringBuilder();
    }

    //public method to get the single instance of log
    public static Log getInstance(){
        if (instance == null){
            instance = new Log();
        }
        return instance;
    }

    //getter for log entries if needed, not sure yet.
    public StringBuilder getLogEntries(){
        return LogEntries;
    }

    public void setLogEntries(StringBuilder logEntries) {
        this.logEntries = logEntries;
    }
    //methods to be added later
}


