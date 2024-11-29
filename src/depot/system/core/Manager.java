/*
Manager.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 22/11/2024
Updated: 29/11/2024
*/

package depot.system.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Manages interactions between the customer queue, parcel map, and log.
 * Provides functionality to read customers and parcels from CSV files
 * and update the corresponding data structures.
 */
public class Manager {
    // References to customer queue, parcel map, and log instance.
    private static QueueOfCustomer customerQueue;
    private static ParcelMap parcelMap;
    private static Log log;

    /**
     * Constructs a Manager object to manage the provided customer queue, parcel map, and log.
     *
     * @param customerQueue the queue of customers.
     * @param parcelMap the map of parcels.
     * @param log the log instance.
     */
    public Manager(QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log) {
        Manager.customerQueue = customerQueue;
        Manager.parcelMap = parcelMap;
        Manager.log = log;
    }

    /**
     * Gets the customer queue managed by this manager.
     *
     * @return the customer queue.
     */
    public QueueOfCustomer getCustomerQueue() {
        return customerQueue;
    }

    /**
     * Gets the parcel map managed by this manager.
     *
     * @return the parcel map.
     */
    public ParcelMap getParcelMap() {
        return parcelMap;
    }

    /**
     * Gets the log instance managed by this manager.
     *
     * @return the log instance.
     */
    public Log getLog() {
        return log;
    }

    /**
     * Reads customer data from a CSV file and adds valid customers to the queue.
     *
     * @param filePath the path to the CSV file containing customer data.
     */
    public static void readCustomers(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // CSV is split by name "," ID.

                if (parts.length == 2) { // Provided CSV has 2 parts
                    String name = parts[0].trim();
                    String parcelID = parts[1].trim();

                    // Split name into first and last name
                    String[] nameParts = name.split(" ");
                    if (nameParts.length < 2) {
                        log.addLogEntry("Skipping invalid customer name: " + name);
                        continue;
                    }
                    String firstName = nameParts[0];
                    String lastName = nameParts[1];

                    // Create a sequence number based on the queue size + 1
                    int sequenceNumber = customerQueue.getListOfCustomer().size() + 1;

                    // Create a Customer object
                    Customer customer = new Customer(sequenceNumber, firstName + " " + lastName, parcelID);

                    // Add customer to the queue
                    customerQueue.enqueueCustomer(customer);
                    log.addLogEntry("Added Customer: " + customer);
                } else {
                    log.addLogEntry("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }
    }

    /**
     * Reads parcel data from a CSV file and adds valid parcels to the map.
     *
     * @param filePath the path to the CSV file containing parcel data.
     */
    public static void readParcels(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 6) { // Expected CSV format with 6 columns
                    String parcelID = parts[0].trim();
                    int daysInDepot = Integer.parseInt(parts[1].trim());
                    double weight = Double.parseDouble(parts[2].trim());
                    String dimensions = parts[3].trim() + " x " + parts[4].trim() + " x " + parts[5].trim();

                    // Create a Parcel object and add it to the map
                    Parcel parcel = new Parcel(parcelID, dimensions, weight, daysInDepot, "Waiting");
                    parcelMap.getParcels().put(parcelID, parcel);
                    log.addLogEntry("Added Parcel: " + parcel);
                } else {
                    log.addLogEntry("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            log.addLogEntry("Error reading parcels file: " + e.getMessage());
        } catch (NumberFormatException e) {
            log.addLogEntry("Error parsing a number in the file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the Manager's state.
     *
     * @return the string representation of the customer queue, parcel map, and log.
     */
    @Override
    public String toString() {
        return "Manager{" +
                "customerQueue=" + customerQueue +
                ", parcelMap=" + parcelMap +
                ", log=" + log +
                '}';
    }
}
