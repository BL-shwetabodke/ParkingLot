package parkinglotsystem;

public class ParkingLotSystem {
    private final int capacity;
    private int currenctCapacity = 0;

    private Object vehicle;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (this.capacity == currenctCapacity)
            throw new ParkingLotException("PARKING CAPACITY IS FULL", ParkingLotException.ExceptionType.PARKING_FULL);
        this.vehicle = vehicle;
        currenctCapacity++;
    }

    public boolean isVehiclePark() throws ParkingLotException {
        if (this.vehicle != null)
            return true;
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
