package depot.system.core;

// Class for the Worker in the Depot System Assignment 2.
public class Worker {
    private final String name;

    // Constructor with validation
    public Worker(String workerID, String name) {
        if (workerID == null || workerID.trim().isEmpty()) {
            throw new IllegalArgumentException("Worker ID cannot be null or empty.");
        }
        if (!workerID.matches("W\\d+")) { // Example: Worker ID should start with 'W' followed by digits
            throw new IllegalArgumentException("Worker ID must start with 'W' followed by digits.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void processCustomer(Customer customer, ParcelMap parcelMap, Log log) {
        String parcelID = customer.getParcelID();
        Parcel parcel = parcelMap.getParcels().get(parcelID);

        if (parcel != null) {
            parcel.setStatus("Collected");
            double fee = calculateFee(parcel);
            log.addLogEntry("Parcel: " + parcelID + " collected by " + customer.getName() + " Fee: £ " + fee);

            parcelMap.addToCollectedParcels(parcel);
        }
    }

        // Calculate fee for a parcel
        public double calculateFee(Parcel parcel) {
            double baseFee = 5.0; // Base fee
            double weightFee = parcel.getWeight() * 0.5;
            double storageFee = parcel.getDaysInDepot() * 0.2;
            double totalFee = baseFee + weightFee + storageFee;
            return parcel.applyDiscount(totalFee);
        }


    }

