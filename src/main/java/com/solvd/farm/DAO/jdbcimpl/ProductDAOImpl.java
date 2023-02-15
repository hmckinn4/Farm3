package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.DAO.ProductDAO;
import com.solvd.farm.binary.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private static final String SQL_QUERY_CREATE_PRODUCT = "INSERT INTO Product (Product_Name, Product_Type, Product_Price) VALUES (?,?, ?)";

    private static final String SQL_QUERY_GET_PRODUCT_BY_ID = "SELECT * FROM Product WHERE Product_ID = ?";

    private static final String SQL_QUERY_GET_ALL_PRODUCTS = "SELECT * FROM Product";

    private static final String SQL_QUERY_UPDATE_PRODUCT = "UPDATE Product SET Product_Name = ?, Product_Type = ?, Product_Price = ? WHERE Product_ID = ?";

    private static final String SQL_QUERY_DELETE_PRODUCT = "DELETE FROM Product WHERE Product_ID = ?";

    @Override
    public boolean create(Product product) {
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CREATE_PRODUCT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setInt(3, product.getPrice());
            //setting values
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
    public Product getById(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            logger.info("Getting product with ID " + id);
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long productId = resultSet.getLong("Product_ID");
                String name = resultSet.getString("Product_Name");
                String type = resultSet.getString("Product_Type");
                Integer price = resultSet.getInt("Product_Price");
                logger.info("Product with ID " + id + " found: " + name + " " + type + " " + price);
                return new Product(productId, name, type, price);
            } else {
                logger.info("Product with ID " + id + " not found");
                return null;
            }
        } catch (SQLException e) {
            logger.error("Get product by ID failed", e);
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
    public List<Product> getAll() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ALL_PRODUCTS);
            resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                long productId = resultSet.getLong("Product_ID");
                String name = resultSet.getString("Product_Name");
                String type = resultSet.getString("Product_Type");
                Integer price = resultSet.getInt("Product_Price");
                products.add(new Product(productId, name, type, price));
            }
            return products;
        } catch (SQLException e) {
            logger.error("Get all Products Query failed");
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
    public boolean update(Product product) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE_PRODUCT);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
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
    public boolean delete(Product product) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_PRODUCT);
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, product.getId());
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return result > 0;
        } catch (SQLException e) {
            logger.error("Delete product failed");
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





