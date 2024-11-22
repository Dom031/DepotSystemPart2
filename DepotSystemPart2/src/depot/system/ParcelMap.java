package depot.system;
import java.util.HashMap;
import java.util.Map;


public class ParcelMap {
    private Map<String, Parcel> parcels; // Map of Parcel with ID -> Parcel
    //constructor
    public ParcelMap(){
        this.parcels = new HashMap<>();
    }
    //getter
    public Map<String, Parcel> getParcels(){
        return parcels;
    }
    //methods to be added later
}


