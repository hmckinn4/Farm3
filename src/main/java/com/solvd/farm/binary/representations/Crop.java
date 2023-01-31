package com.solvd.farm.binary.representations;
import java.util.Objects;

public class Crop {
    private int id;
    private String name;
    private String variety;
    private String growingSeason;

    public Crop() {
        this.id = id;
        this.name = name;
        this.variety = variety;
        this.growingSeason = growingSeason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return id == crop.id &&
                Objects.equals(name, crop.name) &&
                Objects.equals(variety, crop.variety) &&
                Objects.equals(growingSeason, crop.growingSeason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, variety, growingSeason);
    }

    @Override
    public String toString() {
        return "Crop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", variety='" + variety + '\'' +
                ", growingSeason='" + growingSeason + '\'' +
                '}';
    }
}
