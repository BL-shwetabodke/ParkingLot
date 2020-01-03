package parkinglotsystem;

import java.util.List;

public enum HandicapDriverStrategy implements ParkingLotStrategy {
    HANDICAP;

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) throws ParkingLotException {
        ParkingLot parkingLot1 = parkingLots.stream()
                .filter(parkingLot -> parkingLot.getSlotList().size() > 0)
                .findFirst()
                .orElseThrow(() -> new ParkingLotException("PARKING IS FULL", ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND));
        return parkingLot1;
    }
}
