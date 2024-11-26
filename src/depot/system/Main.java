package depot.system;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Depot System - Assignment Part 2");

        // Initialize required objects
        QueueOfCustomer customerQueue = new QueueOfCustomer();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance();
        Manager manager = new Manager(customerQueue, parcelMap, log);

        //open csv file
        String customersFilePath = "resources/Custs.csv";
        String parcelFilePath = "resources/Parcels.csv";

        manager.readCustomers(customersFilePath);
        for (Customer customer : customerQueue.getListOfCustomer()){
            System.out.println("Customer in queue " + customer);
        }

        while (!customerQueue.isEmpty()){
            Customer processedCustomer = customerQueue.dequeueCustomer();
            System.out.println("Processed customer: " + processedCustomer );
        }
        System.out.println("Queue is empty: " + customerQueue.isEmpty());

    }
}