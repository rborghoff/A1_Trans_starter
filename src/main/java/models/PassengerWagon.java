package models;
public class PassengerWagon extends Wagon{
    private int numberOfSeats;

    public PassengerWagon(int wagonId, int numberOfSeats) {
        this.id = wagonId;
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "[Wagon-"+ id +"]";
    }
}
