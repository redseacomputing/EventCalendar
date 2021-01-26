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
                dateTime = LocalDateTime.parse(rs.getString(1), formatter);
                teams[0] = new Team(rs.getString(2));
                category = rs.getString(3);
                events.add(new Event(dateTime, teams, category));
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

    public List<Event> getEvents() {
        return events;
    }


    public List<Team> fetchTeamNamesFrom(int eventID) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/testRadar";

        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Team> allTeamsOfEvent = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement("SELECT t.name\n" +
                    "FROM Teams t\n" +
                    "JOIN EventTeam et ON et._TeamID = t.TeamID\n" +
                    "JOIN Events e ON e.EventID = et._EventID\n" +
                    "WHERE e.EventID = ?;"
            );
            stmt.setInt(1, eventID);
            ResultSet rs = stmt.executeQuery();
            Team team;
            while (rs.next()) {
                team = new Team(rs.getString(1));
                allTeamsOfEvent.add(team);
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
        return allTeamsOfEvent;
    }

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