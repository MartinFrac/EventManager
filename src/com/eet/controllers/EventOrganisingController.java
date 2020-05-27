package com.eet.controllers;

import com.eet.dao.EventOrganisingDao;

public class EventOrganisingController {

    private EventOrganisingDao eventOrganisingDao;

    public EventOrganisingController() {
        eventOrganisingDao = new EventOrganisingDao();
    }

    public Boolean checkIfExists(String userId, int eventId) {
        return eventOrganisingDao.findByUserIdAndEventId(userId, eventId);
    }

    public void delete(int eventId) {
        eventOrganisingDao.deleteById(eventId);
    }
}
