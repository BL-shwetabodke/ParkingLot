package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private int capacity;
    private ParkingOwner parkingOwner = new ParkingOwner();
    private List vehicles;
    private List<ParkingLotObserver> parkingObservers;
    private Object vehicle;
    private ParkingOwner owner;

    public ParkingLotSystem(int capacity) {
        this.capacity = capacity;
        this.parkingObservers = new ArrayList<>();
        this.vehicles = new ArrayList();
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.parkingObservers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (this.vehicles.size() == this.capacity) {
            for (ParkingLotObserver observer : parkingObservers) {
                observer.parkingFull();
            }
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
            for (ParkingLotObserver observer : parkingObservers) {
                observer.parkingAvailable();
            }
            return true;
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }
}
