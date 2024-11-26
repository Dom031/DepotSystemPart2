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
        Worker worker = new Worker("W001", "John");
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.dequeueCustomer();
            worker.processCustomer(customer, parcelMap, log); // Pass parcelMap and log
        }

        // After processing customers, print collected parcels
        System.out.println("Collected Parcels:");
        for (Parcel parcel : parcelMap.getCollectedParcels()) {
            System.out.println(parcel);
        }
    }
}