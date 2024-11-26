package depot.system;
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
    }

    // Remove a customer from the queue
    public Customer dequeueCustomer() {
        return listOfCustomer.poll(); // poll() returns null if empty
    }

    // Peek at the next customer
    public Customer peekNextCustomer() {
        return listOfCustomer.peek(); // peek() returns null if empty
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

// add methods later
}

