package com.solvd.farm.binary;

import java.util.Objects;

public class Animal extends BaseEntity{
    private String name;
    private String type;
    private String breed;


    public Animal(long id, String name, String type, String breed) {
        super(id);
        this.name = name;
        this.type = type;
        this.breed = breed;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return super.getId() == super.getId() &&
                Objects.equals(name, animal.name) &&
                Objects.equals(type, animal.type) &&
                Objects.equals(breed, animal.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, type, breed);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
