package com.sportradar.intern.rest;

import com.sportradar.intern.domain.Event;
import com.sportradar.intern.dto.DTOEvent;
import com.sportradar.intern.service.HockeyService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("calendar/hockey")
public class RestHockey {

    HockeyService hockeyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> all() {
        hockeyService = new HockeyService();
        return hockeyService.getAllEvents();
    }
}