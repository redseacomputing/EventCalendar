package com.sportradar.intern.dto;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private int id;
    private LocalDateTime dateOfEvent;
    private String category;
    private Team[] team;
    private List<Team> teams;

    public Event() {
    }

    public Event(int id, LocalDateTime dateOfEvent) {
        this.id = id;
        this.dateOfEvent = dateOfEvent;
    }

    public Event(LocalDateTime dateOfEvent, Team[] team) {
        this.dateOfEvent = dateOfEvent;
        this.team = team;
    }

    public Event(LocalDateTime dateOfEvent, Team[] team, String category) {
        this.dateOfEvent = dateOfEvent;
        this.team = team;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(LocalDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public Team[] getTeam() {
        return team;
    }

    public void setTeam(Team[] team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Event{" +
                "time=" + dateOfEvent +
                ", team=" + team +
                '}';
    }
}
