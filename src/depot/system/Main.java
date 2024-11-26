package depot.system;

public class Main {
    public static void main(String[] args) {
        System.out.println("Depot System - Assignment Part 2");

        // Initialize required objects
        QueueOfCustomer customerQueue = new QueueOfCustomer();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance();
        Manager manager = new Manager(customerQueue, parcelMap, log);

        // Path to the parcels CSV file (adjust the path if needed)
        String parcelsFilePath = "C:\\Users\\domin\\OneDrive\\Desktop\\DepotSystemPart2\\resources\\Parcels.csv";

        // Read parcels from the file
        manager.readParcels(parcelsFilePath);

        // Print the loaded parcels to verify
        System.out.println("Parcels loaded into ParcelMap:");
        System.out.println(parcelMap.getParcels());

        // Print log entries to verify logging
        System.out.println("Log entries:");
        System.out.println(log.getLogEntries());
    }

    }
