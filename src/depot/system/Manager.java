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



    public void processCustomer(Customer customer){
        String parcelID = customer.getParcelID();
        Parcel parcel = parcelMap.getParcels().get(parcelID);

       if(parcel != null) {
           parcel.setStatus("Collected");
           double fee = calculateFee(parcel);
           log.addLogEntry("Parcel: " + parcelID + " collected by " + customer.getName() + " Fee: £ " + fee);
       }
    }

    public void readCustomers(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) !=null) {
                String[] parts = line.split(","); //csv is split by name "," ID.

                if (parts.length == 2) { // Provided CSV has 2 parts
                    String name = parts[0].trim();
                    String parcelID = parts[1].trim();

                    // Split name into first and last name
                    String[] nameParts = name.split(" ");
                    if (nameParts.length < 2) {
                        log.addLogEntry("Skipping invalid customer name: " + name);
                        continue;
                    }
                    String firstName = nameParts[0];
                    String lastName = nameParts[1];

                    // Create a sequence number based on the queue size + 1
                    int sequenceNumber = customerQueue.getListOfCustomer().size() + 1;

                    // Create a Customer object
                    Customer customer = new Customer(sequenceNumber, firstName + " " + lastName, parcelID);

                    // Add customer to the queue
                    customerQueue.enqueueCustomer(customer);
                    log.addLogEntry("Added Customer: " + customer);
                } else {
                    log.addLogEntry("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }
    }

    public void readParcels(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 6) { // Since there are 6 columns in the sample CSV
                    String parcelID = parts[0].trim();
                    int daysInDepot = Integer.parseInt(parts[1].trim());
                    double weight = Double.parseDouble(parts[2].trim());
                    String dimensions = parts[3].trim() + " x " + parts[4].trim() + " x " + parts[5].trim();

                    // Creating a parcel object and adding it to the map, assuming every parcel is waiting for collection
                    Parcel parcel = new Parcel(parcelID, dimensions, weight, daysInDepot, "Waiting");
                    parcelMap.getParcels().put(parcelID, parcel);
                    log.addLogEntry("Added Parcel: " + parcel);

                } else {
                    // Skip lines that don't have exactly 6 parts, unnecessary for the assignment but good habit.
                    log.addLogEntry("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            log.addLogEntry("Error reading parcels file: " + e.getMessage());
        } catch (NumberFormatException e) {
            log.addLogEntry("Error parsing a number in the file: " + e.getMessage());
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
