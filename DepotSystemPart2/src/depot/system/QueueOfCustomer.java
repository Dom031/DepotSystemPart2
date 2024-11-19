package depot.system;

import java.util.Queue;
import java.util.LinkedList;
public class QueueOfCustomer {
    private Queue<Customer> listOfCustomer;

    //constructor
    public QueueOfCustomer(){
        this.listOfCustomer = new LinkedList<>();
    }

    //getters and setters, I'm unsure if I need a setter since the queue will be managed via other methods
    //but for consistency I am adding it for now, might change later.
    public Queue<Customer> getListOfCustomer() {
        return listOfCustomer;
    }

    public void setListOfCustomer(Queue<Customer> listOfCustomer) {
        this.listOfCustomer = listOfCustomer;
    }
// add methods later
}



