package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int capacity;
    ParkingOwner parkingOwner = new ParkingOwner();
    List vehicles;
    private Object vehicle;
    private ParkingOwner owner;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList();
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.capacity) {
            owner.parkingFull();
            throw new ParkingLotException("PARKING IS FULL", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        }
        if (isVehiclePark(vehicle)) {
            throw new ParkingLotException("VEHICLE ALREADY PARK", ParkingLotException.ExceptionType.PARKING_FULL);
        }
        this.vehicles.add(vehicle);
    }

    public boolean isVehiclePark(Object vehicle) throws ParkingLotException {
        if (this.vehicles.contains(vehicle))
            return true;
        return false;
    }

    public boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicles.contains(vehicle)) {
            this.vehicles.remove(vehicle);
            return true;
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public void registerOwner(ParkingOwner parkingOwner) {
        this.owner = parkingOwner;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
