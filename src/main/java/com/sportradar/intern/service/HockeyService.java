package com.sportradar.intern.service;

import com.sportradar.intern.domain.Event;
import com.sportradar.intern.domain.Team;
import com.sportradar.intern.dto.DTOEvent;
import com.sportradar.intern.dto.DTOTeam;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.util.ArrayList;
import java.util.List;

public class HockeyService {

    H2Event eventController;
    H2Team teamController;
    List<Event> events;

    public List<Event> getAllEvents() {
        eventController = new H2Event();
        teamController = new H2Team();
        events = new ArrayList<>();
        List<DTOEvent> simpleDTOEvents = eventController.fetchAllFromCategory("Hockey");
        for (int i = 0; i < simpleDTOEvents.size(); i++) {
            List<DTOTeam> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleDTOEvents.get(i).getId());
            Event event = new Event();
            event.setDateOfEvent(simpleDTOEvents.get(i).getDateOfEvent().toLocalDate());
            event.setTimeOfEvent(simpleDTOEvents.get(i).getDateOfEvent().toLocalTime());

            List<Team> teams = new ArrayList();
            for (int j = 0; j < teamsOfEvent.size(); j++) {
                teams.add(new Team(teamsOfEvent.get(j).getTeamName()));
            }
            event.setTeams(teams);
            events.add(event);
        }
        return events;
    }
}