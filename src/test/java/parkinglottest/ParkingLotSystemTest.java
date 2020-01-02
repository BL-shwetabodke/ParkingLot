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
        try {
            boolean b = parkingLotsSystem.parkVehicle(vehicle, parkingLot1);
            Assert.assertTrue(b);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
