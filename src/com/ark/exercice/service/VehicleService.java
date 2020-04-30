package com.ark.exercice.service;

import com.ark.exercice.enums.Color;
import com.ark.exercice.enums.TypeMoto;
import com.ark.exercice.enums.TypeVehicle;
import com.ark.exercice.object.Bike;
import com.ark.exercice.object.Car;
import com.ark.exercice.object.Moto;
import com.ark.exercice.object.Vehicle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VehicleService {
    private static final Random random = new Random();
    // Color
    private static final List<Color> colorList = Collections.unmodifiableList(Arrays.asList(Color.values()));
    private static final int listColorSize = colorList.size();
    // MotoType
    private static final List<TypeMoto> typeMotoList = Collections.unmodifiableList(Arrays.asList(TypeMoto.values()));
    private static final int listMotoTypeSize = typeMotoList.size();
    // Vehicle
    private static final List<TypeVehicle> typeVehicleList = Collections.unmodifiableList(Arrays.asList(TypeVehicle.values()));
    private static final int listVehicleTypeSize = typeVehicleList.size();

    public static Color getRandomColor() {
        return colorList.get(random.nextInt(listColorSize));
    }

    public static TypeMoto getRandomTypeMoto() {
        return typeMotoList.get(random.nextInt(listMotoTypeSize));
    }

    public static TypeVehicle getRandomVehicleType() {
        return typeVehicleList.get(random.nextInt(listVehicleTypeSize));
    }

    public static Vehicle initRandomVehicle() {
        final TypeVehicle vehicleType = getRandomVehicleType();
        switch (vehicleType) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case MOTO:
                return new Moto();
            default:
                return null;
        }
    }
}
