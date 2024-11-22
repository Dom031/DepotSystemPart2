package depot.system;



public class Manager {
    //References to customer queue, parcel map and log instance.
    private QueueOfCustomer customerQueue;
    private ParcelMap parcelMap;
    private Log log;

    //constructor
    public Manager(QueueOfCustomer customerQueue, ParcelMap parcelMap, Log log){
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
    }

    //getters
    public QueueOfCustomer getCustomerQueue(){ return customerQueue;}
    public ParcelMap getParcelMap(){return parcelMap;}
    public Log getLog(){return log;}


    //methods to be added 
}
