/*
ParcelPanel.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 29/11/2024
*/

package depot.system.gui;

import depot.system.core.Parcel;
import depot.system.core.ParcelMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * GUI panel for displaying and managing parcels.
 * Displays parcel details in a table and provides a refresh button to update the table.
 */
public class ParcelPanel extends JPanel {
    private final DefaultTableModel tableModel; // Table model for the parcel data
    private final ParcelMap parcelMap;

    /**
     * Constructs a ParcelPanel to display parcel details.
     *
     * @param parcelMap the map of parcels to display; cannot be null.
     */
    public ParcelPanel(ParcelMap parcelMap) {
        if (parcelMap == null) {
            throw new IllegalArgumentException("Parcel map cannot be null.");
        }

        this.parcelMap = parcelMap;

        // Set layout for this panel
        setLayout(new BorderLayout());

        // Initialize table model with column headers
        tableModel = new DefaultTableModel(
                new Object[]{"Parcel ID", "Dimensions", "Weight", "Days in Depot", "Status"},
                0
        );

        // Create table with the model
        JTable parcelTable = new JTable(tableModel);
        parcelTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane tableScrollPane = new JScrollPane(parcelTable);
        add(tableScrollPane, BorderLayout.CENTER);


        // Create and add refresh button
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshParcels());
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshParcels();
    }

    /**
     * Refreshes the parcel table with the latest data from the parcel map.
     * Clears the table and reloads it with current parcel details.
     */
    public void refreshParcels() {
        tableModel.setRowCount(0); // Clear existing rows
        for (Parcel parcel : parcelMap.getParcels().values()) {
            tableModel.addRow(new Object[]{
                    parcel.getParcelID(),
                    parcel.getDimensions(),
                    parcel.getWeight(),
                    parcel.getDaysInDepot(),
                    parcel.getStatus()
            });
        }
    }
}
