package parkinglotsystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NormalDriveStrategy implements ParkingLotStrategy {
    NORMAL;

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws ParkingLotException {
        List<ParkingLot> parkingLotsList = parkingLots;
        Collections.sort(parkingLotsList, Comparator.comparing(list -> list.getSlotList().size(), Comparator.reverseOrder()));
        return parkingLotsList.get(0);
    }
}
