package com.solvd.farm.binary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBPopulate {

    public static List<Crop> getCrops() {
        List<Crop> crops = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM crops";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Crop crop = new Crop();
                // populate the crop object with data from result set
                crops.add(crop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return crops;
    }
}
