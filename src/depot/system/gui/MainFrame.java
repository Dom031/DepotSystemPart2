/*
MainFrame.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 28/11/2024
Updated: 30/11/2024
*/

package depot.system.gui;

import depot.system.core.*;

import javax.swing.*;
import java.awt.*;

/**
 * Main GUI frame for the Depot System.
 * Provides navigation between different panels for customers, parcels, and workers.
 */
public class MainFrame extends JFrame {

    /**
     * Constructs the main frame for the Depot System GUI.
     *
     * @param manager the manager instance to manage interactions; not currently used directly in this frame.
     * @param customerQueue the queue of customers; cannot be null.
     * @param parcelMap the map of parcels; cannot be null.
     * @param log the log instance to record actions; cannot be null.
     */
    public MainFrame(Manager manager, QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log) {
        if (customerQueue == null || parcelMap == null || log == null) {
            throw new IllegalArgumentException("Arguments cannot be null.");
        }

        // Frame properties for main window
        setTitle("Depot System GUI");
        setSize(800, 800); // Width x Height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel with CardLayout for switching views
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Panels added to the CardLayout
        CustomerPanel customerPanel = new CustomerPanel(customerQueue, manager);
        ParcelPanel parcelPanel = new ParcelPanel(parcelMap, manager);
        WorkerPanel workerPanel = new WorkerPanel(customerQueue, parcelMap, log, new Worker("W123", "John"), customerPanel, parcelPanel);

        mainPanel.add(customerPanel, "CustomerPanel");
        mainPanel.add(parcelPanel, "ParcelPanel");
        mainPanel.add(workerPanel, "WorkerPanel");
        // Navigation panel with buttons to switch views
        JPanel navPanel = createNavPanel(cardLayout, mainPanel);

        // Add navigation and main panels to the frame
        add(navPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Creates a navigation panel with buttons to switch between panels.
     *
     * @param cardLayout the CardLayout managing the main panel.
     * @param mainPanel the main panel containing different views.
     * @return the navigation panel with buttons.
     */
    private static JPanel createNavPanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel navPanel = new JPanel();

        // Create buttons for navigation
        JButton customerButton = new JButton("Customer");
        JButton parcelButton = new JButton("Parcel");
        JButton workerButton = new JButton("Worker");

        // Action listeners to switch panels when buttons are clicked
        customerButton.addActionListener(e -> cardLayout.show(mainPanel, "CustomerPanel"));
        parcelButton.addActionListener(e -> cardLayout.show(mainPanel, "ParcelPanel"));
        workerButton.addActionListener(e -> cardLayout.show(mainPanel, "WorkerPanel"));

        // Add buttons to the navigation panel
        navPanel.add(customerButton);
        navPanel.add(parcelButton);
        navPanel.add(workerButton);

        return navPanel;
    }
}
