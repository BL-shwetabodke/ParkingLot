package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.AirportSecurity;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLotSystem;

public class AirportSecurityTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToAirportSecurity() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        parkingLotSystem.initializeParkingLot();
        AirportSecurity airportSecurity = new AirportSecurity();
        Object vehicle = new Object();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = airportSecurity.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformToAirportSecurity() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.setCapacity(2);
        Object vehicle = new Object();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        try {
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }
        boolean parkingAvailable = airportSecurity.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }
}
