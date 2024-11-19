package depot.system;

//Class for the Worker in the Depot System Assignment 2.

public class Worker {
    private String workerID;
    private String name;

    //Constructors

    public Worker(String workerID, String name){
        this.workerID = workerID;
        this.name = name;
    }

    // Getters and Setters
    public String getWorkerID() {
        return workerID;
    }

    public String getName() {
        return name;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public void setName(String name) {
        this.name = name;
    }
    //methods to be added later
}
