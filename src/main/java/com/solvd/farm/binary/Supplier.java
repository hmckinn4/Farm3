package com.solvd.farm.binary;

import java.util.Objects;

public class Supplier extends BaseEntity {
    public String name;
    public String address;
    public String phone;

    public Supplier(long id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return super.getId() == super.getId() &&
                name.equals(supplier.name) &&
                address.equals(supplier.address) &&
                phone.equals(supplier.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, address, phone);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
