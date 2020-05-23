package com.eet.dao;

import com.eet.database.SqliteConnection;
import com.eet.mappers.UserMapper;
import com.eet.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User findById(String id) {
        String query = "SELECT id, name, surname, password, role_id FROM USER WHERE id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return UserMapper.toJava(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(User user) {
        String query = "INSERT INTO USER (id, name, surname, password, role_id) " +
                "VALUES (?,?,?,?,?)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSurname());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getRole());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
