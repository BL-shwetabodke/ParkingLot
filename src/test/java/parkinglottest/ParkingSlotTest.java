package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.ParkingSlot;

import static org.mockito.Mockito.mock;

public class ParkingSlotTest {

    /*@Mock
    ParkingSlot parkingSlot;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        this.parkingSlot = mock(ParkingSlot.class);
    }*/

    @Test
    public void givenParkingSlot_WhenVehiclesAreEquals_ShouldReturnTrue() {
        Object vehicle = new Object();
        ParkingSlot parkingSlot = new ParkingSlot(vehicle);
        ParkingSlot parkingSlot1 = new ParkingSlot(vehicle);
        boolean isVehicleSame = parkingSlot.equals(parkingSlot1);
        Assert.assertTrue(isVehicleSame);
    }
}
