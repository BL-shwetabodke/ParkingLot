package parkinglotsystem;

import java.time.LocalDateTime;

public class ParkingSlot {
    private  ParkingLotStrategy driverType;
    protected LocalDateTime time;
    protected Object vehicle;

    public ParkingSlot(Object vehicle, ParkingLotStrategy driverType) {
        this.vehicle = vehicle;
        this.time = LocalDateTime.now();
        this.driverType = driverType;
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
