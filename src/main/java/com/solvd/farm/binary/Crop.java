package com.solvd.farm.binary;
import java.util.Objects;

public class Crop extends BaseEntity {
    private String name;
    private String variety;
    private String growingSeason;

    public Crop(long id, String name, String variety, String growingSeason) {
        super(id);
        this.name = name;
        this.variety = variety;
        this.growingSeason = growingSeason;
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
        return super.getId() == super.getId() &&
                Objects.equals(name, crop.name) &&
                Objects.equals(variety, crop.variety) &&
                Objects.equals(growingSeason, crop.growingSeason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(), name, variety, growingSeason);
    }

    @Override
    public String toString() {
        return "Crop{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", variety='" + variety + '\'' +
                ", growingSeason='" + growingSeason + '\'' +
                '}';
    }
}
