package com.eet.controllers;

import com.eet.dao.EventDao;
import com.eet.models.Event;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventController {

    private EventDao eventDao;

    public EventController() {
        eventDao = new EventDao();
    }

    public Object[][] getBookings (String id) {
        List<Event> events = eventDao.findBookingsByUserId(id);
        if(events.isEmpty()) {
            return null;
        } else {
            return castToArray(events);
        }
    }

    public Object[][] getBookings (String id, String name) {
        List<Event> events = eventDao.findBookingsByUserIdAndEventName(id, name);
        if(events.isEmpty()) {
            return null;
        } else {
            return castToArray(events);
        }
    }

    private Object[][] castToArray(List<Event> events) {
        Object[][] objects = new Object[events.size()][8];
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            objects[i] = format(event);
        }
        return objects;
    }

    private Object[] format(Event event) {
        Object[] object = new Object[8];
        String spaces = event.getAvailableSpaces() + "/" + event.getSpaceLimitations();
        String type = event.getType().toString().toLowerCase();
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        long minutes = ChronoUnit.MINUTES.between(event.getStartDate(), event.getEndDate());
        long hours = minutes / 60;
        minutes = minutes % 60;
        String duration = hours + "h " + minutes + "m";
        String stringDate = event.getStartDate().toString();
        stringDate = stringDate.replace('T', ' ');

        object[0] = event.getTitle();
        object[1] = type;
        object[2] = event.getDescription();
        object[3] = stringDate;
        object[4] = duration;
        object[5] = spaces;
        object[6] = event.getPlace();
        object[7] = "Cancel";
        return object;
    }
}
