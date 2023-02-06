package com.solvd.farm.binary;

import java.util.Objects;

public class Harvest extends BaseEntity {
    private String date;
    private int weight;

    public Harvest(long id, String date, int weight) {
        super(id);
        this.date = date;
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Harvest harvest = (Harvest) o;
        return super.getId() == super.getId() &&
                weight == harvest.weight &&
                Objects.equals(date, harvest.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, weight);
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "id=" + super.getId() +
                "date='" + date + '\'' +
                ", weight=" + weight +
                '}';
    }

}
