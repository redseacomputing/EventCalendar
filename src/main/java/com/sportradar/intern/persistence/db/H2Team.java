package com.sportradar.intern.persistence.db;

import com.sportradar.intern.dto.Team;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class H2Team {

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
}
