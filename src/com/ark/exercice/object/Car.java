package com.ark.exercice.object;

public class Car extends Vehicle {

    private final static String GO_PARKING_MESSAGE_START = "Vroum ! vroum ! ma couleur est ";
    private final static String GO_PARKING_MESSAGE_END = " et je peux rentrer dans le parking";

    public Car() {
        super();
        this.goPakingMessage = GO_PARKING_MESSAGE_START + this.color.name + GO_PARKING_MESSAGE_END;
    }
}
