/*
Customer.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 19/11/2024
Updated: 29/11/2024
*/

package depot.system.core;

/**
 * Represents a customer in the Depot System.
 * Each customer has a unique sequence number, a name, and an associated parcel ID.
 */
public class Customer {
    private final int sequenceNumber;
    private final String name;
    private final String parcelID;

    /**
     * Constructs a Customer object with the specified details.
     *
     * @param sequenceNumber the sequence number of the customer; must be positive.
     * @param name the name of the customer; cannot be null or empty.
     * @param parcelID the parcel ID associated with the customer; cannot be null or empty.
     * @throws IllegalArgumentException if any parameter does not meet its constraints.
     */
    public Customer(int sequenceNumber, String name, String parcelID) {
        if (sequenceNumber <= 0) {
            throw new IllegalArgumentException("Sequence number must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (parcelID == null || parcelID.trim().isEmpty()) {
            throw new IllegalArgumentException("Parcel ID cannot be null or empty.");
        }
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    /**
     * Gets the name of the customer.
     *
     * @return the name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the parcel ID associated with the customer.
     *
     * @return the parcel ID of the customer.
     */
    public String getParcelID() {
        return parcelID;
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string in the format "Name=... , Parcel ID = ...".
     */
    @Override
    public String toString() {
        return String.format("Name=%s, Parcel ID=%s", name, parcelID);
    }

}
