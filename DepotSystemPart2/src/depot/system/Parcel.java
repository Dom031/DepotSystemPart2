package depot.system;

//Class for Parcel for the Depot System assignment 2.

public class Parcel {
    private String parcelID;
    private String dimensions; // length x width x height
    private double weight; //kgs
    private int daysInDepot;
    private String status; // waiting or collected

    //constructor

    public Parcel (String parcelID, String dimension, double weight, int daysInDepot, String status){
        this.parcelID = parcelID;
        this.dimensions = dimension;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    //getters and setters

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //methods, adjust later.
    public double calculateFee(){
        double baseFee = 5.0; //base for all parcel, double check assignment.
        double weightFee = weight * 0.5; //Also check assignment for details later.
        double storageFee = daysInDepot * 0.2; //once again check assignment brief to confirm any numbers here
        return baseFee + weightFee + storageFee;
    }

    public void updateStatus(String newStatus){
        if (newStatus.equals("Waiting") || newStatus.equals("Collected")){
            this.status = newStatus;
        } else {
            System.out.println("Invalid status. Use 'Waiting' or ' Collected'.");
        }

        //add discount method later!
    }

}

