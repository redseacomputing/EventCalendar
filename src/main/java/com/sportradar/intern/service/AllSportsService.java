package com.sportradar.intern.service;

import com.sportradar.intern.dto.Event;
import com.sportradar.intern.persistence.db.H2DatabaseAllSports;

import java.util.List;

public class AllSportsService {

    H2DatabaseAllSports db;
    List<Event> events;


    public List<Event> getAllEvents(){
        db= new H2DatabaseAllSports();
        db.getAllEvents();
        List<Event> result = db.getEvents();
        return result;

    }
}
