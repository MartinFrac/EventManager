package com.eet.dao;

import com.eet.database.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDao {

    public void create(String userId, int eventId) {
        String query = "INSERT INTO BOOKING (user_id, event_id) VALUES (?,?);";
        String updateSpaces = "UPDATE EVENT SET available_spaces = available_spaces - 1 WHERE id = ?;";
        try (Connection connection = SqliteConnection.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setInt(2, eventId);
            pstmt.executeUpdate();
            pstmt.close();

            PreparedStatement updateSpacesPstmt = connection.prepareStatement(updateSpaces);
            updateSpacesPstmt.setInt(1, eventId);
            updateSpacesPstmt.executeUpdate();
            updateSpacesPstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
