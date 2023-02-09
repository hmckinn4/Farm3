package com.solvd.farm.binary;

import java.util.Objects;

public class Farmer extends BaseEntity{
    private String name;
    private String phone;
    private String address;

    public Farmer(long id, String name, String phone, String address) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return super.getId() == super.getId() &&
                Objects.equals(name, farmer.name) &&
                Objects.equals(address, farmer.address) &&
                Objects.equals(phone, farmer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phone, address);
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
