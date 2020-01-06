package parkinglotsystemTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    ParkingLot parkingLot1;
    private ParkingLot mockedLot;

    @Before

    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        parkingLot1 = new ParkingLot(10);
        mockedLot = mock(ParkingLot.class);
    }

    @Test
    public void givenParkingLotSystem_WhenAddedLots_ShouldReturnTrue() {
        ParkingLot parkingLot2 = new ParkingLot(20);
        parkingLotSystem.addLot(parkingLot1);
        parkingLotSystem.addLot(parkingLot2);
        boolean isLotAdded = parkingLotSystem.isLotAdded(parkingLot2);
        Assert.assertTrue(isLotAdded);
    }

    @Test
    public void givenParkingLotSystem_WhenLotNotAdded_ShouldReturnFalse() {
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLotSystem.addLot(parkingLot1);
        boolean isLotAdded = parkingLotSystem.isLotAdded(parkingLot2);
        Assert.assertFalse(isLotAdded);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleParkedOnLot_ShouldReturnTrue() {
        parkingLotSystem.addLot(mockedLot);
        Vehicle vehicle = new Vehicle("black");
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            verify(mockedLot).parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            when(mockedLot.isVehiclePark(vehicle)).thenReturn(true);
            boolean isVehiclePark = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isVehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotParkedOnLot_ShouldReturnFalse() {
        parkingLotSystem.addLot(mockedLot);
        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            verify(mockedLot).parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            when(mockedLot.isVehiclePark(vehicle2)).thenReturn(false);
            boolean isVehiclePark = parkingLotSystem.isVehiclePark(vehicle2);
            Assert.assertFalse(isVehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleShouldParkInEvenlyDistributedLots_ShouldReturnTrue() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();

        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        Vehicle vehicle4 = new Vehicle("blue");

        parkingLotSystem.addLot(parkingLot2);
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            boolean isVehiclePark1 = parkingLotSystem.isVehiclePark(vehicle);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            boolean isVehiclePark2 = parkingLotSystem.isVehiclePark(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            boolean isVehiclePark3 = parkingLotSystem.isVehiclePark(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            boolean isVehiclePark4 = parkingLotSystem.isVehiclePark(vehicle4);
            Assert.assertTrue(isVehiclePark1 && isVehiclePark2 && isVehiclePark3 && isVehiclePark4);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenUnParkNotAvailableVehicle_ShouldThrowException() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            boolean isUnParkVehicle = parkingLotSystem.unParkVehicle(vehicle3);
            Assert.assertTrue(isUnParkVehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("VEHICLE IS NOT AVAILABLE", e.getMessage());
        }

    }

    @Test
    public void givenParkingLotSystem_WhenVehicleUnPark_ShouldReturnTrue() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        Vehicle vehicle4 = new Vehicle("blue");
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            boolean isUnParkVehicle = parkingLotSystem.unParkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            Assert.assertTrue(isUnParkVehicle);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenDriverTypeIsHandiCap_ShouldReturnNearestLotSpace() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        Vehicle vehicle4 = new Vehicle("blue");
        Vehicle vehicle5 = new Vehicle("white");
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP, "XYZ");
            boolean vehiclePark = parkingLotSystem.isVehiclePark(vehicle5);
            Assert.assertTrue(vehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenLargeVehicleParked_ShouldReturnTrue() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Vehicle vehicle = new Vehicle("black");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        Vehicle vehicle4 = new Vehicle("blue");
        Vehicle vehicle5 = new Vehicle("white");
        Vehicle vehicle6 = new Vehicle("green");

        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(new Vehicle("white"), DriverType.HANDICAP, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP, "XYZ");
            parkingLotSystem.parkVehicle(vehicle6, VehicleType.LARGE_VEHICLE, "XYZ");
            boolean vehiclePark = parkingLotSystem.isVehiclePark(vehicle6);
            Assert.assertTrue(vehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedVehicleColorIsWhite_ShouldListParkedSlots() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Vehicle vehicle1 = new Vehicle("white");
        Vehicle vehicle2 = new Vehicle("black");
        Vehicle vehicle3 = new Vehicle("white");
        Vehicle vehicle4 = new Vehicle("blue");
        Vehicle vehicle5 = new Vehicle("white");
        Vehicle vehicle6 = new Vehicle("green");
        Vehicle vehicle7 = new Vehicle("white");
        Vehicle vehicle8 = new Vehicle("white");
        try {
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle8, DriverType.NORMAL, "XYZ");
            List whiteCarList = parkingLotSystem.findVehicleByField("white");
            System.out.println(whiteCarList.toString());
            List expectedResult = new ArrayList();
            expectedResult.add(0);
            expectedResult.add(2);
            Assert.assertEquals(expectedResult, whiteCarList.get(0));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedVehicleColorIsBlue_ShouldListParkedSlots() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Vehicle vehicle1 = new Vehicle("white", "toyota", "MH-12-A-1234");
        Vehicle vehicle2 = new Vehicle("blue", "BMW", "MH-12-A-1234");
        Vehicle vehicle3 = new Vehicle("blue", "toyota", "MH-12-A-1234");
        Vehicle vehicle4 = new Vehicle("white", "toyota", "MH-12-A-1234");
        Vehicle vehicle5 = new Vehicle("white", "BMW", "MH-12-A-1234");
        Vehicle vehicle6 = new Vehicle("blue", "toyota", "MH-12-B-1234");
        Vehicle vehicle7 = new Vehicle("blue", "toyota", "MH-12-C-1234");
        Vehicle vehicle8 = new Vehicle("blue", "toyota", "MH-12-D-1234");
        try {
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle8, DriverType.NORMAL, "XYZ");
            List<List<String>> vehicleByNumberPlate = parkingLotSystem.findVehicleByNumberPlate("blue", "toyota");
            //System.out.println(vehicleByNumberPlate.toString());
            List expectedResult = new ArrayList();
            expectedResult.add("XYZ  2  MH-12-C-1234");
            Assert.assertEquals(expectedResult, vehicleByNumberPlate.get(0));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenParkedBMWVehicle_ShouldListParkedSlots() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot();
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Vehicle vehicle1 = new Vehicle("white", "toyota", "MH-12-A-1234");
        Vehicle vehicle2 = new Vehicle("blue", "BMW", "MH-12-A-1234");
        Vehicle vehicle3 = new Vehicle("blue", "toyota", "MH-12-A-1234");
        Vehicle vehicle4 = new Vehicle("white", "toyota", "MH-12-A-1234");
        Vehicle vehicle5 = new Vehicle("white", "BMW", "MH-12-A-1234");
        Vehicle vehicle6 = new Vehicle("blue", "toyota", "MH-12-B-1234");
        Vehicle vehicle7 = new Vehicle("blue", "toyota", "MH-12-C-1234");
        Vehicle vehicle8 = new Vehicle("blue", "toyota", "MH-12-D-1234");
        try {
            parkingLotSystem.parkVehicle(vehicle1, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle5, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle6, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle7, DriverType.NORMAL, "XYZ");
            parkingLotSystem.parkVehicle(vehicle8, DriverType.NORMAL, "XYZ");
            List<List<Integer>> vehicleByNumberPlate = parkingLotSystem.findBMWVehicle( "BMW");
            //System.out.println(vehicleByNumberPlate.toString());
            List expectedResult = new ArrayList();
            expectedResult.add(0);
            expectedResult.add(2);
            Assert.assertEquals(expectedResult, vehicleByNumberPlate.get(0));
        } catch (ParkingLotException e) {
        }
    }
}
