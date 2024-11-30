/*
ParcelPanel.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 29/11/2024
*/

package depot.system.gui;

import depot.system.core.Manager;
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
    private final Manager manager;

    /**
     * Constructs a ParcelPanel to display parcel details.
     *
     * @param manager the map of parcels to display; cannot be null.
     */
    public ParcelPanel(ParcelMap parcelMap, Manager manager) {
        if (parcelMap == null || manager == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }
        this.manager = manager;
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
        JButton addParcelButton = new JButton("Add Parcel");
        refreshButton.addActionListener(e -> refreshParcels());
        addParcelButton.addActionListener(e -> showAddParcelDialog());
        buttonPanel.add(refreshButton);
        buttonPanel.add(addParcelButton);
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

    private void showAddParcelDialog() {
        JTextField parcelIDField = new JTextField();
        JTextField dimensionsField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField daysInDepotField = new JTextField();
        JTextField statusField = new JTextField();

        Object[] message = {
                "Parcel ID:", parcelIDField,
                "Dimensions (e.g., 10 x 20 x 30):", dimensionsField,
                "Weight (kg):", weightField,
                "Days in Depot:", daysInDepotField,
                "Status (Waiting/Collected):", statusField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Parcel", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String parcelID = parcelIDField.getText().trim();
            String dimensions = dimensionsField.getText().trim();
            String status = statusField.getText().trim();
            double weight;
            int daysInDepot;

            try {
                // Validate numeric inputs
                weight = Double.parseDouble(weightField.getText().trim());
                daysInDepot = Integer.parseInt(daysInDepotField.getText().trim());

                // Call the Manager's addParcel method
                manager.addParcel(parcelID, dimensions, weight, daysInDepot, status);
                refreshParcels(); // Refresh the table to include the new parcel
                JOptionPane.showMessageDialog(this, "Parcel added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Weight and Days in Depot must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
