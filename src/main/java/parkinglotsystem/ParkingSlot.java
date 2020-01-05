package parkinglotsystem;

import java.time.LocalDateTime;

public class ParkingSlot {
    private int slot;
    private Enum type;
    protected LocalDateTime time;
    protected Vehicle vehicle;
    protected String attendantName;

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ParkingSlot(Vehicle vehicle, Enum driverType, String parkingAttendantName) {
        this.vehicle = vehicle;
        this.time = LocalDateTime.now();
        this.type = driverType;
        this.attendantName = parkingAttendantName;
    }

    public ParkingSlot(int slot) {
        this.slot=slot;
    }

    public int getSlot() {
        return slot;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getAttendantName() {
        return attendantName;
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
}
