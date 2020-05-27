package com.eet.dao;

import com.eet.database.SqliteConnection;
import com.eet.mappers.EventMapper;
import com.eet.models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventOrganisingDao {

    public Boolean findByUserIdAndEventId(String userId, int eventId) {
        String query = "SELECT id FROM EVENT_ORGANISING WHERE user_id = ? AND event_id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, userId);
            pstmt.setInt(2, eventId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteById(int eventId) {
        String query = "DELETE FROM EVENT_ORGANISING WHERE event_id = ?";
        String deleteBookings = "DELETE FROM BOOKING WHERE event_id = ?";
        String deleteEvent = "DELETE FROM EVENT WHERE id = ?";
        try (Connection connection = SqliteConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, eventId);
            pstmt.executeUpdate();
            pstmt.close();

            PreparedStatement deleteBookingsPstmt = connection.prepareStatement(deleteBookings);
            deleteBookingsPstmt.setInt(1, eventId);
            deleteBookingsPstmt.executeUpdate();
            deleteBookingsPstmt.close();

            PreparedStatement deleteEventPstmt = connection.prepareStatement(deleteEvent);
            deleteEventPstmt.setInt(1, eventId);
            deleteEventPstmt.executeUpdate();
            deleteEventPstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
