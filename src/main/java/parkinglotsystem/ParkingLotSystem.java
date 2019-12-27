package parkinglotsystem;

public class ParkingLotSystem {

    private Object vehicle;

    public boolean parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }

    public boolean unParkVehicle(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }
}
