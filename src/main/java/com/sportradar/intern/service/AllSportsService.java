package com.sportradar.intern.service;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.db.H2Category;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.util.ArrayList;
import java.util.List;

public class AllSportsService {

    H2Event eventController;
    H2Category categoryController;
    H2Team teamController;
    List<Event> events;

    public List<Event> getAllEvents(){
        eventController = new H2Event();
        categoryController = new H2Category();
        teamController = new H2Team();
        events = new ArrayList<>();
        List<Event> simpleEvents = eventController.fetchAll();
        for (int i = 0; i < simpleEvents.size(); i++) {
            List<Team> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleEvents.get(i).getId());
            String teamName = teamsOfEvent.get(0).getTeamName();
            List<String> category = categoryController.fetchCategoryFrom(teamName);
            Event event = new Event();
            event.setId(simpleEvents.get(i).getId());
            event.setDateOfEvent(simpleEvents.get(i).getDateOfEvent());
            event.setCategory(category.get(0));
            event.setTeams(teamsOfEvent);
            events.add(event);
        }
        return events;
    }
}
