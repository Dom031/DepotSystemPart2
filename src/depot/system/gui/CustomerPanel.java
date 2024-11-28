package depot.system.gui;

import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel {

    private JList<String> customerList; // List to display customers
    private DefaultListModel<String> listModel; // Data model for the list

    public CustomerPanel(){
        //layout for this panel
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Customer Queue");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD,18));
        add(headerLabel, BorderLayout.NORTH);


        //Default list to hold customer data
        listModel = new DefaultListModel<>();

        //Initializing the JList with the model
        customerList = new JList<>();
        customerList.setFont(new Font("Arial", Font.PLAIN,14));

        JScrollPane scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);


    }
}
