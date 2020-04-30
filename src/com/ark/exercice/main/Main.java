package com.ark.exercice.main;

import com.ark.exercice.service.ParkingService;
import com.ark.exercice.service.VehicleService;
import com.ark.exercice.object.Parking;

/**
 * @author Hélène_Faral
 */
public class Main {

    public static void main(String[] args) {

        final Parking parking = ParkingService.initParking();

        while (parking.getBikes().size() < parking.getBikePlaces()) {
            ParkingService.checkAccessParking(parking, VehicleService.initRandomVehicle());
        }

        ParkingService.checkNumberVehicleByColor(parking);
    }
}
