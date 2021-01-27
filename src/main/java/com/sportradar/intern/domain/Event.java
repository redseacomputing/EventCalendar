package com.sportradar.intern.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Event {
    private LocalDate dateOfEvent;
    private LocalTime timeOfEvent;
    private String category;
    private List<Team> teams;

    public Event() {
    }

    public Event(LocalDate dateOfEvent, LocalTime timeOfEvent, String category, List<Team> teams) {
        this.dateOfEvent = dateOfEvent;
        this.timeOfEvent = timeOfEvent;
        this.category = category;
        this.teams = teams;
    }

    public LocalDate getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(LocalDate dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public LocalTime getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(LocalTime timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}