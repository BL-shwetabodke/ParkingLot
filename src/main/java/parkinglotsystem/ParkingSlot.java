package parkinglotsystem;

import java.time.LocalDateTime;

public class ParkingSlot {
    private  Enum type;
    protected LocalDateTime time;
    protected Object vehicle;

    public ParkingSlot(Object vehicle, Enum type) {
        this.vehicle = vehicle;
        this.time = LocalDateTime.now();
        this.type = type;
    }

    public ParkingSlot(Object vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return vehicle.equals(that.vehicle);
    }
}
