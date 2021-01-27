package com.sportradar.intern.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DTOEvent {
    private int id;
    private LocalDateTime dateOfEvent;
    private String category;
    private DTOTeam[] DTOTeam;
    private List<DTOTeam> DTOTeams;

    public DTOEvent() {
    }

    public DTOEvent(int id, LocalDateTime dateOfEvent) {
        this.id = id;
        this.dateOfEvent = dateOfEvent;
    }

    public DTOEvent(LocalDateTime dateOfEvent, DTOTeam[] DTOTeam) {
        this.dateOfEvent = dateOfEvent;
        this.DTOTeam = DTOTeam;
    }

    public DTOEvent(LocalDateTime dateOfEvent, DTOTeam[] DTOTeam, String category) {
        this.dateOfEvent = dateOfEvent;
        this.DTOTeam = DTOTeam;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DTOTeam> getTeams() {
        return DTOTeams;
    }

    public void setTeams(List<DTOTeam> DTOTeams) {
        this.DTOTeams = DTOTeams;
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

    public DTOTeam[] getTeam() {
        return DTOTeam;
    }

    public void setTeam(DTOTeam[] DTOTeam) {
        this.DTOTeam = DTOTeam;
    }

    @Override
    public String toString() {
        return "DTOEvent{" +
                "time=" + dateOfEvent +
                ", DTOTeam=" + DTOTeam +
                '}';
    }
}
