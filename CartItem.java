package FinalProject;

public class CartItem {
    private BikeClasses bike;
    private int hours;

    public CartItem(BikeClasses bike, int hours) {
        this.bike = bike;
        this.hours = hours;
    }

    public BikeClasses getBike() {
        return bike; 
    }

    public int getHours() { 
        return hours; 
    }

    public void setHours(int hours) { 
        this.hours = hours; 
    }

    public double getSubtotal() { 
        return bike.getHourlyRate() * hours; 
    }
}
