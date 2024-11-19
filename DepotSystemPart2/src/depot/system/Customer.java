package depot.system;

//Class for the Customer in the Depot System Assignment 2.

public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelID;

    //constructor

    public Customer (int sequenceNumber, String name, String parcelID){
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
        this.sequenceNumber = sequenceNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    //methods to be added later
}
