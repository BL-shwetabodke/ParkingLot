package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLotSystem;
import parkinglotsystem.ParkingOwner;

public class ParkinglotOwnerTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.registerParkingLotObserver(parkingOwner);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = parkingOwner.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformOwner() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.registerParkingLotObserver(parkingOwner);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        try {
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }
        boolean parkingAvailable = parkingOwner.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }
}
