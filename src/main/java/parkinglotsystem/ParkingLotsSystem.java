package parkinglotsystem;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotsSystem {
    private int lotCapacity;
    private List<ParkingLot> parkingLots;

    public ParkingLotsSystem(int lotCapacity) {
        this.lotCapacity= lotCapacity;
        parkingLots = new ArrayList<>();
    }

    public void addLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }


    public boolean isLotAdded(ParkingLot parkingLot) {
        if (this.parkingLots.contains(parkingLot))
            return true;
        return false;
    }

    public boolean parkVehicle(Object vehicle, ParkingLot parkingLot1) throws ParkingLotException {
       parkingLot1.setCapacity(10);
       parkingLot1.initializeParkingLot();
        if (parkingLots.contains(parkingLot1)){
            parkingLot1.parkVehicle(vehicle);
            return true;
        }
        return false;
    }
}
