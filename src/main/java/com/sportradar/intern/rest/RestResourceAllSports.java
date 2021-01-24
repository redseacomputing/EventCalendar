package com.sportradar.intern.rest;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.persistence.db.H2DatabaseAllSports;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar")
public class RestResourceAllSports {

    H2DatabaseAllSports databaseController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        databaseController = new H2DatabaseAllSports();
        databaseController.getAllEvents();
        return databaseController.getEvents();
    }
}