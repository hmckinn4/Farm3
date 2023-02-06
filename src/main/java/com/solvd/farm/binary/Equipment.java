package com.solvd.farm.binary;

import java.util.Objects;

public class Equipment extends BaseEntity {
    private String name;
    private String type;

    public Equipment(long id, String name, String type) {
        super(id);
        this.name = name;
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return super.getId() == super.getId() &&
                Objects.equals(name, equipment.name) &&
                Objects.equals(type, equipment.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, type);
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
