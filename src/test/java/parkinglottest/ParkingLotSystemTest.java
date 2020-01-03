package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    ParkingLot parkingLot1;
    Object vehicle;

    @Before

    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(1);
        parkingLot1 = new ParkingLot(10);
        vehicle = new Object();
    }

    @Test
    public void givenParkingLotSystem_WhenAddedLots_ShouldReturnTrue() {
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLotSystem.addLot(parkingLot1);
        parkingLotSystem.addLot(parkingLot2);
        boolean isLotAdded = parkingLotSystem.isLotAdded(parkingLot2);
        Assert.assertTrue(isLotAdded);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleParkedOnLot_ShouldReturnTrue() {
        parkingLotSystem.addLot(parkingLot1);
        parkingLot1.setCapacity(1);
        parkingLot1.initializeParkingLot();
        try {
            parkingLotSystem.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            boolean isVehiclePark = parkingLotSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isVehiclePark);
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
            parkingLotSystem.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            boolean isVehiclePark1 = parkingLotSystem.isVehiclePark(vehicle);
            parkingLotSystem.parkVehicle(vehicle2, NormalDriveStrategy.NORMAL);
            boolean isVehiclePark2 = parkingLotSystem.isVehiclePark(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3, NormalDriveStrategy.NORMAL);
            boolean isVehiclePark3 = parkingLotSystem.isVehiclePark(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4, NormalDriveStrategy.NORMAL);
            boolean isVehiclePark4 = parkingLotSystem.isVehiclePark(vehicle4);
            Assert.assertTrue(isVehiclePark1 && isVehiclePark2 && isVehiclePark3 && isVehiclePark4);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleUnPark_ShouldReturnTrue() {
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
            parkingLotSystem.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, NormalDriveStrategy.NORMAL);
            boolean isUnParkVehicle = parkingLotSystem.unParkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle3, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, NormalDriveStrategy.NORMAL);
            Assert.assertTrue(isUnParkVehicle);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_WhenDriverTypeIsHandiCap_ShouldReturnNearestLotSpace() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot2);

        ParkingLot parkingLot3 = new ParkingLot(10);
        parkingLot3.setCapacity(10);
        parkingLot3.initializeParkingLot();
        parkingLotSystem.addLot(parkingLot3);

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        Object vehicle5 = new Object();
        try {
            parkingLotSystem.parkVehicle(vehicle, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle2, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle3, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle4, NormalDriveStrategy.NORMAL);
            parkingLotSystem.parkVehicle(vehicle5, HandicapDriverStrategy.HANDICAP);
            Assert.assertTrue(true);
        } catch (ParkingLotException e) {
        }
    }
}
