# Parcel Depot System

This project is part of an assignment for [Module Name], demonstrating the design and implementation of a parcel depot system.

## **Description**
The Parcel Depot System simulates the process of managing parcels in a depot, including recording, collecting, and reporting parcel statuses. The implementation adheres to a three-tier architecture and applies design patterns such as Singleton and MVC.

## **Features**
- Initialize customer and parcel data from files.
- Process customer parcel collections and update statuses.
- Calculate collection fees based on parcel attributes.
- Generate reports of collected and uncollected parcels.
- Log system events using a Singleton Log class.

## **Project Structure**
### **Packages**
- `depot.system`: Contains the main classes for the application.
  
### **Key Classes**
- `Parcel`: Represents a parcel with attributes like ID, dimensions, weight, etc.
- `Customer`: Represents a customer collecting parcels.
- `QueueofCustomer`: Manages the queue of customers.
- `ParcelMap`: Stores and retrieves parcels efficiently.
- `Log`: Singleton class for logging system events.
- `Manager`: Central coordinator for system processes.

### **Design Patterns**
- **Singleton Pattern**: Implemented in the `Log` class.
- **MVC**: Applied for structuring the GUI (to be implemented).

## **How to Run**
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/parcel-depot-system.git
   cd parcel-depot-system

