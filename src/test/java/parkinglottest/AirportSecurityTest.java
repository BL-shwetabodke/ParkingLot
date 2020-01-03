package parkinglottest;

import org.junit.Assert;
import org.junit.Test;
import parkinglotsystem.AirportSecurity;
import parkinglotsystem.ParkingLotException;
import parkinglotsystem.ParkingLot;

public class AirportSecurityTest {

    @Test
    public void givenVehicle_WhenParkingLotFull_ShouldInformToAirportSecurity() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.initializeParkingLot();
        AirportSecurity airportSecurity = new AirportSecurity();
        Object vehicle = new Object();
        parkingLot.registerParkingLotObserver(airportSecurity);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        boolean parkingFull = airportSecurity.isParkingFull();
        Assert.assertTrue(parkingFull);
    }

    @Test
    public void givenVehicle_WhenSpaceIsAvailable_ShouldInformToAirportSecurity() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.setCapacity(2);
        parkingLot.initializeParkingLot();
        AirportSecurity airportSecurity = new AirportSecurity();
        Object vehicle = new Object();
        parkingLot.registerParkingLotObserver(airportSecurity);
        try {
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(new Object());
            parkingLot.parkVehicle(new Object());
        } catch (ParkingLotException e) {
        }
        try {
            parkingLot.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }
        boolean parkingAvailable = airportSecurity.isParkingAvailable();
        Assert.assertFalse(parkingAvailable);
    }
}
