package com.eet.controllers;

import com.eet.dao.EventDao;
import com.eet.models.Event;
import com.eet.models.Filters;
import com.eet.models.Repetition;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class EventController {

    private EventDao eventDao;

    public EventController() {
        eventDao = new EventDao();
    }

    public Object[][] getRepeatableBookings (String id) {
        List<Event> events = eventDao.findRepeatableBookingsByUserId(id);
        return castToArray(events);
    }

    public Object[][] getNonRepeatableBookings (String id) {
        List<Event> events = eventDao.findNonRepeatableBookingsByUserId(id);
        return castToArray(events);
    }

    public Object[][] getRepeatableBookings (String id, String name) {
        List<Event> events = eventDao.findRepeatableBookingsByUserIdAndEventName(id, name);
        return castToArray(events);
    }

    public Object[][] getNonRepeatableBookings (String id, String name) {
        List<Event> events = eventDao.findNonRepeatableBookingsByUserIdAndEventName(id, name);
        return castToArray(events);
    }

    public Object[][] getRepeatableBookingswithFilters(String id, Filters filters) {
        List<Event> events = eventDao.findRepeatableBookingsWithFilters(id, filters);
        return castToArray(events);
    }

    public Object[][] getNonRepeatableBookingsWithFilters(String id, Filters filters) {
        List<Event> events = eventDao.findNonRepeatableBookingsWithFilters(id, filters);
        return castToArray(events);
    }

    public void deleteBooking (int eventId, String userId) {
        eventDao.deleteBookingByIdAndUserId(eventId, userId);
    }

    private Object[][] castToArray(List<Event> events) {
        if (events.isEmpty()) {
            return null;
        }
        Object[][] objects = new Object[events.size()][8];
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            objects[i] = format(event);
        }
        return objects;
    }

    private Object[] format(Event event) {
        Object[] object = new Object[9];
        String spaces = event.getAvailableSpaces() + "/" + event.getSpaceLimitations();
        String type = event.getType().toString().toLowerCase();
        type = type.substring(0,1).toUpperCase() + type.substring(1);
        LocalDateTime startDate = event.getStartDate();
        LocalDateTime endDate = event.getEndDate();
        long minutes = ChronoUnit.MINUTES.between(startDate, endDate);
        long hours = minutes / 60;
        minutes = minutes % 60;
        String duration = hours + "h " + minutes + "m";
        object[0] = event.getTitle();
        object[1] = type;
        object[2] = event.getDescription();
        object[3] = formatDate(event);
        object[4] = duration;
        object[5] = spaces;
        object[6] = event.getPlace();
        object[7] = "Cancel";
        object[8] = event.getId();
        return object;
    }

    private String formatDate(Event event) {
        String result = "At ";
        Repetition repetition = event.getRepetition();
        if (repetition==null) {
            String stringDate = event.getStartDate().toString();
            stringDate = stringDate.replace('T', ' ');
            return stringDate;
        }
        String time = repetition.getTime_of_the_day();
        result += time + " every ";
        String day_of_the_week = repetition.getDay_of_the_week();
        if (day_of_the_week!=null) {
            result += day_of_the_week + " ";
        }
        String day_of_the_month = repetition.getDay_of_the_month();
        if (day_of_the_month!=null) {
            result += day_of_the_month;
            switch (day_of_the_month) {
                case "1": result += "st";
                    break;
                case "2": result += "nd";
                    break;
                case "3": result += "rd";
                    break;
                default: result += "th";
            }
            result += " day of a month";
        }
        return result;
    }
}
