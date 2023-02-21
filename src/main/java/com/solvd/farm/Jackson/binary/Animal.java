package com.solvd.farm.Jackson.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal {

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    @JsonProperty
    private String breed;

//    public Animal(String name, String type, String breed) {
//        this.name = name;
//        this.type = type;
//        this.breed = breed;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
