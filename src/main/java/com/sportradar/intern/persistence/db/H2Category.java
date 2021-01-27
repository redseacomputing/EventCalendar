package com.sportradar.intern.persistence.db;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2Category {

    public List<String> fetchCategoryFrom(String teamName) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> categories = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement("SELECT c.Name\n" +
                    "FROM Categories c\n" +
                    "JOIN TEAMS T on c.CategoryID = T._CategoryID\n" +
                    "WHERE T.Name =?;"
            );
            stmt.setString(1, teamName);
            ResultSet rs = stmt.executeQuery();
            String category;
            categories= new ArrayList<>();
            while (rs.next()) {
                category = rs.getString(1);
                categories.add(category);
            }
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Can't load JDBC Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error during SQL query");
            e.printStackTrace();
        }
        return categories;
    }
}
