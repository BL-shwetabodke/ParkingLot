package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLot;
import parkinglotsystem.ParkingOwner;

public class ParkinglotOwnerTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = parkingOwner.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformOwner() {
        ParkingLot parkingLot = new ParkingLot(1);
        Object vehicle = new Object();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.setCapacity(2);
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(new Object());
            parkingLot.parkVehicle(new Object());
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
