package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.*;

public class ParkinglotOwnerTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            parkingLot.parkVehicle(new Object(), NormalDriveStrategy.NORMAL);
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
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            parkingLot.parkVehicle(new Object(), NormalDriveStrategy.NORMAL);
            parkingLot.parkVehicle(new Object(), NormalDriveStrategy.NORMAL);
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
