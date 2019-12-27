package parkinglotsystem;

public class ParkingLotSystem {
    private final int capacity;
    ParkingOwner parkingOwner = new ParkingOwner();
    private int currenctCapacity = 0;
    private Object vehicle;
    private ParkingOwner owner;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (this.capacity == currenctCapacity)
            owner.parkingFull();
        this.vehicle = vehicle;
        currenctCapacity++;
    }

    public boolean isVehiclePark(Object vehicle) throws ParkingLotException {
        if (this.vehicle.equals(vehicle))
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

    public void registerOwner(ParkingOwner parkingOwner) {
        this.owner = parkingOwner;
    }
}
