/*
Worker.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 19/11/2024
Updated: 03/12/2024
*/

package depot.system.core;

/**
 * Represents a worker in the Depot System.
 * Workers are responsible for processing customers and managing parcels.
 */
public class Worker {

    /**
     * Constructs a Worker object with the specified ID and name.
     *
     * @param workerID the unique ID of the worker; must start with 'W' followed by digits.
     * @param name the name of the worker; cannot be null or empty.
     * @throws IllegalArgumentException if the workerID or name is invalid.
     */
    public Worker(String workerID, String name) {
        if (workerID == null || workerID.trim().isEmpty()) {
            throw new IllegalArgumentException("Worker ID cannot be null or empty.");
        }
        if (!workerID.matches("W\\d+")) { // Worker ID should start with 'W' followed by digits
            throw new IllegalArgumentException("Worker ID must start with 'W' followed by digits.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    /**
     * Processes a customer's parcel, marking it as collected, calculating fees,
     * logging the transaction, and adding the parcel to the collected parcels list.
     *
     * @param customer the customer whose parcel is being processed.
     * @param parcelMap the map containing parcels and their statuses.
     * @param log the log to record processing details.
     */
    public void processCustomer(Customer customer, ParcelMap parcelMap, Log log) {
        String parcelID = customer.getParcelID();
        Parcel parcel = parcelMap.getParcels().get(parcelID);

        if (parcel != null) {
            parcel.setStatus("Collected");
            double fee = calculateFee(parcel);
            log.addLogEntry("PROCESS_PARCEL", String.format(
                    "Parcel: %s collected by %s, Fee: £%.2f",
                    parcelID, customer.getName(), fee
            ));

            parcelMap.addToCollectedParcels(parcel);
        }
    }

    /**
     * Calculates the total fee for a parcel based on its weight, storage time,
     * and any applicable discounts. The fees are combined and
     * any discounts are applied by a separated function based on the days in depot.
     *
     * @param parcel the parcel for which the fee is calculated.
     * @return the total fee after applying discounts.
     */
    public double calculateFee(Parcel parcel) {
        double baseFee = 5.0; // Base fee
        double weightFee = parcel.getWeight() * 0.5;
        double storageFee = parcel.getDaysInDepot() * 0.2;
        double totalFee = baseFee + weightFee + storageFee;
        return parcel.applyDiscount(totalFee);
    }
}
