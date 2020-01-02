package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {

    ParkingLot parkingLot = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1);
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        parkingLot.initializeParkingLot();
        try {
            parkingLot.parkVehicle(vehicle);
            boolean isParked = parkingLot.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehichle_WhenUnParked_ShouldReturnTrue() {
        parkingLot.initializeParkingLot();
        boolean isUnParked = false;
        try {
            parkingLot.parkVehicle(vehicle);
            isUnParked = parkingLot.unParkVehicle(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleParkAndWhenUnParkedAnotherVehicle_ShouldThrowException() {
        parkingLot.initializeParkingLot();
        try {
            parkingLot.parkVehicle(vehicle);
            boolean isUnParked = parkingLot.unParkVehicle(new Object());
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        parkingLot.initializeParkingLot();
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
    public void givenCapacityIs2ShouldBeAbleToPark2Vehicle() {
        parkingLot.setCapacity(3);
        parkingLot.initializeParkingLot();
        Object vehicle2 = new Object();
        try {
            parkingLot.parkVehicle(vehicle);
            boolean isParked1 = parkingLot.isVehiclePark(vehicle);
            parkingLot.parkVehicle(vehicle2);
            boolean isParked2 = parkingLot.isVehiclePark(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSameVehiclesTwoTimes_WhenParked_ShouldThrowException() {
        parkingLot.setCapacity(2);
        parkingLot.initializeParkingLot();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE ALREADY PARK", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLot.registerParkingLotObserver(airportSecurity);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = airportSecurity.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.setCapacity(2);
        parkingLot.initializeParkingLot();
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

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLot.setCapacity(2);
        parkingLot.initializeParkingLot();
        parkingLot.registerParkingLotObserver(airportSecurity);
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
        boolean parkingAvailable = airportSecurity.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }

    @Test
    public void givenParkingLot_WhenInitialize_ShouldReturnParkingCapacity() {
        parkingLot.setCapacity(10);
        int parkingLotCapacity = parkingLot.initializeParkingLot();
        Assert.assertEquals(10, parkingLotCapacity);
    }

    @Test
    public void givenParkingLot_ShouldReturnAvailableSlots() {
        List expectedList = new ArrayList();
        expectedList.add(0);
        expectedList.add(1);
        parkingLot.setCapacity(2);
        parkingLot.initializeParkingLot();
        ArrayList emptySlotList = parkingLot.getSlotList();
        Assert.assertEquals(expectedList, emptySlotList);
    }

    @Test
    public void givenParkingLot_WhenParkWithProvidedSlot_ShouldReturnTrue() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        try {
            parkingLot.parkVehicle(vehicle);
            boolean vehiclePark = parkingLot.isVehiclePark(vehicle);
            Assert.assertTrue(vehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleFound_ShouldReturnVehicleSlot() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        try {
            parkingLot.parkVehicle(new Object());
            parkingLot.parkVehicle(vehicle);
            int slotNumber = parkingLot.findVehicle(vehicle);
            Assert.assertEquals(1, slotNumber);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleParkedIfTimeIsSet_ShouldReturnTrue() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        try {
            parkingLot.parkVehicle(vehicle);
            boolean isTimeSet = parkingLot.isTimeSet(vehicle);
            Assert.assertTrue(isTimeSet);
        } catch (ParkingLotException e) {
        }
    }

//    @Test
//    public void givenParkingLotAddMultipleLots() {
//        ParkingLotsSystem parkingLotsSystem = new ParkingLotsSystem();
//        parkingLotsSystem.addLots();
//    }
}
