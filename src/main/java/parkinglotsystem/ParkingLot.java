package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLot {
    private int capacity;
    private List<ParkingSlot> vehicles;
    private List<ParkingLotObserver> parkingObservers;
    private int vehicleCount;

    public ParkingLot() {
        this.parkingObservers = new ArrayList<>();
        this.vehicles = new ArrayList();
    }

    public ParkingLot(int capacity) {
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

    public void parkVehicle(Vehicle vehicle, Enum driverType, String attendantName) throws ParkingLotException {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle, driverType, attendantName);
        if (isVehiclePark(vehicle)) {
            throw new ParkingLotException("VEHICLE ALREADY PARK", ParkingLotException.ExceptionType.PARKING_FULL);
        }
        int emptySlot = getParkingSlot();
        parkingSlot.setSlot(emptySlot);
        this.vehicles.set(emptySlot, parkingSlot);
        vehicleCount++;
    }

    public boolean isVehiclePark(Vehicle vehicle) {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        if (this.vehicles.contains(parkingSlot))
            return true;
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) throws ParkingLotException {
        boolean present = this.vehicles.stream()
                .filter(slot -> (vehicle) == slot.getVehicle())
                .findFirst()
                .isPresent();
        for (ParkingLotObserver observer : parkingObservers) {
            observer.parkingAvailable();
        }
        if (present)
            return true;
        return false;
    }

    public int initializeParkingLot() {
        IntStream.range(0, this.capacity).forEach(slots -> this.vehicles.add(new ParkingSlot(slots)));
        return vehicles.size();
    }

    public ArrayList getSlotList() {
        ArrayList<Integer> emptySlots = new ArrayList();
        IntStream.range(0, capacity)
                .filter(slot -> this.vehicles.get(slot).getVehicle() == null)
                .forEach(slot -> emptySlots.add(slot));
        if (emptySlots.size() == 0) {
            for (ParkingLotObserver observer : parkingObservers) {
                observer.parkingFull();
            }
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

    public int findVehicle(Vehicle vehicle) throws ParkingLotException {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        if (this.vehicles.contains(parkingSlot))
            return this.vehicles.indexOf(parkingSlot);
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public boolean isTimeSet(Vehicle vehicle) {
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        for (int i = 0; i < this.vehicles.size(); i++) {
            if (this.vehicles.get(i).time != null && this.vehicles.contains(parkingSlot))
                return true;
        }
        return false;
    }

    public int getParkVehicleCount() {
        return vehicleCount;
    }

    public List<Integer> findOnField(String fieldName) {
        List<Integer> whiteVehicleList = new ArrayList<>();
        whiteVehicleList = this.vehicles.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle() != null)
                .filter(parkingSlot -> parkingSlot.getVehicle().getColor().equals(fieldName))
                .map(parkingSlot -> parkingSlot.getSlot())
                .collect(Collectors.toList());
        return whiteVehicleList;
    }

    public List<String> findParkedToyatoVehicleDetails(String color, String modelName) {
        List<String> fieldList = new ArrayList<>();
        List<String> fieldList1 = new ArrayList<>();
        fieldList = this.vehicles.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle() != null)
                .filter(parkingSlot -> parkingSlot.getVehicle().getModelName().equals(modelName))
                .filter(parkingSlot -> parkingSlot.getVehicle().getColor().equals(color))
                .map(parkingSlot -> (parkingSlot.getAttendantName())+"  "+(parkingSlot.getSlot())+"  "+(parkingSlot.vehicle.getNumberPlate()))
                .collect(Collectors.toList());
        return fieldList;
    }

    public List<Integer> findParkedBMWVehicleDetails(String modelName) {
        List<Integer> whiteVehicleList = new ArrayList<>();
        whiteVehicleList = this.vehicles.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle() != null)
                .filter(parkingSlot -> parkingSlot.getVehicle().getModelName().equals(modelName))
                .map(parkingSlot -> parkingSlot.getSlot())
                .collect(Collectors.toList());
        return whiteVehicleList;
    }
}
