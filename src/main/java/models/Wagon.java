package models;

public abstract class Wagon {
    protected int id;                 // some unique ID of a Wagon
    private Wagon nextWagon;        // another wagon that is appended at the tail of this wagon
                                    // a.k.a. the successor of this wagon in a sequence
                                    // set to null if no successor is connected
    private Wagon previousWagon;    // another wagon that is prepended at the front of this wagon
                                    // a.k.a. the predecessor of this wagon in a sequence
                                    // set to null if no predecessor is connected


    // representation invariant propositions:
    // tail-connection-invariant:   wagon.nextWagon == null or wagon == wagon.nextWagon.previousWagon
    // front-connection-invariant:  wagon.previousWagon == null or wagon = wagon.previousWagon.nextWagon

    public int getId() {
        return id;
    }

    public Wagon getNextWagon() {
        return this.nextWagon;
    }

    public Wagon getPreviousWagon() {
        return this.previousWagon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNextWagon(Wagon nextWagon) {
        this.nextWagon = nextWagon;
    }

    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    /**
     * @return  whether this wagon has a wagon appended at the tail
     */
    public boolean hasNextWagon() {
        if (this.nextWagon != null){
            return true;
        }
        return false;
    }

    /**
     * @return  whether this wagon has a wagon prepended at the front
     */
    public boolean hasPreviousWagon() {
        if (this.previousWagon != null){
            return true;
        }
        return false;
    }

    /**
     * finds the last wagon of the sequence of wagons attached to this wagon
     * if no wagons are attached return this wagon itselves
     * @return  the wagon found
     */
    public Wagon getLastWagonAttached() {
        if (this.hasNextWagon()) {
            return this.nextWagon.getLastWagonAttached();
        } else {
            return this;
        }
    }

    /**
     * @return  the length of the sequence of wagons starting with this wagon
     *          return 1 if no wagons have been attached to this wagon.
     */
    public int getSequenceLength() {
        int length = 1;
        Wagon temp = this.nextWagon;
        if(!this.hasNextWagon()){
            return 1;
        }
        while(this.hasNextWagon()){
           this.nextWagon = this.nextWagon.getNextWagon();
            length++;
        }
        this.nextWagon = temp;
        return length;
    }

    /**
     * attaches this wagon at the tail of a given prevWagon.
     * @param newPreviousWagon
     * @throws RuntimeException if this wagon already has been appended to a wagon.
     * @throws RuntimeException if prevWagon already has got a wagon appended.
     */
    public void attachTo(Wagon newPreviousWagon) {
        if(this.hasPreviousWagon() || newPreviousWagon.hasNextWagon()){
            throw new RuntimeException();
        }
        newPreviousWagon.setNextWagon(this);
        this.setPreviousWagon(newPreviousWagon);
    }




    /**
     * detaches this wagon from its previous wagons.
     * no action if this wagon has no previous wagon attached.
     */
    public void detachFromPrevious() {
        if (this.hasPreviousWagon()){
        this.previousWagon.setNextWagon(null);
        this.setPreviousWagon(null);}

    }

    /**
     * detaches this wagon from its tail wagons.
     * no action if this wagon has no succeeding next wagon attached.
     */
    public void detachTail() {
       Wagon wagon = this.nextWagon;
        this.setNextWagon(null);
        wagon.setPreviousWagon(null);
    }

    /**
     * attaches this wagon at the tail of a given newPreviousWagon.
     * if required, first detaches this wagon from its current predecessor
     * and/or detaches the newPreviousWagon from its current successor
     * @param newPreviousWagon
     */
    public void reAttachTo(Wagon newPreviousWagon) {
        if(this.hasPreviousWagon()){
        detachFromPrevious();}
        if(newPreviousWagon.hasNextWagon()){
            newPreviousWagon.nextWagon.detachFromPrevious();
        }
        attachTo(newPreviousWagon);
    }

    /**
     * Removes this wagon from the sequence that it is part of, if any.
     * Reconnect the subsequence of its predecessors with the subsequence of its successors, if any.
     */
    public void removeFromSequence() {
        Wagon prev = this.previousWagon;
        Wagon next = this.nextWagon;
       if(this.hasNextWagon() && !this.hasPreviousWagon()){
           Wagon wagon = this.nextWagon;
           this.setNextWagon(null);
           wagon.setPreviousWagon(null);
       }

        else if(!this.hasNextWagon() && this.hasPreviousWagon()){
            this.previousWagon.setNextWagon(null);
            this.setNextWagon(null);
            this.setPreviousWagon(null);
      }else {

        this.previousWagon.setNextWagon(next);
        this.nextWagon.setPreviousWagon(prev);
        this.setNextWagon(null);
        this.setPreviousWagon(null);}

    }


    /**
     * reverses the order in the sequence of wagons from this Wagon until its final successor.
     * The reversed sequence is attached again to the predecessor of this Wagon, if any.
     * no action if this Wagon has no succeeding next wagon attached.
     * @return the new start Wagon of the reversed sequence (with is the former last Wagon of the original sequence)
     */
    public Wagon reverseSequence() {
        // TODO provide a recursive implementation

        Wagon temp = this.nextWagon;
        this.setNextWagon(this.previousWagon);
        this.setPreviousWagon(temp);

        while (this.previousWagon != null){
            return reverseSequence();
        }

        return  this;
    }


}
