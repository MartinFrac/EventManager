package com.eet.controllers;

import com.eet.dao.BookingDao;

public class BookingController {

    private BookingDao bookingDao;

    public BookingController() {
        bookingDao = new BookingDao();
    }

    public void create(String userId, int eventId) {
        bookingDao.create(userId, eventId);
    }
}
