/*
ParcelMap.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 22/11/2024
Updated: 03/12/2024
*/

package depot.system.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Manages a collection of parcels in the Depot System.
 * Provides functionality for tracking and saving parcel details.
 */
public class ParcelMap {
    private final Map<String, Parcel> parcels; // Map of Parcel with ID -> Parcel
    private final List<Parcel> collectedParcels; // Separate list for collected parcels

    /**
     * Constructs a ParcelMap object to manage parcels and collected parcels.
     */
    public ParcelMap() {
        this.parcels = new HashMap<>();
        this.collectedParcels = new ArrayList<>();
    }

    /**
     * Gets the map of parcels.
     *
     * @return a map of parcel IDs to Parcel objects.
     */
    public Map<String, Parcel> getParcels() {
        return parcels;
    }

    /**
     * Adds a parcel to the list of collected parcels.
     *
     * @param parcel the parcel to add; cannot be null.
     */
    public void addToCollectedParcels(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel cannot be null.");
        }
        collectedParcels.add(parcel);
    }


    /**
     * Gets the list of collected parcels.
     *
     * @return a list of collected parcels.
     */
    public List<Parcel> getCollectedParcels() {
        return collectedParcels;
    }

    /**
     * Saves the details of collected parcels to a file.
     *
     * @param filePath the file path to save the parcel details; must be valid.
     */
    public void saveCollectedParcelsToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("=== Collected Parcels ===\n");
            for (Parcel parcel : getCollectedParcels()) {
                writer.write(parcel.toString() + "\n");
            }
            System.out.println("Collected parcels saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving collected parcels to file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the parcels map.
     *
     * @return the string representation of the parcels map.
     */
    @Override
    public String toString() {
        return parcels.toString();
    }
}



