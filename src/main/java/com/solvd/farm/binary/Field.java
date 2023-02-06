package com.solvd.farm.binary;

import java.util.Objects;

public class Field extends BaseEntity{
    private String name;
    private int size;
    private String location;

    public Field(long id, String name, int size, String location) {
        super(id);
        this.name = name;
        this.size = size;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return super.getId() == super.getId() &&
                size == field.size &&
                Objects.equals(name, field.name) &&
                Objects.equals(location, field.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, size, location);
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                '}';
    }
}
