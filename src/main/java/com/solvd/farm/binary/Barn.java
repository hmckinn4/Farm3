package com.solvd.farm.binary;

import java.util.Objects;

public class Barn extends BaseEntity {
    private String name;
    private String location;

    public Barn(long id, String name, String location) {
        super(id);
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
          Barn barn = (Barn) o;
          return super.getId() == super.getId() &&
                 Objects.equals(name, barn.name) &&
                 Objects.equals(location, barn.location);
     }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, location);
    }

    @Override
    public  String toString() {
        return "Barn{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
