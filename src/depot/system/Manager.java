package depot.system;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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



    public void readParcels(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) !=null){
                String[] parts = line.split(",");

                if (parts.length == 6){ //Since there are 6 rows on the sample CSV file
                    String parcelID = parts[0];
                    int daysInDepot = Integer.parseInt(parts[1]);
                    double weight = Double.parseDouble(parts[2]);
                    String dimensions = parts[3] + "x" + parts[4] + "x" + parts[5];

                    //Creating a parcel object and adding it to the map, assuming every parcel is waiting collection
                    Parcel parcel = new Parcel(parcelID, dimensions, weight, daysInDepot, "Waiting");
                    parcelMap.getParcels().put(parcelID, parcel);
                } else { //won't be needed here due to every line in CSV having 6 parts but good habit to have.
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading parcels file: " + e.getMessage() );;
        } catch (NumberFormatException e){
            System.out.println("Error parsing a number in the file: " + e.getMessage());
        }

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
