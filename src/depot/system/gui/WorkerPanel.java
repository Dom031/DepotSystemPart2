package depot.system.gui;

import depot.system.core.Customer;
import depot.system.core.Parcel;
import depot.system.core.ParcelMap;
import depot.system.core.QueueOfCustomer;
import depot.system.core.Worker;
import depot.system.core.Log;

import javax.swing.*;
import java.awt.*;

public class WorkerPanel extends JPanel {
    private JLabel customerLabel;
    private JLabel parcelLabel;
    private JButton processButton;
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
        customerLabel = new JLabel("Current customer: ");
        parcelLabel = new JLabel("Current Parcel");
        infoPanel.add(customerLabel);
        infoPanel.add(parcelLabel);
        add(infoPanel, BorderLayout.CENTER);

        //Buttons in the panel
        JPanel buttonPanel = new JPanel();
        processButton = new JButton("Process next customer");
        buttonPanel.add(processButton);
        add(buttonPanel, BorderLayout.SOUTH);

        processButton.addActionListener(e -> processNextCustomer());
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


}
