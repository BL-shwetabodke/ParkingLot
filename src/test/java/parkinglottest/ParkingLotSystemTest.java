package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.ParkingLot;
import parkinglotsystem.ParkingLotsSystem;

public class ParkingLotSystemTest {

    ParkingLotsSystem parkingLotsSystem;
    ParkingLot parkingLot;
    @Before

    public void setUp() throws Exception {
        parkingLotsSystem = new ParkingLotsSystem(1);
        parkingLot = new ParkingLot(10);
    }

    @Test
    public void givenParkingLotSystem_WhenAddLot_ShouldReturnTrue() {
        parkingLotsSystem.addLot(parkingLot);
        boolean isLotAdded = parkingLotsSystem.isLotAdded(parkingLot);
        Assert.assertTrue(isLotAdded);
    }
}
