package models;

import java.util.Iterator;
import java.util.List;
public class Train  implements Iterable<Wagon>{
    private String origin;
    private String destination;
    private Locomotive engine;
    private Wagon firstWagon;
    public List<Wagon> trainWagonsList;

    /* Representation invariants:
        firstWagon == null || firstWagon.previousWagon == null
        engine != null
     */

    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setEngine(Locomotive engine) {
        this.engine = engine;
    }

    /* three helper methods that are usefull in other methods */
    public boolean hasWagons() {
        return (this.firstWagon != null);
    }

    public boolean isPassengerTrain() {
        System.out.println("Next wagon : " + this.firstWagon);

        return this.firstWagon instanceof PassengerWagon;
    }

    public boolean isFreightTrain() {
       return this.firstWagon instanceof FreightWagon;
    }

    public Locomotive getEngine() {
        return this.engine;
    }

    public Wagon getFirstWagon() {
        return this.firstWagon;
    }

    /**
     * Replaces the current sequence of wagons (if any) in the train
     * by the given new sequence of wagons (if any)
     * (sustaining all representation invariants)
     * @param newSequence   the new sequence of wagons (can be null)
     */
    public void setFirstWagon(Wagon newSequence) {
        this.firstWagon = newSequence;
    }

    /**
     * @return  the number of Wagons connected to the train
     */
    public int getNumberOfWagons() {
        int length=0;
        for (Wagon wagon :this){
            length++;
        }
        return length-1;
    }

    /**
     * @return  the last wagon attached to the train
     */
    public Wagon getLastWagonAttached() {

        return null;
    }

    /**
     * @return  the total number of seats on a passenger train
     *          (return 0 for a freight train)
     */
    public int getTotalNumberOfSeats() {
        int availableSeats = 0;
        if(firstWagon instanceof PassengerWagon){
            for (Wagon wagon : this) {
                availableSeats += ((PassengerWagon) wagon).getNumberOfSeats();
            }
        }
        return availableSeats;
    }

    /**
     * calculates the total maximum weight of a freight train
     * @return  the total maximum weight of a freight train
     *          (return 0 for a passenger train)
     *
     */
    public int getTotalMaxWeight() {
        int availableWeight= 0;
        if(firstWagon instanceof PassengerWagon){
            for(Wagon wagon :this){
                availableWeight += ((FreightWagon) wagon).getMaxWeight();
            }
        }
        return availableWeight;
    }

     /**
     * Finds the wagon at the given position (starting at 1 for the first wagon of the train)
     * @param position
     * @return  the wagon found at the given position
     *          (return null if the position is not valid for this train)
     */
    public Wagon findWagonAtPosition(int position) {
        Wagon currentWagon = firstWagon;
        int numberOfWagonsFound = 1;
        if(firstWagon == null) return null;
        for (int i = 1; i < position; i++) {
            if (currentWagon.hasNextWagon()) {
                currentWagon = currentWagon.getNextWagon();
                numberOfWagonsFound++;
            }
        }
        if (numberOfWagonsFound == position) {
            return currentWagon;
        }
        return null;
    }

    /**
     * Finds the wagon with a given wagonId
     * @param wagonId
     * @return  the wagon found
     *          (return null if no wagon was found with the given wagonId)
     */
    public Wagon findWagonById(int wagonId) {
        Wagon wagon = firstWagon;
       while(wagon !=null){
           if (wagon.getId() == wagonId) return wagon;
           else wagon = wagon.getNextWagon();
       }
        return null;
    }

    /**
     * Determines if the given sequence of wagons can be attached to the train
     * Verfies of the type of wagons match the type of train (Passenger or Freight)
     * Verfies that the capacity of the engine is sufficient to pull the additional wagons
     * @param sequence
     * @return
     */
    public boolean canAttach(Wagon sequence) {
        // TODO

        return false;
    }

    /**
     * Tries to attach the given sequence of wagons to the rear of the train
     * No change is made if the attachment cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * @param sequence
     * @return  whether the attachment could be completed successfully
     */
    public boolean attachToRear(Wagon sequence) {
        if (this.firstWagon !=null) {
            Wagon w = this.firstWagon;
            while(w.hasNextWagon()){
                w = w.getNextWagon();
            }
            w.attachTo(sequence);
        }
        this.firstWagon = sequence;
       return true;
    }

    /**
     * Tries to insert the given sequence of wagons at the front of the train
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible or the engine has insufficient capacity)
     * @param sequence
     * @return  whether the insertion could be completed successfully
     */
    public boolean insertAtFront(Wagon sequence) {
        // TODO

        return false;
    }

    /**
     * Tries to insert the given sequence of wagons at the given wagon position in the train
     * No change is made if the insertion cannot be made.
     * (when the sequence is not compatible of the engine has insufficient capacity
     * or the given position is not valid in this train)
     * @param sequence
     * @return  whether the insertion could be completed successfully
     */
    public boolean insertAtPosition(int position, Wagon sequence) {
        // TODO

        return false;
    }

    /**
     * Tries to remove one Wagon with the given wagonId from this train
     * and attach it at the rear of the given toTrain
     * No change is made if the removal or attachment cannot be made
     * (when the wagon cannot be found, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     * @param wagonId
     * @param toTrain
     * @return  whether the move could be completed successfully
     */
    public boolean moveOneWagon(int wagonId, Train toTrain) {
        // TODO

        return false;
     }

    /**
     * Tries to split this train and move the complete sequence of wagons from the given position
     * to the rear of toTrain
     * No change is made if the split or re-attachment cannot be made
     * (when the position is not valid for this train, or the trains are not compatible
     * or the engine of toTrain has insufficient capacity)
     * @param position
     * @param toTrain
     * @return  whether the move could be completed successfully
     */
    public boolean splitAtPosition(int position, Train toTrain) {
        // TODO

        return false;
    }

    /**
     * Reverses the sequence of wagons in this train (if any)
     * i.e. the last wagon becomes the first wagon
     *      the previous wagon of the last wagon becomes the second wagon
     *      etc.
     * (No change if the train has no wagons or only one wagon)
     */
    public void reverse() {
        // TODO

    }


    @Override
    public Iterator<Wagon> iterator() {
        return trainWagonsList.iterator();
    }

    // TODO
}
