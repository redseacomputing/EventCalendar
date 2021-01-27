package com.sportradar.intern.service;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FootballService {

    H2Event eventController;
    H2Team teamController;
    List<Event> events;
    List<Event> result;


    public List<Event> getAllEvents(){
        eventController = new H2Event();
        teamController = new H2Team();
        events = new ArrayList<>();
        List<Event> simpleEvents = eventController.fetchAllFromCategory("Soccer");
        for (int i = 0; i < simpleEvents.size(); i++) {
            List<Team> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleEvents.get(i).getId());
            Event event = new Event();
            event.setId(simpleEvents.get(i).getId());
            event.setDateOfEvent(simpleEvents.get(i).getDateOfEvent());
            event.setTeams(teamsOfEvent);
            events.add(event);
        }
        result = new ArrayList<>(
                new HashSet<>(events));
        return result;
    }
}
