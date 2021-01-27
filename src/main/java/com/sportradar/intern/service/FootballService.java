package com.sportradar.intern.service;

import com.sportradar.intern.dto.DTOEvent;
import com.sportradar.intern.dto.DTOTeam;
import com.sportradar.intern.persistence.db.H2Event;
import com.sportradar.intern.persistence.db.H2Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FootballService {

    H2Event eventController;
    H2Team teamController;
    List<DTOEvent> DTOEvents;
    List<DTOEvent> result;


    public List<DTOEvent> getAllEvents(){
        eventController = new H2Event();
        teamController = new H2Team();
        DTOEvents = new ArrayList<>();
        List<DTOEvent> simpleDTOEvents = eventController.fetchAllFromCategory("Soccer");
        for (int i = 0; i < simpleDTOEvents.size(); i++) {
            List<DTOTeam> teamsOfEvent = teamController.fetchTeamNamesFrom(simpleDTOEvents.get(i).getId());
            DTOEvent DTOEvent = new DTOEvent();
            DTOEvent.setId(simpleDTOEvents.get(i).getId());
            DTOEvent.setDateOfEvent(simpleDTOEvents.get(i).getDateOfEvent());
            DTOEvent.setTeams(teamsOfEvent);
            DTOEvents.add(DTOEvent);
        }
        result = new ArrayList<>(
                new HashSet<>(DTOEvents));
        return result;
    }
}
