package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        parkingLotSystem.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehichle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.initializeParkingLot();
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
        parkingLotSystem.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isUnParked = parkingLotSystem.unParkVehicle(new Object());
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        parkingLotSystem.initializeParkingLot();
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
        parkingLotSystem.setCapacity(3);
        parkingLotSystem.initializeParkingLot();
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked1 = parkingLotSystem.isVehiclePark(vehicle);
            parkingLotSystem.parkVehicle(vehicle2);
            boolean isParked2 = parkingLotSystem.isVehiclePark(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSameVehiclesTwoTimes_WhenParked_ShouldThrowException() {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.initializeParkingLot();
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

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.initializeParkingLot();
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

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.initializeParkingLot();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
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
        boolean parkingAvailable = airportSecurity.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }

    @Test
    public void givenParkingLot_WhenInitialize_ShouldReturnParkingCapacity() {
        parkingLotSystem.setCapacity(10);
        int parkingLotCapacity = parkingLotSystem.initializeParkingLot();
        Assert.assertEquals(10, parkingLotCapacity);
    }

    @Test
    public void givenParkingLot_ShouldReturnAvailableSlots() {
        List expectedList = new ArrayList();
        expectedList.add(0);
        expectedList.add(1);
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.initializeParkingLot();
        ArrayList emptySlotList = parkingLotSystem.getSlotList();
        Assert.assertEquals(expectedList, emptySlotList);
    }

    @Test
    public void givenParkingLot_WhenParkWithProvidedSlot_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(10);
        parkingLotSystem.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean vehiclePark = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(vehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleFound_ShouldReturnVehicleSlot() {
        parkingLotSystem.setCapacity(10);
        parkingLotSystem.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(new Object());
            parkingLotSystem.parkVehicle(vehicle);
            int slotNumber = parkingLotSystem.findVehicle(vehicle);
            Assert.assertEquals(1, slotNumber);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleParkedIfTimeIsSet_ShouldReturnTrue() {
        parkingLotSystem.setCapacity(10);
        parkingLotSystem.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean istimeSet = parkingLotSystem.isTimeSet(vehicle);
            Assert.assertTrue(istimeSet);
        } catch (ParkingLotException e) {
        }
    }
}
