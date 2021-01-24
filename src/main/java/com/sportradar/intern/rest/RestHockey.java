package com.sportradar.intern.rest;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.persistence.H2Hockey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar/hockey")
public class RestHockey {

    H2Hockey hockeyDatabaseController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        hockeyDatabaseController = new H2Hockey();
        hockeyDatabaseController.hockeyH2Events();
        return hockeyDatabaseController.getEvents();
    }
}