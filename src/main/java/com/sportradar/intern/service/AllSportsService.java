package com.sportradar.intern.service;

import com.sportradar.intern.domain.Event;
import com.sportradar.intern.domain.Team;
import com.sportradar.intern.dto.DTOEvent;
import com.sportradar.intern.dto.DTOTeam;
import com.sportradar.intern.persistence.db.H2Category;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AllSportsService {

    H2Event eventController;
    H2Category categoryController;
    H2Team teamController;
    List<Event> events;

    public List<Event> getAllEvents() {
        eventController = new H2Event();
        categoryController = new H2Category();
        teamController = new H2Team();
        events = new ArrayList<>();

        List<DTOEvent> simpleDTOEvents = eventController.fetchAll();
        for (int i = 0; i < simpleDTOEvents.size(); i++) {

            List<DTOTeam> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleDTOEvents.get(i).getId());
            String teamName = teamsOfEvent.get(0).getTeamName();

            List<String> categories = categoryController.fetchCategoryFrom(teamName);
            String category = categories.get(0);

            List<Team> teams = new ArrayList();
            for (int j = 0; j < teamsOfEvent.size(); j++) {
                teams.add(new Team(teamsOfEvent.get(j).getTeamName()));
            }
            LocalDateTime dateTime = simpleDTOEvents.get(i).getDateOfEvent();

            Event event = new Event(dateTime.toLocalDate(), dateTime.toLocalTime(), category, teams);
            events.add(event);
        }
        return events;
    }
}