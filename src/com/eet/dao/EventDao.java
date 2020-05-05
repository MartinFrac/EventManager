package com.eet.dao;
import com.eet.mappers.UserMapper;
import com.eet.models.User;

import com.eet.database.SqliteConnection;
import com.eet.mappers.EventMapper;
import com.eet.models.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public List<Event> findEventsByUserId(String id) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "r.day_of_the_year, e.type, e.title, e.description, e.date_time, e.space_limitations, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }


    public List<Event> getEventsList() {
        List<Event> eventsList = new ArrayList<Event>();
        String query = "SELECT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "r.day_of_the_year, e.type, e.title, e.description, e.date_time, e.space_limitations, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id ";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                eventsList.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventsList;
    }



}
