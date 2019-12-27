package parkinglotsystem;

public class ParkingLotSystem {

    private Object vehicle;

    public boolean parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
        return true;
    }

    public boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
