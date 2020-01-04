package parkinglotsystemTest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.*;

import java.util.ArrayList;
import java.util.List;

public class NormalDriverStrategyTest {

    @Test
    public void givenParkingLotSystem_WhenDriverTypeIsNormal_ShouldReturnNearestLotSpace() {
        ParkingLotStrategy parkingLotStrategy = StrategyFactory.getStrategy(DriverType.NORMAL);
        List<ParkingLot> parkingLots1 = new ArrayList<>();
        ParkingLot parkingLot12 = new ParkingLot(1);
        parkingLot12.setCapacity(10);
        parkingLot12.initializeParkingLot();
        parkingLots1.add(parkingLot12);
        ParkingLot parkingLot = null;
        try {
            parkingLot = parkingLotStrategy.getParkingLot(parkingLots1);
            Assert.assertEquals(parkingLot12, parkingLot);
        } catch (ParkingLotException e) {
        }
    }
}
