package parkinglotsystem;

public class StrategyFactory {

    public static ParkingLotStrategy getStrategy(Enum type) {
        if (type.equals(DriverType.HANDICAP)) {
            return new HandicapDriversStrategy();
        } else if (type.equals(VehicleType.LARGE_VEHICLE)) {
            return new LargeVehicleStrategy();
        }
        return new NormalDriverStrategy();
    }
}
