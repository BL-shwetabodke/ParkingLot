package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotSystem {
    private int lotCapacity;
    private List<ParkingLot> parkingLots;

    public ParkingLotSystem(int lotCapacity) {
        this.lotCapacity = lotCapacity;
        this.parkingLots = new ArrayList<>();
    }

    public void addLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public boolean isLotAdded(ParkingLot parkingLot) {
        if (this.parkingLots.contains(parkingLot))
            return true;
        return false;
    }

    public void parkVehicle(Vehicle vehicle, Enum type, String xyz) throws ParkingLotException {
        ParkingLotStrategy parkingLotStrategy = StrategyFactory.getStrategy(type);
        ParkingLot lot = parkingLotStrategy.getParkingLot(this.parkingLots);
        lot.parkVehicle(vehicle, type, "XYZ");
    }

    public boolean isVehiclePark(Vehicle vehicle) {
        for (int i = 0; i < this.parkingLots.size(); i++) {
            if (this.parkingLots.get(i).isVehiclePark(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) throws ParkingLotException {
        for (int parkingLot = 0; parkingLot < this.parkingLots.size(); parkingLot++) {
            if (this.parkingLots.get(parkingLot).unParkVehicle(vehicle)) {
                return true;
            }
        }
        throw new ParkingLotException("VEHICLE IS NOT AVAILABLE", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND);
    }

    public List<List<Integer>> findVehicleByField(String fieldName) {
        List<List<Integer>> listOfLotsWithWhiteVehicles = this.parkingLots.stream()
                .map(lot -> lot.findOnField(fieldName))
                .collect(Collectors.toList());
        return  listOfLotsWithWhiteVehicles;
    }

    public List<List<String>> findVehicleByNumberPlate(String color, String modelName) {
        List<List<String>> parkingLotsList = new ArrayList<>();
        for (ParkingLot list : this.parkingLots) {
            List<String> onField = list.findParkedToyatoVehicleDetails(color,modelName);
            parkingLotsList.add(onField);
        }
        return parkingLotsList;
    }

    public List<List<Integer>> findBMWVehicle(String fieldName) {
        List<List<Integer>> listOfLotsWithWhiteVehicles = this.parkingLots.stream()
                .map(lot -> lot.findParkedBMWVehicleDetails(fieldName))
                .collect(Collectors.toList());
        return  listOfLotsWithWhiteVehicles;
    }

}
