package com.sportradar.intern.dto;

import java.time.LocalDateTime;

public class Event {
    private LocalDateTime dateOfEvent;
    private Team[] team;
    private String category;

    public Event() {
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
