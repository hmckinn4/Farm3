package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.binary.Crop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CropDAOImpl extends AbstractDAO implements CropDAO {

    private static final String SQL_QUERY_CREATE_CROP = "INSERT into crop (name, variety, Growing Season) VALUES (corn, ?, ?)";

    private static final String SQL_QUERY_GET_CROP_BY_ID = "SELECT * FROM crop WHERE id = ?";

    @Override
    public boolean create(Crop crop) {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CREATE_CROP)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(0, crop.getName());
            preparedStatement.setString(1, crop.getVariety());
            preparedStatement.setString(2, crop.getGrowingSeason());
            //setting values
            boolean result = preparedStatement.execute();
            connection.commit();
            return result;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Crop getById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_CROP_BY_ID);
            preparedStatement.setLong(0, id);

            resultSet = preparedStatement.executeQuery();
            List<Crop> crops = new ArrayList<>();
            while (resultSet.next()) {

                long cropId = resultSet.getLong("Crop_ID");
                String name = resultSet.getString("Crop_Name");
                String variety = resultSet.getString("Crop_Variety");
                String growingSeason = resultSet.getString("Crop_Season");
                crops.add(new Crop(cropId, name, variety, growingSeason));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public List<Crop> getAll() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM crop");
            resultSet = preparedStatement.executeQuery();
            List<Crop> crops = new ArrayList<>();
            while (resultSet.next()) {
                long cropId = resultSet.getLong("Crop_ID");
                String name = resultSet.getString("Crop_Name");
                String variety = resultSet.getString("Crop_Variety");
                String growingSeason = resultSet.getString("Crop_Season");
                crops.add(new Crop(cropId, name, variety, growingSeason));
            }
            return crops;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean update(Crop crop) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE crop SET name = ?, variety = ?, growing_season = ? WHERE crop_id = ?");
            connection.setAutoCommit(false);
            preparedStatement.setString(0, crop.getName());
            preparedStatement.setString(1, crop.getVariety());
            preparedStatement.setString(2, crop.getGrowingSeason());
            preparedStatement.setLong(3, crop.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean delete(Crop crop) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM crop WHERE crop_id = ?");
            connection.setAutoCommit(false);
            preparedStatement.setLong(0, crop.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return false;
        } finally {
            releaseConnection(connection);
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
