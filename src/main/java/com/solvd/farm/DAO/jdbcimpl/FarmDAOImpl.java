package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.FarmDAO;
import com.solvd.farm.binary.Farm;

import java.sql.Connection;
import java.util.List;

public class FarmDAOImpl extends AbstractDAO implements FarmDAO {
    @Override
    public List<Farm> getAllCrops() {
        Connection connection =getConnection();

        releaseConnection(connection);
        return null;
    }

    @Override
    public int updateCrop(Farm farm) {
        return 0;
    }

    @Override
    public int deleteCrop(int id) {
        return 0;
    }

    @Override
    public void create(Farm farm) {

    }

    @Override
    public Farm getById(long id) {
        return null;
    }
}
