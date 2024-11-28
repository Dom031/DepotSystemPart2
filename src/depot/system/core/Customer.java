package depot.system.core;

//Class for the Customer in the Depot System Assignment 2.

public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelID;

    //constructor for Customer class with validation
    public Customer (int sequenceNumber, String name, String parcelID){
        if (sequenceNumber <= 0) {
            throw new IllegalArgumentException("Sequence number must be positive.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (parcelID == null || parcelID.trim().isEmpty()) {
            throw new IllegalArgumentException("Parcel ID cannot be null or empty.");
        }
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    //getters and setters
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public String getParcelID() {
        return parcelID;
    }

    public void setSequenceNumber(int sequenceNumber) {
        if (sequenceNumber < 0) {
            throw new IllegalArgumentException("Sequence number cannot be negative.");
        }
        this.sequenceNumber = sequenceNumber;
    }


    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setParcelID(String parcelID) {
        if (parcelID == null || parcelID.trim().isEmpty()) {
            throw new IllegalArgumentException("Parcel ID cannot be null or empty.");
        }
        this.parcelID = parcelID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "sequenceNumber=" + sequenceNumber +
                ", name='" + name + '\'' +
                ", parcelID='" + parcelID + '\'' +
                '}';
    }

}
