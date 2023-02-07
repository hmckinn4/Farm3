package com.solvd.farm;
// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

import com.solvd.farm.DAO.FarmDAO;
import com.solvd.farm.DAO.jdbcimpl.FarmDAOImpl;
import com.solvd.farm.binary.Farm;

public class Main2 {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        Farm farm = new Farm(2, "Green Valley", "123 Cowboy Road Texas","555-5555");
        farm.setName("Green Valley");
        farm.setAddress("123 Cowboy Road Texas");

        FarmDAO farmDAO = new FarmDAOImpl();
        farmDAO.create(farm);
    }
}



