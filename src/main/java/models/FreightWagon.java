package models;

public class FreightWagon extends Wagon {

    private int maxWeight;

    public FreightWagon(int wagonId, int maxWeight) {
       this.id = wagonId;
       this.maxWeight = maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    @Override
    public String toString() {
        return "[Wagon-"+ id +"]";
    }
}
