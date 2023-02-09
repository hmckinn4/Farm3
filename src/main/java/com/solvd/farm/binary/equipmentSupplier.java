package com.solvd.farm.binary;

import java.util.Objects;

public class equipmentSupplier extends BaseEntity{
    private String name;

    private String phone;

    public equipmentSupplier(long id, String name, String phone) {
        super(id);
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        equipmentSupplier equipmentSupplier = (equipmentSupplier) o;
        return super.getId() == super.getId() &&
                Objects.equals(name, equipmentSupplier.name) &&
                Objects.equals(phone, equipmentSupplier.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, phone);
    }

    @Override
    public String toString() {
        return "Equipment_Supplier{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", type='" + phone + '\'' +
                '}';
    }
}
