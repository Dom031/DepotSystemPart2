package depot.system;

import depot.system.core.*;
import depot.system.gui.MainFrame;

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

        // Read data from files using Manager
        Manager.readParcels(parcelsFilePath);
        Manager.readCustomers(customersFilePath);

        // Launch GUI
        new MainFrame(manager, customerQueue, parcelMap, log);
    }
}