package com.ark.exercice.object;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private String name;
    private int carPlaces;
    private int motoPlaces = 15;
    private int bikePlaces;
    private List<Vehicle> motos;
    private List<Vehicle> bikes;
    private List<Vehicle> cars;

    public Parking(final String name, final int carPlaces, final int motoPlaces, final int bikePlaces) {
        this.name = name;
        this.bikePlaces = bikePlaces;
        this.carPlaces = carPlaces;
        this.motoPlaces = motoPlaces;
        this.motos = new ArrayList<>();
        this.bikes = new ArrayList<>();
        this.cars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public void setCarPlaces(int carPlaces) {
        this.carPlaces = carPlaces;
    }

    public int getMotoPlaces() {
        return motoPlaces;
    }

    public void setMotoPlaces(int motoPlaces) {
        this.motoPlaces = motoPlaces;
    }

    public int getBikePlaces() {
        return bikePlaces;
    }

    public void setBikePlaces(int bikePlaces) {
        this.bikePlaces = bikePlaces;
    }

    public List<Vehicle> getMotos() {
        return motos;
    }

    public void setMotos(List<Vehicle> motos) {
        this.motos = motos;
    }

    public List<Vehicle> getBikes() {
        return bikes;
    }

    public void setBikes(List<Vehicle> bikes) {
        this.bikes = bikes;
    }

    public List<Vehicle> getCars() {
        return cars;
    }

    public void setCars(List<Vehicle> cars) {
        this.cars = cars;
    }
}
