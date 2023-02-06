package com.solvd.farm.DAO.jdbcimpl;

import com.solvd.farm.util.ConnectionPool;

import java.sql.Connection;

public abstract class AbstractDAO {
    public Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    public void releaseConnection(Connection connection) {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
