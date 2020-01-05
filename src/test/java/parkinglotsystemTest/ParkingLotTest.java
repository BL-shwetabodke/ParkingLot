package parkinglotsystemTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {

    ParkingLot parkingLot = null;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        parkingLot.initializeParkingLot();
        Vehicle vehicle = new Vehicle("black");
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            boolean isParked = parkingLot.isVehiclePark(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehichle_WhenUnParked_ShouldReturnTrue() {
        parkingLot.initializeParkingLot();
        Vehicle vehicle = new Vehicle("black");
        boolean isUnParked = false;
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            isUnParked = parkingLot.unParkVehicle(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleParkAndWhenUnParkedAnotherVehicle_ShouldThrowException() {
        parkingLot.initializeParkingLot();
        Vehicle vehicle = new Vehicle("black");
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            boolean isUnParked = parkingLot.unParkVehicle(new Vehicle("black"));
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToOwner() {
        parkingLot.initializeParkingLot();
        Vehicle vehicle1 = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = parkingOwner.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenCapacityIs2ShouldBeAbleToPark2Vehicle() {
        parkingLot.setCapacity(3);
        parkingLot.initializeParkingLot();
        Vehicle vehicle1 = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        try {
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            boolean isParked1 = parkingLot.isVehiclePark(vehicle1);
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            boolean isParked2 = parkingLot.isVehiclePark(vehicle2);
            Assert.assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenSameVehiclesTwoTimes_WhenParked_ShouldThrowException() {
        parkingLot.setCapacity(2);
        Vehicle vehicle = new Vehicle("black");
        parkingLot.initializeParkingLot();
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLot.registerParkingLotObserver(parkingOwner);
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE ALREADY PARK", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToAirportSecurity() {
        parkingLot.setCapacity(1);
        parkingLot.initializeParkingLot();
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLot.registerParkingLotObserver(airportSecurity);
        try {
            Vehicle vehicle = new Vehicle("black");
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
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
        Vehicle vehicle = new Vehicle("black");
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
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
        Vehicle vehicle = new Vehicle("black");
        parkingLot.registerParkingLotObserver(airportSecurity);
        try {
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
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
        ArrayList<Integer> emptySlotList = null;
        emptySlotList = parkingLot.getSlotList();
        Assert.assertEquals(expectedList, emptySlotList);
    }

    @Test
    public void givenParkingLot_WhenParkWithProvidedSlot_ShouldReturnTrue() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        try {
            Vehicle vehicle = new Vehicle("black");
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
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
            Vehicle vehicle = new Vehicle("black");
            Vehicle vehicle2 = new Vehicle("black");
            parkingLot.parkVehicle(new Vehicle("black"), DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            int slotNumber = parkingLot.findVehicle(vehicle2);
            Assert.assertEquals(2, slotNumber);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleParkedIfTimeIsSet_ShouldReturnTrue() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        try {
            Vehicle vehicle = new Vehicle("black");
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            boolean isTimeSet = parkingLot.isTimeSet(vehicle);
            Assert.assertTrue(isTimeSet);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedVehicleColorIsWhite_ShouldReturn1() {
        parkingLot.setCapacity(10);
        parkingLot.initializeParkingLot();
        Vehicle vehicle1 = new Vehicle("white");
        Vehicle vehicle2 = new Vehicle("black");
        try {
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            List<Integer> onField = parkingLot.findOnField("white");
            List whiteCarList = new ArrayList();
            whiteCarList.add(0);
            Assert.assertEquals(whiteCarList, onField);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedBlueToyotaCar_ShouldReturnLocationAndAttendantNameAndPlateNumber() {
        parkingLot.setCapacity(20);
        parkingLot.initializeParkingLot();
        Vehicle vehicle1 = new Vehicle("white","toyota","MH-12-A-1234");
        Vehicle vehicle2 = new Vehicle("blue","BMW","MH-12-A-1234");
        Vehicle vehicle3 = new Vehicle("blue","toyota","MH-12-A-1234");
        Vehicle vehicle4 = new Vehicle("white","toyota","MH-12-A-1234");
        Vehicle vehicle5 = new Vehicle("white","BMW","MH-12-A-1234");
        Vehicle vehicle6 = new Vehicle("blue","toyota","MH-12-B-1234");
        try {
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "asb");
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle3, DriverType.NORMAL, "pqr");
            parkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "xyz");
            List<String> onField = parkingLot.findParkedToyatoVehicleDetails("blue","toyota");
            List<String> whiteCarList = new ArrayList();
            whiteCarList.add("pqr  2  MH-12-A-1234");
            whiteCarList.add("xyz  5  MH-12-B-1234");
            Assert.assertEquals(whiteCarList, onField);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedBMWVehicle_ShouldReturnLocation() {
        parkingLot.setCapacity(20);
        parkingLot.initializeParkingLot();
        Vehicle vehicle1 = new Vehicle("white","toyota","MH-12-A-1234");
        Vehicle vehicle2 = new Vehicle("blue","BMW","MH-12-A-1234");
        Vehicle vehicle3 = new Vehicle("blue","toyota","MH-12-A-1234");
        Vehicle vehicle4 = new Vehicle("white","toyota","MH-12-A-1234");
        Vehicle vehicle5 = new Vehicle("white","BMW","MH-12-A-1234");
        Vehicle vehicle6 = new Vehicle("blue","toyota","MH-12-B-1234");
        try {
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "asb");
            parkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle3, DriverType.NORMAL, "pqr");
            parkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "xyz");
            parkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "xyz");
            List<Integer> vehicleByNumberPlate = parkingLot.findParkedBMWVehicleDetails( "BMW");
            //System.out.println(vehicleByNumberPlate.toString());
            List expectedResult = new ArrayList();
            expectedResult.add(1);
            expectedResult.add(4);
            Assert.assertEquals(expectedResult, vehicleByNumberPlate);
        } catch (ParkingLotException e) {
        }
    }
}
