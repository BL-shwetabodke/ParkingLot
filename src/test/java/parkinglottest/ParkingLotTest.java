package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.ParkingLotSystem;

public class ParkingLotTest {

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.parkVehicle();
        Assert.assertTrue(isParked);
    }
}
