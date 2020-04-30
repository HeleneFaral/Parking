package com.ark.exercice.service;

import com.ark.exercice.enums.Color;
import com.ark.exercice.enums.TypeVehicle;
import com.ark.exercice.object.*;

import java.util.List;
import java.util.Scanner;

public class ParkingService {
    private final static String PARKING_MESSAGE_START = "Je suis le parking ";
    private final static String GO_PARKING_MESSAGE_END = " et je viens d'accepter un(e) ";
    private final static String GO_PARKING_MESSAGE_COUNT_START = "Il me reste maintenant ";
    private final static String GO_PARKING_MESSAGE_COUNT_END = " places pour ";
    private final static String NO_GO_PARKING_MESSAGE_END = " et je viens de refuser un(e) ";
    static Color userChoiceColor;
    final static Scanner scanner = new Scanner(System.in);



    /**
     * Init a parking Object with inputs from the user :
     * name of the parking
     * car place number
     * moto place number
     * bike place number
     * And save the color choose by the user
     *
     * @return Parking - the parking created by the user
     */
    public static Parking initParking() {

        System.out.println("\nBienvenue, nous allons initaliser un parking :) \n");

        final String parkingName = getParkingName();

        final int carPlaceNumber = getCorrectPlaceNumber(TypeVehicle.CAR.type, 10, -1, -1);
        final int motoPlaceNumber = getCorrectPlaceNumber(TypeVehicle.MOTO.type, 1, -1, 15);
        final int bikePlaceNumber = getCorrectPlaceNumber(TypeVehicle.BIKE.type, 1, 10, -1);

        userChoiceColor = getUserChoiceColor();

        return new Parking(parkingName, carPlaceNumber, motoPlaceNumber, bikePlaceNumber);
    }

    /**
     * Check parking access for random vehicles
     *
     * @param parking - the parking created by the user
     * @param vehicle - the vehicule requiring access to the parking
     */
    public static void checkAccessParking(final Parking parking, final Vehicle vehicle) {
        boolean accessAllowed;
        if (vehicle instanceof Car) {
            accessAllowed = parking.getCars().size() < parking.getCarPlaces();
            if (accessAllowed) {
                final List<Vehicle> cars = parking.getCars();
                cars.add(vehicle);
                parking.setCars(cars);
            }
            sendAccessInformations(parking, vehicle, TypeVehicle.CAR, accessAllowed, (parking.getCarPlaces() - parking.getCars().size()));
        } else if (vehicle instanceof Moto) {
            accessAllowed = parking.getMotos().size() < parking.getMotoPlaces();
            if (accessAllowed) {
                final List<Vehicle> motos = parking.getMotos();
                motos.add(vehicle);
                parking.setMotos(motos);
            }
            sendAccessInformations(parking, vehicle, TypeVehicle.MOTO, accessAllowed, (parking.getMotoPlaces() - parking.getMotos().size()));
        } else if (vehicle instanceof Bike) {
            final List<Vehicle> bikes = parking.getBikes();
            bikes.add(vehicle);
            parking.setBikes(bikes);
            sendAccessInformations(parking, vehicle, TypeVehicle.BIKE, true, (parking.getBikePlaces() - parking.getBikes().size()));
        } else {
            System.out.println("Ce type de véhicule ne peut pas rentrer dans le parking \n");
        }
    }

    /**
     * Retrieve the parking name from the user
     * Must contains at least one carater
     *
     * @return String - a correct parking name
     */
    private static String getParkingName() {
        System.out.println("Comment voulez-vous nommer le parking ?");
        String name = scanner.nextLine();
        boolean isNotEmpty = true;
        while (isNotEmpty) {
            if (name == null || name.isEmpty()) {
                System.out.println("Merci de saisir au moins un caractère : ");
                name = scanner.nextLine();
            } else {
                isNotEmpty = false;
            }
        }
        return name;
    }

    /**
     * Retrieve the number of places of a given type of vehicle from the user
     * Must respect a maximum and minimum number of place if required
     * If the input is wrong a default value is applied if required
     * If value is -1 it's not required
     *
     * @param typeVehicule - given type of vehicle
     * @param nbMinPlace - the minimum number of places allowed
     * @param nbMaxPlace - the maximum number of places allowed
     * @param defalutValue - Infos de la CB
     * @return placeNumber - the number of places
     */
    private static int getCorrectPlaceNumber(final String typeVehicule, final int nbMinPlace, final int nbMaxPlace, final int defalutValue) {
        boolean isNotCorrect = true;
        int placeNumber = defalutValue;

        String userEntry = getUserEntry(typeVehicule, nbMinPlace, nbMaxPlace, true);

        while (isNotCorrect) {
            if (isStringNumeric(userEntry) && (Integer.parseInt(userEntry) >= nbMinPlace && (nbMaxPlace == -1 || Integer.parseInt(userEntry) <= nbMaxPlace))) {
                isNotCorrect = false;
                placeNumber = Integer.parseInt(userEntry);
            } else {
                if (defalutValue == -1) {
                    userEntry = getUserEntry(typeVehicule, nbMinPlace, nbMaxPlace, false);
                } else {
                    isNotCorrect = false;
                }
            }
        }
        return placeNumber;
    }

