package com.ark.exercice.object;

import com.ark.exercice.enums.TypeMoto;
import com.ark.exercice.service.VehicleService;

public class Moto extends Vehicle  {

    private final static String GO_PARKING_MESSAGE_START = "Youpi ! ma couleur est ";
    private final static String GO_PARKING_MESSAGE_END = " et je peux rentrer dans le parking";
    private final static String NO_GO_PARKING_MESSAGE_START = "Hey ! je suis une ";
    private final static String NO_GO_PARKING_MESSAGE_END = " vous allez regretter de ne pas m’avoir laissé entrer";

    protected TypeMoto typeMoto;

    public Moto() {
        super();
        this.typeMoto = VehicleService.getRandomTypeMoto();
        this.goPakingMessage = GO_PARKING_MESSAGE_START + this.color.name + GO_PARKING_MESSAGE_END;
        this.noGoPakingMessage = NO_GO_PARKING_MESSAGE_START + this.typeMoto.type + NO_GO_PARKING_MESSAGE_END;
    }
}