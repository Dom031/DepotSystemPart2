/*
QueueOfCustomer.java
Author: Domingos Neto <dn22aau@herts.ac.uk>
Module: 6COM2013-0901-2024 - Software Architecture
Tutor: Dr. John Kanyaru
Created: 19/11/2024
Updated: 03/12/2024
*/

package depot.system.core;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Represents a queue of customers in the Depot System.
 * Provides operations to enqueue and dequeue customers,
 * as well as check the status of the queue.
 */
public class QueueOfCustomer {
    private final Queue<Customer> listOfCustomer;

    /**
     * Constructs an empty queue for managing customers.
     */
    public QueueOfCustomer() {
        this.listOfCustomer = new LinkedList<>();
    }

    /**
     * Gets the underlying queue of customers.
     *
     * @return the queue of customers.
     */
    public Queue<Customer> getListOfCustomer() {
        return listOfCustomer;
    }

    /**
     * Adds a customer to the queue.
     *
     * @param customer the customer to add; cannot be null.
     */
    public void enqueueCustomer(Customer customer) {
        listOfCustomer.add(customer);
    }

    /**
     * Removes and processes a customer from the queue.
     *
     * @return the customer that was removed, or {@code null} if the queue is empty.
     */
    public Customer dequeueCustomer() {
        if (!listOfCustomer.isEmpty()) {
            return listOfCustomer.poll();
        } else {
            System.out.println("Queue is empty.");
            return null;
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return listOfCustomer.isEmpty();
    }

    /**
     * Returns a string representation of the queue.
     *
     * @return a string in the format "QueueOfCustomer{listOfCustomer=[...]}".
     */
    @Override
    public String toString() {
        return "QueueOfCustomer{" +
                "listOfCustomer=" + listOfCustomer +
                '}';
    }
}
