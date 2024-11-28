package depot.system.gui;
import depot.system.core.Parcel;
import depot.system.core.ParcelMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;



public class ParcelPanel extends JPanel{
    private JTable parcelTable;
    private DefaultTableModel tableModel;
    private ParcelMap parcelMap;

    public ParcelPanel(ParcelMap parcelMap){
        //Table setup and adding to the panel
        this.parcelMap = parcelMap;
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Parcel ID", "Dimensions", "Weight", "Days in Depot", "Status"}, 0);
        parcelTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(parcelTable);

        add(tableScrollPane, BorderLayout.CENTER);

        //Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");

        //Refresh button and layout in the frame
        refreshButton.addActionListener(e -> refreshParcels());
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void refreshParcels(){
        tableModel.setRowCount(0);
        for(Parcel parcel : parcelMap.getParcels().values()){
            tableModel.addRow(new Object[]{
                    parcel.getParcelID(),
                    parcel.getDimensions(),
                    parcel.getWeight(),
                    parcel.getDaysInDepot(),
                    parcel.getStatus()
            });
        }
    }
}
