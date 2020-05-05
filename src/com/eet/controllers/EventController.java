package com.eet.controllers;

import com.eet.dao.EventDao;
import com.eet.models.Event;

import java.util.Date;
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
    public Object getEvents() {
        List<Event> searchEvents = eventDao.getEventsList();
        if (searchEvents.isEmpty()) {
            return null;
        }
        else{
            Object[][] objects = new Object[searchEvents.size()][7];
            for (int i = 0; i < searchEvents.size(); i++) {
                objects[i][0] = searchEvents.get(i).getTitle();
                objects[i][1] = searchEvents.get(i).getType().toString().toLowerCase();
                objects[i][2] = searchEvents.get(i).getDescription();
                objects[i][3] = searchEvents.get(i).getDateTime();
                objects[i][4] = searchEvents.get(i).getSpaceLimitations();
                objects[i][5] = searchEvents.get(i).getPlace();
                objects[i][6] = "Book";
            }
            return objects;
        }

    }

    public Object cancelEvents() {
        List<Event> searchEvents = eventDao.getEventsList();
        if (searchEvents.isEmpty()) {
            return null;
        }
        else{
            Object[][] objects = new Object[searchEvents.size()][7];
            for (int i = 0; i < searchEvents.size(); i++) {
                objects[i][0] = searchEvents.get(i).getTitle();
                objects[i][1] = searchEvents.get(i).getType().toString().toLowerCase();
                objects[i][2] = searchEvents.get(i).getDescription();
                objects[i][3] = searchEvents.get(i).getDateTime();
                objects[i][4] = searchEvents.get(i).getSpaceLimitations();
                objects[i][5] = searchEvents.get(i).getPlace();
                objects[i][6] = "Cancel";
            }
            return objects;
        }
    }




}
