package com.sportradar.intern.dto;

public class Team {

    private String teamName;

    public Team(String name) {
        this.teamName = name;
    }

    public Team() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + teamName + '\'' +
                '}';
    }
}