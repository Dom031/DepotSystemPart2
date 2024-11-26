package depot.system;


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

    // TODO methods to be added

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
