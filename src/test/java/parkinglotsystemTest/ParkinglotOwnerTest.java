package parkinglotsystemTest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.*;

public class ParkinglotOwnerTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle("black");
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL);
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = parkingOwner.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformOwner() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.setCapacity(3);
        parkingLot.initializeParkingLot();
        Vehicle vehicle = new Vehicle("black");
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL);
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL);
        } catch (ParkingLotException e) {
        }
        try {
            parkingLot.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }
        boolean parkingAvailable = parkingOwner.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }
}
