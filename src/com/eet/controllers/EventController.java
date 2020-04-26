package com.eet.controllers;

import com.eet.dao.EventDao;
import com.eet.models.Event;

import java.util.List;

public class EventController {

    private EventDao eventDao;

    public EventController() {
        eventDao = new EventDao();
    }

    public Object[][] getBookings (String id) {
        List<Event> events = eventDao.findEventsByUserId(id);
        if(events.isEmpty()) {
            return null;
        } else {
            Object[][] objects = new Object[events.size()][7];
            for (int i = 0; i < events.size(); i++) {
                objects[i][0] = events.get(i).getTitle();
                objects[i][1] = events.get(i).getType().toString().toLowerCase();
                objects[i][2] = events.get(i).getDescription();
                objects[i][3] = events.get(i).getDateTime();
                objects[i][4] = events.get(i).getSpaceLimitations();
                objects[i][5] = events.get(i).getPlace();
                objects[i][6] = "Cancel";
            }
            return objects;
        }
    }
}
