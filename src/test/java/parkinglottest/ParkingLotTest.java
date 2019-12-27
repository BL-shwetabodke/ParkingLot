package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.AirportSecurity;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLotSystem;
import parkinglotsystem.ParkingOwner;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehichle_WhenUnParked_ShouldReturnTrue() {
        boolean isUnParked = false;
        try {
            parkingLotSystem.parkVehicle(vehicle);
            isUnParked = parkingLotSystem.unParkVehicle(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleParkAndWhenUnParkedAnotherVehicle_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isUnParked = parkingLotSystem.unParkVehicle(new Object());
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_CheckIfVehicleisNotPresent_ShouldRetuenThrowException() {
        try {
            parkingLotSystem.parkVehicle(null);
            boolean isParked = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
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
    public void givenCapacityIs2ShouldBeAbleToPark2Vehicle() {
        parkingLotSystem.setCapacity(2);
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked1 = parkingLotSystem.isVehiclePark(vehicle);
            parkingLotSystem.parkVehicle(vehicle2);
            boolean isParked2 = parkingLotSystem.isVehiclePark(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenSameVehiclesTwoTimes_WhenParked_ShouldThrowException() {
        parkingLotSystem.setCapacity(2);
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.registerParkingLotObserver(parkingOwner);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE ALREADY PARK", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = airportSecurity.isParkingFull();
        Assert.assertTrue(parkingFull);
    }
}
