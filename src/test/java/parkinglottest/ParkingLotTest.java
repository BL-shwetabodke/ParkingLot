package parkinglottest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglotsystem.ParkingLotSystem;

public class ParkingLotTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        vehicle= new Object();
    }

    @Test
    public void givenVehicle_WhenParked_ShouldRetuenTrue() {
        boolean isParked = parkingLotSystem.parkVehicle(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehichle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
