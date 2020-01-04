package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

import static org.mockito.Mockito.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    ParkingLot parkingLot1;
    Object vehicle;
    private ParkingLot mockedLot;

    @Before

    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        parkingLot1 = new ParkingLot(10);
        vehicle = new Object();
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
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            verify(mockedLot).parkVehicle(vehicle, DriverType.NORMAL);
            when(mockedLot.isVehiclePark(vehicle)).thenReturn(true);
            boolean isVehiclePark = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isVehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotParkedOnLot_ShouldReturnFalse() {
        parkingLotSystem.addLot(mockedLot);
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            verify(mockedLot).parkVehicle(vehicle, DriverType.NORMAL);
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

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        parkingLotSystem.addLot(parkingLot2);
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            boolean isVehiclePark1 = parkingLotSystem.isVehiclePark(vehicle);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            boolean isVehiclePark2 = parkingLotSystem.isVehiclePark(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            boolean isVehiclePark3 = parkingLotSystem.isVehiclePark(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
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
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
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

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        parkingLotSystem.addLot(parkingLot2);
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            boolean isUnParkVehicle = parkingLotSystem.unParkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
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

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        Object vehicle5 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP);
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


        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        Object vehicle5 = new Object();
        Object vehicle6 = new Object();

        try {
            parkingLotSystem.parkVehicle(vehicle, DriverType.NORMAL);

            parkingLotSystem.parkVehicle(new Object(), DriverType.HANDICAP);
            parkingLotSystem.parkVehicle(vehicle2, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle3, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, DriverType.NORMAL);
            parkingLotSystem.parkVehicle(vehicle5, DriverType.HANDICAP);
            parkingLotSystem.parkVehicle(vehicle6, VehicleType.LARGE_VEHICLE);
            boolean vehiclePark = parkingLotSystem.isVehiclePark(vehicle6);
            Assert.assertTrue(vehiclePark);
        } catch (ParkingLotException e) {
        }
    }
}
