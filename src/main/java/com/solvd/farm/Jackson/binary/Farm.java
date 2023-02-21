package com.solvd.farm.Jackson.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Farm {

    @JsonProperty
    private String name;

    @JsonProperty
    private String address;

    @JsonProperty
    private String phone;

//    public Farm(String name, String address, String phone) {
//        this.name = name;
//        this.address = address;
//        this.phone = phone;
//    }

    @JsonProperty("animals")
    private List<Animal> animals;

    @JsonProperty("crops")
    private List<Crop> crops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
