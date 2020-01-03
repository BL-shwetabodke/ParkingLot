package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {
    private int capacity;
    private List<ParkingSlot> vehicles;
    private List<ParkingLotObserver> parkingObservers;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingObservers = new ArrayList<>();
        this.vehicles = new ArrayList();
    }

    public ParkingLot() {
    }

    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.parkingObservers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        if (!this.vehicles.contains(null)) {
            for (ParkingLotObserver observer : parkingObservers) {
                observer.parkingFull();
            }
            throw new ParkingLotException("PARKING IS FULL", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
        }
        if (isVehiclePark(vehicle)) {
            throw new ParkingLotException("VEHICLE ALREADY PARK", ParkingLotException.ExceptionType.PARKING_FULL);
        }
        int emptySlot = getParkingSlot();
        this.vehicles.set(emptySlot, parkingSlot);
    }

    public boolean isVehiclePark(Object vehicle) {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        if (this.vehicles.contains(parkingSlot))
            return true;
        return false;
    }

    public boolean unParkVehicle(Object vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        for (int slotnumber = 0; slotnumber < this.vehicles.size(); slotnumber++) {
            if (this.vehicles.contains(parkingSlot)) {
                this.vehicles.set(slotnumber, null);
                for (ParkingLotObserver observer : parkingObservers) {
                    observer.parkingAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public int initializeParkingLot() {
        IntStream.range(0, this.capacity).forEach(slots -> vehicles.add(null));
        return vehicles.size();
    }

    public ArrayList getSlotList() {
        ArrayList emptySlots = new ArrayList();
        for (int slot = 0; slot < this.capacity; slot++) {
            if (this.vehicles.get(slot) == null)
                emptySlots.add(slot);
        }
        return emptySlots;
    }

    public int getParkingSlot() throws ParkingLotException {
        ArrayList<Integer> emptySlotList = getSlotList();
        for (int slot = 0; slot < emptySlotList.size(); slot++) {
            if (emptySlotList.get(0) == (slot)) {
                return slot;
            }
        }
        throw new ParkingLotException("PARKING IS FULL", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public int findVehicle(Object vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        if (this.vehicles.contains(parkingSlot))
            return this.vehicles.indexOf(parkingSlot);
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean isTimeSet(Object vehicle) {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).time != null && this.vehicles.contains(parkingSlot))
                return true;
        }
        return false;
    }
}
