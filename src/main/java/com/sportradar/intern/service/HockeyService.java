package com.sportradar.intern.service;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.util.ArrayList;
import java.util.List;

public class HockeyService {

    H2Event eventController;
    H2Team teamController;
    List<Event> events;

    public List<Event> getAllEvents(){
        eventController = new H2Event();
        teamController = new H2Team();
        events = new ArrayList<>();
        List<Event> simpleEvents = eventController.fetchAllFromCategory("Hockey");
        for (int i = 0; i < simpleEvents.size(); i++) {
            List<Team> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleEvents.get(i).getId());
            Event event = new Event();
            event.setId(simpleEvents.get(i).getId());
            event.setDateOfEvent(simpleEvents.get(i).getDateOfEvent());
            event.setTeams(teamsOfEvent);
            events.add(event);
        }
        return events;
    }
}
