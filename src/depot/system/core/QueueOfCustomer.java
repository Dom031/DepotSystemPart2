package depot.system.core;
import java.util.Queue;
import java.util.LinkedList;


public class QueueOfCustomer {
    private final Queue<Customer> listOfCustomer;

    //constructor
    public QueueOfCustomer(){
        this.listOfCustomer = new LinkedList<>();
    }

    //getter for the queue
    public Queue<Customer> getListOfCustomer() {
        return listOfCustomer;
    }

    // Add a customer to the queue
    public void enqueueCustomer(Customer customer) {
        listOfCustomer.add(customer);
        //System.out.println("Customer added: " + customer.getName());
    }

    // Remove a customer from the queue
    public Customer dequeueCustomer() {
        if (!listOfCustomer.isEmpty()){
            Customer removedCustomer = listOfCustomer.poll();
            System.out.println("Customer processed: " + removedCustomer.getName());
            return removedCustomer;
        } else {
            System.out.println("Queue is empty. ");
            return null;
        }
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return listOfCustomer.isEmpty();
    }

    @Override
    public String toString() {
        return "QueueOfCustomer{" +
                "listOfCustomer=" + listOfCustomer +
                '}';
    }
}