    /**
     * Retrieve the vehicle color from the user
     * Must match an existing vehicle number
     *
     * @return String - a correct parking name
     */
    private static Color getUserChoiceColor() {
        System.out.println("Choisissez une couleur parmi les suivantes  : bleue, blanche, noire, rose");
        String userColor = scanner.nextLine();
        Color colorByValue = null;
        boolean isNotEmpty = true;
        while (isNotEmpty) {
            colorByValue = findColorByValue(userColor);
            if (colorByValue == null) {
                System.out.println("La saisie n'est pas bonne");
                System.out.println("Merci de choisir une couleur parmi les suivantes  : bleue, blanche, noire, rose");
                userColor = scanner.nextLine();
            } else {
                isNotEmpty = false;
            }
        }
        return colorByValue;
    }

    /**
     * Retrieve the number of vehicule present in the parking with a given color
     *
     * @param parking -  - the parking created by the user
     */
    public static void checkNumberVehicleByColor(final Parking parking) {
        System.out.println("Le parking " + parking.getName() + " ne possède plus de places pour vélos, Le programme s'arrête là ! \n");
        final int totalVehicle = findVehiculeByColor(parking.getCars(), userChoiceColor) + findVehiculeByColor(parking.getCars(), userChoiceColor) + findVehiculeByColor(parking.getMotos(), userChoiceColor);
        System.out.println("Je suis le parking " + parking.getName() + " et j’ai " + totalVehicle + " véhicules de couleur " + userChoiceColor.name + " en mon antre");
    }

    /**
     * Retrieve the number of vehicule of a type present in the parking with a given color
     *
     * @param vehicles - the color choosen by user
     * @param color - the color choosen by user
     * @return the number of given type vehicule
     */
    private static int findVehiculeByColor(final List<Vehicle> vehicles, final Color color) {
        return Math.toIntExact(vehicles.stream().filter(v -> v.getColor().equals(color)).count());
    }

    /**
     * Retrieve the Color matching a given value
     * return null if none
     *
     * @param userColor - the color choosen by user
     * @return Color - matching color
     */
    private static Color findColorByValue(final String userColor) {
        if (userColor != null && !userColor.isEmpty()) {
            for (Color color : Color.values()) {
                if (color.name.equals(userColor.toLowerCase())) {
                    return color;
                }
            }
        }
        return null;
    }

    /**
     * Display for the user the informations about the vehicle access attempt to the parking
     *
     * @param parking - the parking created by the user
     * @param vehicle - the parking created by the user
     * @param type - the parking created by the user
     * @param isAllowed - the parking created by the user
     * @param emptyPlaces - the parking created by the user
     *
     * @return Color - matching color
     */
    private static void sendAccessInformations(final Parking parking, final Vehicle vehicle, final TypeVehicle type, final boolean isAllowed, final int emptyPlaces) {
        if (isAllowed) {
            System.out.println(PARKING_MESSAGE_START + parking.getName() + GO_PARKING_MESSAGE_END + type.type + "\n");
            System.out.println(GO_PARKING_MESSAGE_COUNT_START + emptyPlaces + GO_PARKING_MESSAGE_COUNT_END + type.type + "\n");
            System.out.println(vehicle.getGoPakingMessage() + "\n");
        } else {
            System.out.println(PARKING_MESSAGE_START + parking.getName() + NO_GO_PARKING_MESSAGE_END + type.type + "\n");
            System.out.println(vehicle.getNoGoPakingMessage() + "\n");
        }
    }

    /**
     * Check if String is elligible to int cast
     *
     * @param userEntry - input from the user
     *
     * @return boolean - if elligible
     */
    private static boolean isStringNumeric(final String userEntry) {
        if (userEntry == null || userEntry.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(userEntry);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Retrieve the user entry about vehicle from given type place number
     *
     * @param typeVehicule - input from the user
     * @param nbMinPlace - input from the user
     * @param nbMaxPlace - input from the user
     * @param firstEntry - input from the user
     * @return boolean - if elligible
     */
    private static String getUserEntry(final String typeVehicule, final int nbMinPlace, final int nbMaxPlace, final boolean firstEntry) {
        if (firstEntry) {
            System.out.println("Combien de places pour " + typeVehicule + " voulez vous ?");
        } else {
            System.out.println("La donnée n'est pas correcte, merci de la saisir à nouveau");
        }
        System.out.println("Le nombre doit être supérieur ou égal à " + nbMinPlace);
        if (nbMaxPlace != -1) {
            System.out.println("Le nombre de place doit être inférieur ou égal à " + nbMaxPlace);
        }
        return scanner.nextLine();
    }

}
