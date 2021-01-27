package com.sportradar.intern.domain;

public class Team {

    private String teamName;

    public Team() {
    }

    public Team(String name) {
        this.teamName = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

}