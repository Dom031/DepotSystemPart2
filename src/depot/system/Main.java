package depot.system;
import java.util.Map;
public class Main {
    public static void main(String[] args) {
        System.out.println("Depot System - Assignment Part 2");

        // Initialize required objects
        QueueOfCustomer customerQueue = new QueueOfCustomer();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance();
        Manager manager = new Manager(customerQueue, parcelMap, log);

        // Paths to CSV files
        String parcelsFilePath = "resources/Parcels.csv";
        String customersFilePath = "resources/Custs.csv";

        // Read data from files
        manager.readParcels(parcelsFilePath);
        manager.readCustomers(customersFilePath);

        // Process all customers in the queue
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.dequeueCustomer();
            manager.processCustomer(customer);
        }

        // Print all log entries
        System.out.println("Log entries:");
        System.out.println(log.getLogEntries());
    }
}