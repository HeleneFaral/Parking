package com.ark.exercice.enums;

public enum Color {
    BLUE("bleue"),
    WHITE("blanche"),
    BLACK("noire"),
    PINK("rose");

    public String name = "";

    //Constructor
    Color(String name) {
        this.name = name;
    }
}
