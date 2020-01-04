package parkinglotsystemTest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.ParkingSlot;
import parkinglotsystem.Vehicle;

public class ParkingSlotTest {

    @Test
    public void givenParkingSlot_WhenVehiclesAreEquals_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("white");
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        ParkingSlot parkingSlot1 = new ParkingSlot(vehicle);
        boolean isVehicleSame = parkingSlot.equals(parkingSlot1);
        Assert.assertTrue(isVehicleSame);
    }
}
