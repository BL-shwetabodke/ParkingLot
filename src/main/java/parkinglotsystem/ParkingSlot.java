package parkinglotsystem;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingSlot {
    private int slotNumber;
    private  Enum type;
    protected LocalDateTime time;
    protected Vehicle vehicle;

    public ParkingSlot(Vehicle vehicle, Enum driverType) {
        this.vehicle = vehicle;
        this.time = LocalDateTime.now();
        this.type = driverType;
    }

    public ParkingSlot(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return vehicle.equals(that.vehicle);
    }
/*
    @Override
    public int hashCode() {
        return Objects.hash(vehicle);
    }

    @Override
    public boolean equals(Vehicle o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return vehicle.equals(that.vehicle);
    }*/
}
