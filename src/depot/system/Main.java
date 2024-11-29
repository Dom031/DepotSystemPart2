/*
Main.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 29/11/2024
*/

package depot.system;

import depot.system.core.*;
import depot.system.gui.MainFrame;

/**
 * Main entry point for the Depot System application.
 * The program simulates the operations of a parcel depot, 
 * including customer management, parcel tracking, and logging interactions.
 * It loads customer and parcel data from CSV files and provides a GUI
 * for managing depot activities such as processing customers, saving logs, and managing parcels.
 */
public class Main {

    /**
     * Main method to initialize the Depot System application.
     *
     * @param args command-line arguments (not used).
     */
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
