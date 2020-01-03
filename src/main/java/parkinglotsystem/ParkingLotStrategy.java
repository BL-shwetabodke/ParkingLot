package parkinglotsystem;

import java.util.List;

public interface ParkingLotStrategy {
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws ParkingLotException;
}
