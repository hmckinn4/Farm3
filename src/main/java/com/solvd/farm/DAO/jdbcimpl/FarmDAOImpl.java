package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.FarmDAO;
import com.solvd.farm.binary.Farm;

import java.util.List;

public class FarmDAOImpl extends AbstractDAO implements FarmDAO {

    @Override
    public boolean create(Farm farm) {

        return false;
    }

    @Override
    public Farm getById(long id) {
        return null;
    }

    @Override
    public List<Farm> getAll() {
        return null;
    }

    @Override
    public boolean update(Farm farm) {
        return false;
    }

    @Override
    public boolean delete(Farm farm) {
        return false;
    }
}
