package depot.system;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

// Class to manage interactions between customer queue, parcel map and log.
public class Manager {
    //References to customer queue, parcel map and log instance.
    private QueueOfCustomer customerQueue;
    private ParcelMap parcelMap;
    private Log log;

    //constructor
    public Manager(QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log){
        if (customerQueue == null || parcelMap == null || log == null){
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
    }

    //getters
    public QueueOfCustomer getCustomerQueue(){
        return customerQueue;
    }
    public ParcelMap getParcelMap(){
        return parcelMap;
    }
    public Log getLog(){
        return log;
    }

    // Calculate fee for a parcel
    public double calculateFee(Parcel parcel) {
        double baseFee = 5.0; // Base fee
        double weightFee = parcel.getWeight() * 0.5;
        double storageFee = parcel.getDaysInDepot() * 0.2;
        return baseFee + weightFee + storageFee;
    }


    public void readParcels(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debug: Print the raw line
                String[] parts = line.split(","); // Split by commas
                System.out.println("Parsed line: " + Arrays.toString(parts)); // Debug: Print the parsed parts

                if (parts.length == 6) { // Ensure there are exactly 6 parts
                    String parcelID = parts[0];
                    Parcel parcel = getParcel(parts, parcelID); // Get the parcel object
                    parcelMap.getParcels().put(parcelID, parcel); // Add to map
                    System.out.println("Added Parcel: " + parcel); // Debug: Print the added parcel
                } else {
                    System.out.println("Skipping invalid line: " + line); // Debug: Log skipped lines
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading parcels file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing a number in the file: " + e.getMessage());
        }
    }

    private static Parcel getParcel(String[] parts, String parcelID) {
        int daysInDepot = Integer.parseInt(parts[1]);
        double weight = Double.parseDouble(parts[2]);
        String part3 = parts[3].trim();
        String part4 = parts[4].trim();
        String part5 = parts[5].trim();
        String dimensions = part3 + " x " + part4 + " x " + part5;

        // Return the Parcel object
        return new Parcel(parcelID, dimensions, weight, daysInDepot, "Waiting");
    }




    // Override toString for debugging/logging
    @Override
    public String toString() {
        return "Manager{" +
                "customerQueue=" + customerQueue +
                ", parcelMap=" + parcelMap +
                ", log=" + log +
                '}';
    }
}
