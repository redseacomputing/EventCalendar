package com.sportradar.intern.persistence.officialAPI;

import com.sportradar.intern.persistence.DataEventAccess;

public class SportradarWebAPI implements DataEventAccess {

    //Here can be provided the access to the sportsradar web API
    //https://developer.sportradar.com/

    final String API_KEY = "7896956756354656323080";
    final String URI = "nfl/official/trial/v2/interceptionOfTheDay";

    @Override
    public void getAllEvents() {

    }
}