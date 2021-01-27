package com.sportradar.intern.persistence.db;

import com.sportradar.intern.dto.DTOEvent;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2Event {

    public List<DTOEvent> fetchAll() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<DTOEvent> allDTOEvents = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(
                    "SELECT e.DateTime, e.EventID FROM Events e;"
            );
            ResultSet rs = stmt.executeQuery();
            LocalDateTime dateTime;
            while (rs.next()) {
                dateTime = LocalDateTime.parse(rs.getString(1), formatter);
                allDTOEvents.add(new DTOEvent(Integer.valueOf(rs.getString(2)), dateTime));
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
        return allDTOEvents;
    }

    public List<DTOEvent> fetchAllFromCategory(String category) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<DTOEvent> allDTOEvents = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(
                    "SELECT DISTINCT e.DateTime, e.EventID" +
                            "                    FROM Events e\n" +
                            "                    JOIN EventTeam et ON et._EventID = e.EventID\n" +
                            "                    JOIN Teams t ON t.TeamID = et._TeamID\n" +
                            "                    JOIN CATEGORIES c on t._CategoryID = c.CategoryID\n" +
                            "                    WHERE c.Name LIKE ?" +
                            "                    ORDER BY e.DateTime;"
            );

            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            LocalDateTime dateTime;
            while (rs.next()) {
                dateTime = LocalDateTime.parse(rs.getString(1), formatter);
                allDTOEvents.add(new DTOEvent(Integer.valueOf(rs.getString(2)), dateTime));
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
        return allDTOEvents;
    }

}