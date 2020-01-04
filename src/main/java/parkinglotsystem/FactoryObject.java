package parkinglotsystem;

public class FactoryObject {

    public static ParkingLotStrategy asadadf(Enum type) {
        if (type.equals(DriverType.HANDICAP)) {
            return new HandicapDriversStrategy();
        } else if (type.equals(VehicleType.LARGE_VEHICLE)) {
            return new LargeVehicleStrategy();
        }
        return new NormalDriverStrategy();
    }
}
