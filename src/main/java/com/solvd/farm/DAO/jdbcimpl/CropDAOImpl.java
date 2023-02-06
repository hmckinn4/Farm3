package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.binary.Crop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CropDAOImpl extends AbstractDAO implements CropDAO {

    @Override
    public List<Crop> getAllCrops() {
        Connection connection =getConnection();

        releaseConnection(connection);
        // Implementation using JDBC to get all crops from database
        return null;
    }

    @Override
    public Crop getCropById(int id) {
        PreparedStatement
                ResultSet;
        // Implementation using JDBC to get a crop by id from database
        return null;
    }

    @Override
    public int addCrop(Crop crop) {
        // Implementation using JDBC to add a crop to the database
        return 0;
    }

    @Override
    public int updateCrop(Crop crop) {
        // Implementation using JDBC to update a crop in the database
        return 0;
    }

    @Override
    public int deleteCrop(int id) {
        // Implementation using JDBC to delete a crop from the database
        return 0;
    }

    @Override
    public void create(Crop crop) {

    }

    @Override
    public Crop getById(long id) {
        return null;
    }
}
