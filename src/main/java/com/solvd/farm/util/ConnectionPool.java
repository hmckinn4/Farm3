package com.solvd.farm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static ConnectionPool instance;
    private List<Connection> connections;
    private int poolSize;
    private String url;
    private String username;
    private String password;
private ConnectionPool() {
        // initialize variables such as poolSize, url, username, and password based on your database configuration
        poolSize = 10;
        url = "jdbc:mysql://localhost:3306/FarmDB";
        username = "root";
        password = "12345678";
        connections = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
        try {
        Connection connection = DriverManager.getConnection(url, username, password);
        connections.add(connection);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }
        }

public static ConnectionPool getInstance() {
        if (instance == null) {
        instance = new ConnectionPool();
        }
        return instance;
        }

public synchronized Connection getConnection() {
        if (connections.isEmpty()) {
        try {
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }
        return connections.remove(connections.size() - 1);
        }

public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        }
}
