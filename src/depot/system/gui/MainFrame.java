package depot.system.gui;

import depot.system.core.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Manager manager;
    private QueueOfCustomer customerQueue;
    private ParcelMap parcelMap;
    private Log log;

    public MainFrame(Manager manager, QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log) {
        this.manager=manager;
        this.customerQueue=customerQueue;
        this.parcelMap=parcelMap;
        this.log = log;

        //frame properties for main window
        setTitle("Depot System GUI");
        setSize(800,800); //w x h
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main panel with CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);


        // Panels to the CardLayout (Placeholders for now)
        CustomerPanel customerPanel = new CustomerPanel(customerQueue); // Pass customerQueue to the panel
        mainPanel.add(customerPanel, "CustomerPanel");
        mainPanel.add(new ParcelPanel(parcelMap), "ParcelPanel");
        mainPanel.add(new WorkerPanel(customerQueue, parcelMap, log, new Worker("W123", "John")), "WorkerPanel");

        //Navigating panels with buttons
        JPanel navPanel = getJPanel(cardLayout, mainPanel);


        add(navPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);


    }

    private static JPanel getJPanel(CardLayout cardLayout, JPanel mainPanel) {
        JPanel navPanel = new JPanel();

        JButton customerButton = new JButton("Customer");
        JButton parcelButton = new JButton("Parcel");
        JButton workerButton = new JButton("Worker");

        //Action listeners to switch panels when clicked.
        customerButton.addActionListener(e -> cardLayout.show(mainPanel, "CustomerPanel"));
        parcelButton.addActionListener(e -> cardLayout.show(mainPanel, "ParcelPanel"));
        workerButton.addActionListener(e -> cardLayout.show(mainPanel, "WorkerPanel"));

        //Adding the buttons to the nav panel.
        navPanel.add(customerButton);
        navPanel.add(parcelButton);
        navPanel.add(workerButton);

        return navPanel;
    }
}
