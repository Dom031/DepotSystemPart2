package depot.system;

public class Main {
    public static void main(String[] args) {
        System.out.println("Depot System - Assignment Part 2");

        // Initialize some sample data for testing
        try {
            // Create Customer objects
            Customer customer1 = new Customer(1, "John Doe", "X123");
            Customer customer2 = new Customer(2, "Jane Smith", "X456");

            // Create Parcel objects
            Parcel parcel1 = new Parcel("X123", "10 x 5 x 2", 4.5, 3, "Waiting");
            Parcel parcel2 = new Parcel("X456", "15 x 7 x 3", 6.0, 5, "Waiting");

            // Create QueueOfCustomer and ParcelMap
            QueueOfCustomer customerQueue = new QueueOfCustomer();
            customerQueue.enqueueCustomer(customer1);
            customerQueue.enqueueCustomer(customer2);

            ParcelMap parcelMap = new ParcelMap();
            parcelMap.getParcels().put(parcel1.getParcelID(), parcel1);
            parcelMap.getParcels().put(parcel2.getParcelID(), parcel2);

            // Create Log instance (Singleton)
            Log log = Log.getInstance();
            log.addLogEntry("System initialized.");

            // Create Manager object
            Manager manager = new Manager(customerQueue, parcelMap, log);

            // Output initialized data
            System.out.println("Customers in queue: " + customerQueue.getListOfCustomer());
            System.out.println("Parcels in map: " + parcelMap.getParcels());
            System.out.println("Log entries: " + log.getLogEntries());

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
