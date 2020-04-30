package com.ark.exercice.enums;

public enum TypeVehicle {
    MOTO("moto"),
    BIKE("vélo"),
    CAR("voiture");

    public String type = "";

    //Constructor
    TypeVehicle(String type) {
        this.type = type;
    }
}
