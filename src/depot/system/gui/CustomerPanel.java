/*
CustomerPanel.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 29/11/2024
*/

package depot.system.gui;

import depot.system.core.Customer;
import depot.system.core.QueueOfCustomer;

import javax.swing.*;
import java.awt.*;

/**
 * GUI panel for displaying and managing the customer queue.
 * Provides a list of customers and a button to refresh the displayed queue.
 */
public class CustomerPanel extends JPanel {

    private final DefaultListModel<String> listModel; // Data model for the list
    private final QueueOfCustomer customerQueue;

    /**
     * Constructs a CustomerPanel to display and manage the customer queue.
     *
     * @param customerQueue the queue of customers to display; cannot be null.
     */
    public CustomerPanel(QueueOfCustomer customerQueue) {
        if (customerQueue == null) {
            throw new IllegalArgumentException("Customer queue cannot be null.");
        }

        this.customerQueue = customerQueue;

        // Set layout for this panel
        setLayout(new BorderLayout());

        // Header label for the panel
        JLabel headerLabel = new JLabel("Customer Queue");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headerLabel, BorderLayout.NORTH);

        // Default list model to hold customer data
        listModel = new DefaultListModel<>();

        // Initializing the JList with the model
        JList<String> customerList = new JList<>(listModel);
        customerList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Scroll pane for the customer list
        JScrollPane scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);

        // Create and add a refresh button
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh Queue");
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the refresh button
        refreshButton.addActionListener(e -> refreshCustomerList());

        // Populate the list on initialization
        refreshCustomerList();
    }

    /**
     * Refreshes the customer list displayed in the panel.
     * Clears the list and reloads it with the current customers in the queue.
     */
    public void refreshCustomerList() {
        listModel.clear();
        for (Customer customer : customerQueue.getListOfCustomer()) {
            listModel.addElement(customer.getName());
        }
    }
}
