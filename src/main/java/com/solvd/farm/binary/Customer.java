package com.solvd.farm.binary;

import java.util.Objects;

public class Customer extends BaseEntity{

    private String name;

    private String address;

    private String phone;

    public Customer(long id, String name, String address, String phone) {
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
        Customer customer = (Customer) o;
        return super.getId() == super.getId() &&
                Objects.equals(name, customer.name) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, address, phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
