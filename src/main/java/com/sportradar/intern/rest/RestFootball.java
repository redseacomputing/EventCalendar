package com.sportradar.intern.rest;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.persistence.db.H2Football;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar/football")
public class RestFootball {

    H2Football soccerDatabaseController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        soccerDatabaseController = new H2Football();
        soccerDatabaseController.getAllEvents();
        return soccerDatabaseController.getEvents();
    }
}