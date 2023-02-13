package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.AnimalDAO;
import com.solvd.farm.binary.Animal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOImpl extends AbstractDAO implements AnimalDAO {

    private static final Logger logger = LogManager.getLogger(AnimalDAOImpl.class);

    private static final String SQL_QUERY_CREATE_ANIMAL = "INSERT INTO animal (Animal_Name, Animal_Type, Animal_Breed) VALUES (?,?,?)";

    private static final String SQL_QUERY_GET_ANIMAL_BY_ID = "SELECT * FROM animal WHERE Animal_ID = ?";

    private static final String SQL_QUERY_GET_ALL_ANIMALS = "SELECT * FROM animal";

    private static final String SQL_QUERY_UPDATE_ANIMAL = "UPDATE animal SET Animal_Name = ?, Animal_Type = ?, Animal_Breed = ? WHERE Animal_ID = ?";

    private static final String SQL_QUERY_DELETE_ANIMAL = "DELETE FROM animal WHERE Animal_ID = ?";

    @Override
    public boolean create(Animal animal) {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CREATE_ANIMAL)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getType());
            preparedStatement.setString(3, animal.getBreed());
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
    public Animal getById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            logger.info("Getting animal with ID " + id);
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ANIMAL_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long animalId = resultSet.getLong("Animal_ID");
                String name = resultSet.getString("Animal_Name");
                String type = resultSet.getString("Animal_Type");
                String breed = resultSet.getString("Animal_Breed");
                logger.info("Animal with ID " + id + " found: " + name + " " + type + " " + breed);
                return new Animal(animalId, name, type, breed);
            } else {
                logger.info("Animal with ID " + id + " not found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Get animal by ID failed", e);
            return null;
        } finally {
            releaseConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Animal> getAll() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ALL_ANIMALS);
            resultSet = preparedStatement.executeQuery();
            List<Animal> animals = new ArrayList<>();
            while (resultSet.next()) {
                long animalId = resultSet.getLong("Animal_ID");
                String name = resultSet.getString("Animal_Name");
                String type = resultSet.getString("Animal_Type");
                String breed = resultSet.getString("Animal_Breed");
                animals.add(new Animal(animalId, name, type, breed));
            }
            return animals;
        } catch (SQLException e) {
            logger.error("Get all animals query failed");
            return null;
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
    public boolean update(Animal animal) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE_ANIMAL);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getType());
            preparedStatement.setString(3, animal.getBreed());
            preparedStatement.setLong(4, animal.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public boolean delete(Animal animal) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_ANIMAL);
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, animal.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            logger.error("Delete animal failed");
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

