package depot.system.gui;

import depot.system.core.Customer;
import depot.system.core.QueueOfCustomer;

import javax.swing.*;
import java.awt.*;

public class CustomerPanel extends JPanel {

    private final DefaultListModel<String> listModel; // Data model for the list
    private final QueueOfCustomer customerQueue;


    public CustomerPanel(QueueOfCustomer customerQueue){
        this.customerQueue = customerQueue;

        //layout for this panel
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Customer Queue");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD,18));
        add(headerLabel, BorderLayout.NORTH);


        //Default list to hold customer data
        listModel = new DefaultListModel<>();

        //Initializing the JList with the model
        // List to display customers
        JList<String> customerList = new JList<>(listModel);
        customerList.setFont(new Font("Arial", Font.PLAIN,14));

        JScrollPane scrollPane = new JScrollPane(customerList);
        add(scrollPane, BorderLayout.CENTER);

        // Create and add a refresh button
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh Queue");
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e-> refreshCustomerList());

        refreshCustomerList();

    }

    public void refreshCustomerList(){
        listModel.clear();
        for(Customer customer: customerQueue.getListOfCustomer()){
            listModel.addElement((customer.getName()));
        }
    }
}
