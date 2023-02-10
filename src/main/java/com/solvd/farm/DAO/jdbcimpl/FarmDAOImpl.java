package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.FarmDAO;
import com.solvd.farm.binary.Farm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmDAOImpl extends AbstractDAO implements FarmDAO {

    private static final String SQL_QUERY_CREATE_FARM = "INSERT INTO farm (Farm_Name, Farm_Address, Farm_Phone) VALUES (?,?,?)";

    private static final String SQL_QUERY_GET_FARM_BY_ID = "SELECT * FROM farm WHERE Farm_ID = ?";

    private static final String SQL_QUERY_GET_ALL_FARMS = "SELECT * FROM farm";

    private static final String SQL_QUERY_UPDATE_FARM = "UPDATE farm SET Farm_Name = ?, Farm_Address = ?, Farm_Phone = ? WHERE Farm_ID = ?";

    private static final String SQL_QUERY_DELETE_FARM = "DELETE FROM farm WHERE Farm_ID = ?";

    @Override
    public boolean create(Farm farm) {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CREATE_FARM)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, farm.getName());
            preparedStatement.setString(2, farm.getAddress());
            preparedStatement.setString(3, farm.getPhone());
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
        }
    }
    @Override
    public Farm getById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_FARM_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            List<Farm> farms = new ArrayList<>();
            while (resultSet.next()) {

                long farmID = resultSet.getLong("Farm_ID");
                String name = resultSet.getString("Farm_Name");
                String address = resultSet.getString("Farm_Address");
                String phone = resultSet.getString("Farm_Phone");
                farms.add(new Farm(farmID, name, address, phone));
            }

            if (farms.size() > 0) {
                return farms.get(0);
            } else {
                return null;
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
    }

    @Override
    public List<Farm> getAll() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ALL_FARMS);
            resultSet = preparedStatement.executeQuery();
            List<Farm> farms = new ArrayList<>();
            while (resultSet.next()) {
                long farmID = resultSet.getLong("Farm_ID");
                String name = resultSet.getString("Farm_Name");
                String address = resultSet.getString("Farm_Address");
                String phone = resultSet.getString("Farm_Phone");
                farms.add(new Farm(farmID, name, address, phone));
            }
            return farms;
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
    public boolean update(Farm farm) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE_FARM)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, farm.getName());
            preparedStatement.setString(2, farm.getAddress());
            preparedStatement.setString(3, farm.getPhone());
            preparedStatement.setLong(4, farm.getId());
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
        }
    }

    @Override
    public boolean delete(Farm farm) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_FARM);
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, farm.getId());
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}