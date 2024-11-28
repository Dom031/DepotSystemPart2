package depot.system.gui;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        //frame properties for main frame
        setTitle("Depot System GUI");
        setSize(800,800); //w x h
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Main panel with CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Panels to the CardLayout (Placeholders for now)
        mainPanel.add(new JPanel(), "CustomerPanel");
        mainPanel.add(new JPanel(), "ParcelPanel");
        mainPanel.add(new JPanel(), "WorkerPanel");

        //Buttons for the panels
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


        add(navPanel, BorderLayout.NORTH);
        add(navPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}
