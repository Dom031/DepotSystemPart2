package depot.system.gui;

import depot.system.core.Customer;
import depot.system.core.Parcel;
import depot.system.core.ParcelMap;
import depot.system.core.QueueOfCustomer;
import depot.system.core.Worker;
import depot.system.core.Log;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WorkerPanel extends JPanel {
    private JLabel customerLabel;
    private JLabel parcelLabel;
    private JButton processButton;
    private JButton saveParcelsButton;
    private QueueOfCustomer customerQueue;
    private ParcelMap parcelMap;
    private Log log;
    private Worker worker;

    public WorkerPanel(QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log, Worker worker){
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
        this.worker = worker;

        setLayout(new BorderLayout());

        //Customer and Parcel information panel
        JPanel infoPanel = new JPanel(new GridLayout(2,1));
        customerLabel = new JLabel("Current customer: None");
        parcelLabel = new JLabel("Current Parcel: None");
        infoPanel.add(customerLabel);
        infoPanel.add(parcelLabel);
        add(infoPanel, BorderLayout.CENTER);

        //Buttons in the panel
        JPanel buttonPanel = new JPanel();
        processButton = new JButton("Process next customer");
        buttonPanel.add(processButton);
        saveParcelsButton = new JButton("Save Collected Parcels to File");
        buttonPanel.add(saveParcelsButton);
        JButton saveLogButton = new JButton("Save Log to File");
        buttonPanel.add(saveLogButton);
        add(buttonPanel, BorderLayout.SOUTH);

        processButton.addActionListener(e -> processNextCustomer());
        saveParcelsButton.addActionListener(e -> saveParcelsToFile());
        saveLogButton.addActionListener(e -> saveLogToFile());


    }

    private void processNextCustomer() {
        if(customerQueue.isEmpty()){
            JOptionPane.showMessageDialog(this, "No customer in the queue", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Customer customer = customerQueue.dequeueCustomer();
        customerLabel.setText("Current Customer: " + customer.getName());

        Parcel parcel = parcelMap.getParcels().get(customer.getParcelID());
        if (parcel != null){
            parcelLabel.setText("Associated Parcel: " + parcel);
            worker.processCustomer(customer, parcelMap, log); //process customer and update parcel
        } else {
            parcelLabel.setText("Associated Parcel: Not Found!");
        }
    }
    private void saveLogToFile() {
        // Define the path to the output directory
        String outputDir = "output/";

        // Set the log file path
        String filePath = outputDir + "log_entries.txt"; // log_entries.txt in the output directory

        // Call the log's saveToFile method to save the log
        log.saveToFile(filePath);
        JOptionPane.showMessageDialog(this, "Log successfully saved to: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveParcelsToFile() {
        // Define the path to the output directory
        String outputDir = "output/";

        // Set the parcels file path
        String filePath = outputDir + "collected_parcels.txt"; // collected_parcels.txt in the output directory

        // Call the ParcelMap's saveCollectedParcelsToFile method to save the collected parcels
        parcelMap.saveCollectedParcelsToFile(filePath);
        JOptionPane.showMessageDialog(this, "Collected parcels successfully saved to: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
    }



}
