package com.sportradar.intern.persistence.db;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.DataEventAccess;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2DatabaseAllSports implements DataEventAccess {
    List<Event> events;

    @Override
    public void getAllEvents() {

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        events = new ArrayList();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(
                    "SELECT DISTINCT e.DateTime, t.Name, c.Name\n" +
                            "                    FROM Events e\n" +
                            "                    JOIN EventTeam et ON et._EventID = e.EventID\n" +
                            "                    JOIN Teams t ON t.TeamID = et._TeamID\n" +
                            "                    JOIN CATEGORIES c on t._CategoryID = c.CategoryID\n" +
                            "                    ORDER BY e.DateTime;"
            );

            ResultSet rs = stmt.executeQuery();
            LocalDateTime dateTime;
            String category;
            while (rs.next()) {
                Team[] teams = new Team[2];
                dateTime  = LocalDateTime.parse(rs.getString(1), formatter);
                teams[0] = new Team(rs.getString(2));
                category = rs.getString(3);
                events.add(new Event(dateTime,teams,category));
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
    }

    public List<Event> getEvents() {
        return events;
    }
}