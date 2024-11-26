package depot.system;
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

    @Override
    public String toString() {
        return parcels.toString();
    }

}


