package com.sportradar.intern.service;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.dto.Team;
import com.sportradar.intern.persistence.db.H2DatabaseAllSports;

import java.util.ArrayList;
import java.util.List;

public class AllSportsService {

    H2DatabaseAllSports db;
    List<Event> events;


    public List<Event> getAllEvents(){
        db= new H2DatabaseAllSports();
        events = new ArrayList<>();
        List<Event> simpleEvents = db.fetchAll();
        for (int i = 0; i < simpleEvents.size(); i++) {
            List<Team> teamsOfEvent = db.fetchTeamNamesFrom(simpleEvents.get(i).getId());
            //Team team = teamsOfEvent.get(0);
            //fetch category of team from database
           // List<String> category = db.fetchCategoryFrom(team.getTeamName());
            Event event = new Event();
            event.setId(simpleEvents.get(i).getId());
            event.setDateOfEvent(simpleEvents.get(i).getDateOfEvent());
            //event.setCategory(category.get(0));
            event.setTeams(teamsOfEvent);
            events.add(event);
        }
        return events;
    }
}
