package com.sportradar.intern.dto;

public class DTOTeam {

    private String teamName;

    public DTOTeam(String name) {
        this.teamName = name;
    }

    public DTOTeam() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "DTOTeam{" +
                "name='" + teamName + '\'' +
                '}';
    }
}