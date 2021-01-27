package com.sportradar.intern.persistence.db;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.DataEventAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2Event {

    public List<Event> fetchAll() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Event> allEvents = new ArrayList<>();
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
                allEvents.add(new Event(Integer.valueOf(rs.getString(2)), dateTime));
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
        return allEvents;
    }

    public List<Event> fetchAllFromCategory(String category) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Event> allEvents = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(
                    "SELECT e.DateTime, e.EventID" +
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
                allEvents.add(new Event(Integer.valueOf(rs.getString(2)), dateTime));
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
        return allEvents;
    }

}