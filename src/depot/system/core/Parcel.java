/*
Parcel.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 19/11/2024
Updated: 29/11/2024
*/

package depot.system.core;

/**
 * Represents a parcel in the Depot System.
 * Stores essential details about the parcel, such as:
 * - Parcel ID
 * - Dimensions (length x width x height)
 * - Weight in kilograms
 * - Days in the depot
 * - Status ("Waiting" or "Collected").
 *
 */
public class Parcel {
    private String parcelID;
    private String dimensions; // length x width x height
    private double weight; // kgs
    private int daysInDepot;
    private String status; // waiting or collected

    /**
     * Constructs a Parcel object with the specified details.
     *
     * @param parcelID the unique ID of the parcel; cannot be null or empty.
     * @param dimensions the dimensions of the parcel in the format 'length x width x height'; must be valid.
     * @param weight the weight of the parcel in kilograms; must be a positive number.
     * @param daysInDepot the number of days the parcel has been in the depot; must be a positive number.
     * @param status the status of the parcel; must be "Waiting" or "Collected".
     * @throws IllegalArgumentException if any parameter does not meet its constraints.
     */
    public Parcel(String parcelID, String dimensions, double weight, int daysInDepot, String status) {
        if (parcelID == null || parcelID.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (dimensions == null || validateDimensions(dimensions)) {
            throw new IllegalArgumentException("Invalid dimensions. Use the format 'length x width x height'.");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Parcel can't have a negative weight");
        }
        if (daysInDepot < 0) {
            throw new IllegalArgumentException("Days in depot can't be negative");
        }
        if (!status.equals("Waiting") && !status.equals("Collected")) {
            throw new IllegalArgumentException("Status must be 'Waiting' or 'Collected'");
        }
        this.parcelID = parcelID;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    /**
     * Gets the parcel ID.
     *
     * @return the parcel ID.
     */
    public String getParcelID() {
        return parcelID;
    }

    /**
     * Gets the dimensions of the parcel.
     *
     * @return the dimensions of the parcel in the format 'length x width x height'.
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * Gets the weight of the parcel.
     *
     * @return the weight of the parcel in kilograms.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the parcel.
     *
     * @param weight the new weight; must be non-negative.
     * @throws IllegalArgumentException if weight is negative.
     */
    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Parcel can't have a negative weight");
        }
        this.weight = weight;
    }

    /**
     * Gets the number of days the parcel has been in the depot.
     *
     * @return the number of days in the depot.
     */
    public int getDaysInDepot() {
        return daysInDepot;
    }

    /**
     * Gets the status of the parcel.
     *
     * @return the status of the parcel, either "Waiting" or "Collected".
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the parcel.
     *
     * @param status the new status; must be "Waiting" or "Collected".
     * @throws IllegalArgumentException if status is invalid.
     */
    public void setStatus(String status) {
        if (!status.equals("Waiting") && !status.equals("Collected")) {
            throw new IllegalArgumentException("Status must be 'Waiting' or 'Collected'");
        }
        this.status = status;
    }

    /**
     * Applies a discount to a given fee based on the number of days in the depot.
     * If the parcel has been in the depot for less than 2 days, a 10% discount is applied.
     * @param fee the original fee.
     * @return the discounted fee if the parcel is picked up within 2 days; otherwise, the original fee.
     * @throws IllegalArgumentException if the fee is negative.
     */
    public double applyDiscount(double fee) {
        if (daysInDepot < 2) {
            double discount = fee * 0.10; // 10% discount if customers pick up parcel fast
            return fee - discount;
        }
        return fee;
    }

    /**
     * Validates the dimensions string for the parcel.
     *
     * @param dimensions the dimensions string to validate in the format length x width x height.
     * @return true if the dimensions are invalid, false otherwise.
     */
    private boolean validateDimensions(String dimensions) {
        return !dimensions.matches("\\d+ x \\d+ x \\d+");
    }

    /**
     * Returns a string representation of the Parcel object.
     *
     * @return a string in the format "ID=..., Dimensions=..., Weight=...kg, Days in Depot=..., Status=...".
     */
    @Override
    public String toString() {
        return String.format("ID=%s, Dimensions=%s, Weight=%.1fkg, Days in Depot=%d, Status=%s",
                parcelID, dimensions, weight, daysInDepot, status);
    }

}
