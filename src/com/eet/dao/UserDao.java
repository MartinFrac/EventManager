package com.eet.dao;

import com.eet.database.SqliteConnection;
import com.eet.mappers.UserMapper;
import com.eet.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User findById(String id) {
        String query = "SELECT id, name, surname, role_id, password FROM USER WHERE id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return UserMapper.fromSql(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(User user) {
        String query = "INSERT INTO USER (id, name, surname, role_id, password) " +
                "VALUES (?,?,?,?,?)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSurname());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getRole().getLevel());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findWithFilters(User user) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, surname, role_id, password FROM USER " +
                "WHERE (CASE WHEN :id = '' THEN TRUE ELSE id = :id END) " +
                "AND (CASE WHEN :name = '' THEN TRUE ELSE name LIKE '%'|| :name ||'%' END) " +
                "AND (CASE WHEN :surname = '' THEN TRUE ELSE surname LIKE '%'|| :surname ||'%' END) " +
                "AND (CASE WHEN :role_id = 0 THEN TRUE ELSE role_id = :role_id END);";

        try (Connection connection = SqliteConnection.getConnection()) {
            NamedParamStatement namedParamStatement = new NamedParamStatement(connection, query);
            namedParamStatement.setString("id", user.getId());
            namedParamStatement.setString("name", user.getName());
            namedParamStatement.setString("surname", user.getSurname());
            namedParamStatement.setInt("role_id", user.getRole().getLevel());
            ResultSet resultSet = namedParamStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findNonPrivileged() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, surname, role_id, password FROM USER WHERE role_id = 3";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findPrivileged() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, surname, role_id, password FROM USER WHERE role_id = 2";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findPrivilegedById(String id) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, surname, role_id, password FROM USER WHERE role_id = 2 AND (CASE WHEN ? = '' THEN TRUE ELSE id = ? END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findNonPrivilegedById(String id) {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, surname, role_id, password FROM USER WHERE role_id = 3 AND (CASE WHEN ? = '' THEN TRUE ELSE id = ? END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void grantPrivileges(String userId) {
        String query = "UPDATE USER SET role_id = 2 WHERE id = ?";

        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void revokePrivileges(String userId) {
        String query = "UPDATE USER SET role_id = 3 WHERE id = ?";

        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findPrivilegedByBookingEventId(int eventId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.role_id, u.password FROM USER AS u " +
                "INNER JOIN BOOKING AS b on u.id = b.user_id WHERE u.role_id = 2 AND b.event_id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findNonPrivilegedByBookingEventId(int eventId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.role_id, u.password FROM USER AS u " +
                "INNER JOIN BOOKING AS b on u.id = b.user_id WHERE u.role_id = 3 AND b.event_id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findPrivilegedByIdAndBookingEventId(String id, int eventId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.role_id, u.password FROM USER AS u " +
                "INNER JOIN BOOKING AS b on u.id = b.user_id WHERE u.role_id = 2 AND b.event_id = ? " +
                "AND u.id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findNonPrivilegedByIdAndBookingEventId(String id, int eventId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.role_id, u.password FROM USER AS u " +
                "INNER JOIN BOOKING AS b on u.id = b.user_id WHERE u.role_id = 3 AND b.event_id = ? " +
                "AND u.id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findByBookingEventIdWithFilters(User user, int eventId) {
        List<User> users = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.surname, u.role_id, u.password FROM USER AS u " +
                "INNER JOIN BOOKING AS b on u.id = b.user_id " +
                "WHERE (CASE WHEN :id = '' THEN TRUE ELSE u.id = :id END) " +
                "AND (CASE WHEN :name = '' THEN TRUE ELSE u.name LIKE '%'|| :name ||'%' END) " +
                "AND (CASE WHEN :surname = '' THEN TRUE ELSE u.surname LIKE '%'|| :surname ||'%' END) " +
                "AND (CASE WHEN :role_id = 0 THEN TRUE ELSE u.role_id = :role_id END) " +
                "AND b.event_id = :event_id ;";

        try (Connection connection = SqliteConnection.getConnection()) {
            NamedParamStatement namedParamStatement = new NamedParamStatement(connection, query);
            namedParamStatement.setString("id", user.getId());
            namedParamStatement.setString("name", user.getName());
            namedParamStatement.setString("surname", user.getSurname());
            namedParamStatement.setInt("role_id", user.getRole().getLevel());
            namedParamStatement.setInt("event_id", eventId);
            ResultSet resultSet = namedParamStatement.executeQuery();

            while (resultSet.next()) {
                users.add(UserMapper.fromSql(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
