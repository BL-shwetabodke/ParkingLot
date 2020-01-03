package parkinglotsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParkingLotsSystem {
    private int lotCapacity;
    private List<ParkingLot> parkingLots;

    public ParkingLotsSystem(int lotCapacity) {
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

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        List<ParkingLot> parkingLotsList = this.parkingLots;
        Collections.sort(parkingLotsList, Comparator.comparing(list -> list.getSlotList().size(), Comparator.reverseOrder()));
        ParkingLot lot = parkingLotsList.get(0);
        lot.parkVehicle(vehicle);
    }

    public boolean isVehiclePark(Object vehicle) {
        if (this.parkingLots.get(0).isVehiclePark(vehicle)){
            return true;
        }
        return false;
    }
}
