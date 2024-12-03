/*
WorkerPanel.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 03/12/2024
*/

package depot.system.gui;

import depot.system.core.Customer;
import depot.system.core.Parcel;
import depot.system.core.ParcelMap;
import depot.system.core.QueueOfCustomer;
import depot.system.core.Worker;
import depot.system.core.Log;

import javax.swing.*;
import java.awt.*;

/**
 * GUI panel for worker interactions in the Depot System.
 * Provides functionality to process the next customer, save logs, and save collected parcels.
 */
    public class WorkerPanel extends JPanel {
    private final JLabel customerLabel; // Label to display the current customer
    private final JLabel parcelLabel;   // Label to display the current parcel
    private final QueueOfCustomer customerQueue;
    private final ParcelMap parcelMap;
    private final Log log;
    private final Worker worker;
    private final CustomerPanel customerPanel;
    private final ParcelPanel parcelPanel;

    /**
     * Constructs a WorkerPanel for managing worker interactions.
     *
     * @param customerQueue the queue of customers; cannot be null.
     * @param parcelMap the map of parcels; cannot be null.
     * @param log the log instance for recording actions; cannot be null.
     * @param worker the worker processing the customers and parcels; cannot be null.
     */
    public WorkerPanel(QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log, Worker worker, CustomerPanel customerPanel, ParcelPanel parcelPanel) {
        if (customerQueue == null || parcelMap == null || log == null || worker == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }

        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
        this.worker = worker;
        this.customerPanel = customerPanel;
        this.parcelPanel = parcelPanel;

        // Set layout
        setLayout(new BorderLayout());

        // Customer and Parcel information panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        customerLabel = new JLabel("Current Customer: None");
        parcelLabel = new JLabel("Current Parcel: None");
        infoPanel.add(customerLabel);
        infoPanel.add(parcelLabel);
        add(infoPanel, BorderLayout.CENTER);

        // Buttons in the panel
        JPanel buttonPanel = new JPanel();
        JButton processButton = new JButton("Process Next Customer");
        buttonPanel.add(processButton);
        JButton saveParcelsButton = new JButton("Save Collected Parcels to File");
        buttonPanel.add(saveParcelsButton);
        JButton saveLogButton = new JButton("Save Log to File");
        buttonPanel.add(saveLogButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        processButton.addActionListener(e -> processNextCustomer());
        saveParcelsButton.addActionListener(e -> saveParcelsToFile());
        saveLogButton.addActionListener(e -> saveAllLogsToFiles());
    }

    /**
     * Processes the next customer in the queue.
     * Updates the customer and parcel labels and processes the parcel associated with the customer.
     *  Logs the transaction and refrehes the customer and parcel panels and if
     *  no associated parcel is found, it shows an error message.
     *  Not applicable in this scenario as the CSV matches completely.
     *
     */
    private void processNextCustomer() {
        if (customerQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customer in the queue.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Customer customer = customerQueue.dequeueCustomer();
        customerLabel.setText("Current Customer: " + customer.getName());

        Parcel parcel = parcelMap.getParcels().get(customer.getParcelID());
        if (parcel != null) {
            parcelLabel.setText(String.format("Associated Parcel: ID=%s, Dimensions=%s, Weight=%.1fkg, Days in Depot=%d, Status=%s",
                    parcel.getParcelID(), parcel.getDimensions(), parcel.getWeight(), parcel.getDaysInDepot(), parcel.getStatus()));

            worker.processCustomer(customer, parcelMap, log); // Process customer and update parcel
        } else {
            parcelLabel.setText("Associated Parcel: Not Found!");
        }

        customerPanel.refreshCustomerList();
        parcelPanel.refreshParcels();
    }

    /**
     * Saves the log entries in separated files for general, parcel and customer logs
     * in the output directory.
     * Displays a confirmation message upon successful save.
     */
    private void saveAllLogsToFiles() {
        String baseFilePath = "output/log_entries";
        log.saveToFile(baseFilePath);
        JOptionPane.showMessageDialog(this, "Logs successfully saved to multiple files.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Saves the collected parcels to a file in the output directory.
     * Displays a confirmation message upon successful save.
     */
    private void saveParcelsToFile() {
        String outputDir = "output/";
        String filePath = outputDir + "collected_parcels.txt";
        parcelMap.saveCollectedParcelsToFile(filePath);
        JOptionPane.showMessageDialog(this, "Collected parcels successfully saved to: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
