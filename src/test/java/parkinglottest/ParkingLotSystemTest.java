package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.ParkingLot;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLotsSystem;

public class ParkingLotSystemTest {

    ParkingLotsSystem parkingLotsSystem;
    ParkingLot parkingLot1;
    Object vehicle;

    @Before

    public void setUp() throws Exception {
        parkingLotsSystem = new ParkingLotsSystem(1);
        parkingLot1 = new ParkingLot(10);
        vehicle = new Object();
    }

    @Test
    public void givenParkingLotSystem_WhenAddedLots_ShouldReturnTrue() {
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLotsSystem.addLot(parkingLot1);
        parkingLotsSystem.addLot(parkingLot2);
        boolean isLotAdded = parkingLotsSystem.isLotAdded(parkingLot2);
        Assert.assertTrue(isLotAdded);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleParkedOnLot_ShouldReturnTrue() {
        parkingLotsSystem.addLot(parkingLot1);
        parkingLot1.setCapacity(1);
        parkingLot1.initializeParkingLot();
        try {
            parkingLotsSystem.parkVehicle(vehicle);
            boolean isVehiclePark = parkingLotsSystem.isVehiclePark(vehicle);
            Assert.assertTrue(isVehiclePark);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotSystem_EvenlyParkCar_() {
        parkingLot1.setCapacity(10);
        parkingLot1.initializeParkingLot();
        parkingLotsSystem.addLot(parkingLot1);

        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLot2.setCapacity(10);
        parkingLot2.initializeParkingLot();
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        Object vehicle4 = new Object();
        parkingLotsSystem.addLot(parkingLot2);
        try {
            parkingLotsSystem.parkVehicle(vehicle);
            boolean isVehiclePark1 = parkingLotsSystem.isVehiclePark(vehicle);
            parkingLotsSystem.parkVehicle(vehicle2);
            boolean isVehiclePark2 = parkingLotsSystem.isVehiclePark(vehicle2);
            parkingLotsSystem.parkVehicle(vehicle3);
            boolean isVehiclePark3 = parkingLotsSystem.isVehiclePark(vehicle3);
            parkingLotsSystem.parkVehicle(vehicle4);
            boolean isVehiclePark4 = parkingLotsSystem.isVehiclePark(vehicle4);
            Assert.assertTrue(isVehiclePark1 && isVehiclePark2 && isVehiclePark3 && isVehiclePark4);
        } catch (ParkingLotException e) {
        }
    }
}
