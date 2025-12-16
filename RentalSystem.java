package FinalProject;

public class RentalSystem {
    private BikeClasses[] bikes;
    private boolean[] rented;

    public RentalSystem() {
        bikes = new BikeClasses[] {
            new MountainBike(),
            new DirtBike(),
            new Scooter(),
            new FrenchBike(),
            new BMX(),
            new FoldingBike()
        };

        rented = new boolean[bikes.length];
    }

    public BikeClasses[] getBikes() {
        return bikes;
    }

    public boolean isRented(int index) {
        return rented[index];
    }

    public void rentBike(int index) {
        rented[index] = true;
    }

    public void returnBike(int index) {
        rented[index] = false;
    }

    public double calculateTotal(int index, int hours) {
        return bikes[index].getHourlyRate() * hours;
    }

    // runs the entire program
    public static void main(String[] args) {
        new RentalGUI();
    }

}
