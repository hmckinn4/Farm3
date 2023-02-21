package com.solvd.farm.Jackson.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crop {

    @JsonProperty
    private String name;

    @JsonProperty
    private String variety;

    @JsonProperty
    private String growingSeason;

//    public Crop(String name, String variety, String growingSeason) {
//        this.name = name;
//        this.variety = variety;
//        this.growingSeason = growingSeason;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getGrowingSeason() {
        return growingSeason;
    }

    public void setGrowingSeason(String growingSeason) {
        this.growingSeason = growingSeason;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "name='" + name + '\'' +
                ", variety='" + variety + '\'' +
                ", growingSeason='" + growingSeason + '\'' +
                '}';
    }
}