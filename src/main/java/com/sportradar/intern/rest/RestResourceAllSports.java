package com.sportradar.intern.rest;

import com.sportradar.intern.domain.Event;
import com.sportradar.intern.dto.DTOEvent;
import com.sportradar.intern.service.AllSportsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar")
public class RestResourceAllSports {

    AllSportsService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        service = new AllSportsService();
        return service.getAllEvents();
    }
}