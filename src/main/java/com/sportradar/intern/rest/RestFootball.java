package com.sportradar.intern.rest;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.persistence.db.H2Football;
import com.sportradar.intern.service.FootballService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar/football")
public class RestFootball {

    FootballService footballService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        footballService = new FootballService();
        return footballService.getAllEvents();
    }
}