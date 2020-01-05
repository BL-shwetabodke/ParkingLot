package parkinglotsystem;

public class Vehicle {
    private String numberPlate;
    private String modeleName;
    private String color;

    public Vehicle(String color) {
        this.color = color;
    }

    public Vehicle(String color, String modeleName, String numberPlate) {
        this.color=color;
        this.numberPlate=numberPlate;
        this.modeleName=modeleName;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getModeleName() {
        return modeleName;
    }

    public String getColor() {
        return color;
    }
}
