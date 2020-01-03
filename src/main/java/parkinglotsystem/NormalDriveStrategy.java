package parkinglotsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum NormalDriveStrategy implements ParkingLotStrategy {
    NORMAL;

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots)  {
        List<ParkingLot> parkingLotsList =new ArrayList<>(parkingLots);
        Collections.sort(parkingLotsList, Comparator.comparing(list -> list.getSlotList().size(), Comparator.reverseOrder()));
        return parkingLotsList.get(0);
    }
}
