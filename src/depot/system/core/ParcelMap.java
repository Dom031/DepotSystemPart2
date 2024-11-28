package depot.system.core;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ParcelMap {
    private Map<String, Parcel> parcels; // Map of Parcel with ID -> Parcel
    private List<Parcel> collectedParcels; // Separate list for collected parcels

    //constructor
    public ParcelMap(){
        this.parcels = new HashMap<>();
        this.collectedParcels = new ArrayList<>();

    }
    //getter
    public Map<String, Parcel> getParcels(){
        return parcels;
    }

    // Add a collected parcel
    public void addToCollectedParcels(Parcel parcel) {
        collectedParcels.add(parcel);
    }

    // Getter for collected parcels
    public List<Parcel> getCollectedParcels() {
        return collectedParcels;
    }

    public void saveCollectedParcelsToFile(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Parcel parcel : getCollectedParcels()){
                writer.write(parcel.toString());
                writer.newLine();
            }
            System.out.println("Collected parcels saved to: " + filePath);
        } catch (IOException e){
            System.out.println("Error saving collected parcels to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return parcels.toString();
    }

}


