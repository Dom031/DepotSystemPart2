package depot.system.core;

//Class for Parcel for the Depot System assignment 2.

public class Parcel {
    private String parcelID;
    private String dimensions; // length x width x height
    private double weight; //kgs
    private int daysInDepot;
    private String status; // waiting or collected

    //constructor

    public Parcel(String parcelID, String dimensions, double weight, int daysInDepot, String status) {
        if (parcelID == null || parcelID.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        if (dimensions == null || validateDimensions(dimensions)) {
            throw new IllegalArgumentException("Invalid dimensions. Use the format 'length x width x height'.");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Parcel can't have a negative weight");
        }
        if (daysInDepot < 0) {
            throw new IllegalArgumentException("Days in depot can't be negative");
        }
        if (!status.equals("Waiting") && !status.equals("Collected")) {
            throw new IllegalArgumentException("Status must be 'Waiting' or 'Collected'");
        }
        this.parcelID = parcelID;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }


    //getters and setters

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        if (parcelID == null || parcelID.trim().isEmpty()){
            throw new IllegalArgumentException("ID Cannot be empty");
        }
        this.parcelID = parcelID;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        if (validateDimensions(dimensions)){
            throw new IllegalArgumentException(("Dimensions must be in the format 'Length x Width x Height."));
        }
        this.dimensions = dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0){
            throw new IllegalArgumentException(("Parcel can't have a negative weight"));
        }
        this.weight = weight;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        if (daysInDepot < 0){
            throw new IllegalArgumentException(("Days in depot can't be negative"));
        }
        this.daysInDepot = daysInDepot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!status.equals("Waiting") && !status.equals("Collected")){
            throw new IllegalArgumentException("Status must be 'Waiting' or 'Collected'");
        }
        this.status = status;
    }

    // Helper method to validate dimensions
    private boolean validateDimensions(String dimensions) {
        return !dimensions.matches("\\d+ x \\d+ x \\d+");
    }
    // Override toString for debugging
    @Override
    public String toString() {
        return "Parcel{" +
                "parcelID='" + parcelID + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", weight=" + weight +
                ", daysInDepot=" + daysInDepot +
                ", status='" + status + '\'' +
                '}';
    }

    public double applyDiscount(double fee) {
        if (daysInDepot < 2){
            double discount = fee *0.10; //10% discount if customers pick parcel fast
            return fee - discount;
        }
        return fee;
    }
}

