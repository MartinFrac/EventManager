package com.eet.dao;

import com.eet.database.SqliteConnection;
import com.eet.mappers.EventMapper;
import com.eet.models.Event;
import com.eet.models.Filters;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public List<Event> findRepeatableBookingsByUserId(String id) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "e.type_id, e.title, e.description, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id = ?" +
                "AND e.repetition_id IS NOT NULL";
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

    public List<Event> findNonRepeatableBookingsByUserId(String id) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.id, e.repetition_id, e.type_id, e.title, e.description, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id = ?" +
                "AND e.repetition_id IS NULL";
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

    public List<Event> findRepeatableEventsByUserIdNotBookings(String id) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT DISTINCT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "e.type_id, e.title, e.description, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id != ?" +
                "AND e.repetition_id IS NOT NULL";
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

    public List<Event> findNonRepeatableEventsByUserIdNotBookings(String id) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT DISTINCT e.id, e.repetition_id, e.type_id, e.title, e.description, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id != ?" +
                "AND e.repetition_id IS NULL";
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

    public List<Event> findRepeatableBookingsByUserIdAndEventName(String id, String name) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, e.type_id, " +
                "t.name, e.title, e.description, e.start, e.end, e.space_limitations, e.available_spaces, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "INNER JOIN TYPE AS t on e.type_id = t.id " +
                "WHERE e.repetition_id IS NOT NULL " +
                "AND b.user_id = ? " +
                "AND (CASE WHEN ? = '' THEN TRUE ELSE e.title LIKE '%'|| ? ||'%' END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, name);
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

    public List<Event> findNonRepeatableBookingsByUserIdAndEventName(String id, String name) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT e.id, e.repetition_id, e.type_id, t.name, e.title, e.description, e.start, e.end, e.space_limitations, e.available_spaces, e.place " +
                "FROM EVENT AS e INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "INNER JOIN TYPE AS t on e.type_id = t.id " +
                "WHERE e.repetition_id IS NULL " +
                "AND b.user_id = ? " +
                "AND (CASE WHEN ? = '' THEN TRUE ELSE e.title LIKE '%'|| ? ||'%' END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, name);
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

    public List<Event> findRepeatableEventsByUserIdAndEventNameNotBookings(String id, String name) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT DISTINCT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, e.type_id, " +
                "t.name, e.title, e.description, e.start, e.end, e.space_limitations, e.available_spaces, e.place " +
                "FROM EVENT AS e LEFT JOIN REPETITION AS r ON r.id = e.repetition_id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "INNER JOIN TYPE AS t on e.type_id = t.id " +
                "WHERE e.repetition_id IS NOT NULL " +
                "AND b.user_id != ? " +
                "AND (CASE WHEN ? = '' THEN TRUE ELSE e.title LIKE '%'|| ? ||'%' END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, name);
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

    public List<Event> findNonRepeatableBEventsByUserIdAndEventNameNotBookings(String id, String name) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT DISTINCT e.id, e.repetition_id, e.type_id, t.name, e.title, e.description, e.start, e.end, e.space_limitations, e.available_spaces, e.place " +
                "FROM EVENT AS e INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "INNER JOIN TYPE AS t on e.type_id = t.id " +
                "WHERE e.repetition_id IS NULL " +
                "AND b.user_id != ? " +
                "AND (CASE WHEN ? = '' THEN TRUE ELSE e.title LIKE '%'|| ? ||'%' END)";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, name);
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

    public List<Event> findNonRepeatableBookingsWithFilters(String id, Filters filters) {
        List<Event> events = new ArrayList<>();
        String filterQuery = "SELECT e.id, e.repetition_id, e.description, e.type_id, e.title, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id = (CASE WHEN :user_id = '' THEN b.user_id ELSE :user_id END) " +
                "AND e.repetition_id IS NULL " +
                "AND (CASE WHEN :event_title = '' THEN TRUE ELSE e.title LIKE '%'|| :event_title ||'%' END) " +
                "AND e.type_id = (CASE WHEN :event_type = -1 THEN e.type_id ELSE :event_type END) " +
                "AND (CASE WHEN :event_place = '' THEN TRUE ELSE e.place LIKE '%'|| :event_place ||'%' END) " +
                "AND (CASE WHEN :start_date IS NULL THEN TRUE ELSE e.start BETWEEN :start_date AND :end_date END) " +
                "AND e.available_spaces >= (CASE WHEN :available_spaces = -1 THEN TRUE ELSE :available_spaces END) " +
                "AND (CASE WHEN :keywords = '' THEN TRUE ELSE e.description_id in (SELECT id FROM description WHERE description MATCH (SELECT var FROM variables)) END);";

        String insertKeywordsQuery = "INSERT INTO variables (var) VALUES (?)";
        String clearVariablesQuery = "DELETE FROM variables";

        try (Connection connection = SqliteConnection.getConnection();) {

            if (!filters.getKeywords().equals("")) {
                PreparedStatement insertKeywords = connection.prepareStatement(insertKeywordsQuery);
                insertKeywords.setString(1, filters.getKeywords());
                insertKeywords.executeUpdate();
                insertKeywords.close();
            }

            NamedParamStatement filterBookings = new NamedParamStatement(connection, filterQuery);

            String startDate = null;
            String endDate = null;
            if (filters.getStartDate()!=null) {
                startDate = filters.getStartDate().toString();
                endDate = filters.getEndDate().toString();
            }

            filterBookings.setString("user_id", id);
            filterBookings.setString("event_title", filters.getTitle());
            filterBookings.setInt("event_type", filters.getType());
            filterBookings.setString("event_place", filters.getPlace());
            filterBookings.setString("start_date", startDate);
            filterBookings.setString("end_date", endDate);
            filterBookings.setInt("available_spaces", filters.getAvailableSpaces());
            filterBookings.setString("keywords", filters.getKeywords());

            ResultSet resultSet = filterBookings.executeQuery();

            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                events.add(event);
            }
            filterBookings.close();

            PreparedStatement clearVariables = connection.prepareStatement(clearVariablesQuery);
            clearVariables.executeUpdate();
            clearVariables.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public void deleteBookingByIdAndUserId(int eventId, String userId) {
        String query1 = "DELETE FROM BOOKING WHERE event_id = ? AND user_id = ?";
        String query2 = "UPDATE EVENT SET available_spaces = available_spaces + 1 WHERE id = ?";
        try (Connection connection = SqliteConnection.getConnection();
             PreparedStatement pstmt1 = connection.prepareStatement(query1);
             PreparedStatement pstmt2 = connection.prepareStatement(query2)) {
            pstmt1.setInt(1, eventId);
            pstmt1.setString(2, userId);
            pstmt1.executeUpdate();
            pstmt2.setInt(1, eventId);
            pstmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> findRepeatableBookingsWithFilters(String id, Filters filters) {
        List<Event> events = new ArrayList<>();
        String filterQuery = "SELECT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "e.description, e.type_id, e.title, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e INNER JOIN REPETITION AS r on e.repetition_id = r.id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id = (CASE WHEN :user_id = '' THEN b.user_id ELSE :user_id END) " +
                "AND e.repetition_id IS NOT NULL " +
                "AND (CASE WHEN :event_title = '' THEN TRUE ELSE e.title LIKE '%'|| :event_title ||'%' END) " +
                "AND e.type_id = (CASE WHEN :event_type = -1 THEN e.type_id ELSE :event_type END) " +
                "AND (CASE WHEN :event_place = '' THEN TRUE ELSE e.place LIKE '%'|| :event_place ||'%' END) " +
                "AND (CASE WHEN :start_date IS NULL THEN TRUE ELSE e.start BETWEEN :start_date AND :end_date END) " +
                "AND e.available_spaces >= (CASE WHEN :available_spaces = -1 THEN TRUE ELSE :available_spaces END) " +
                "AND (CASE WHEN :keywords = '' THEN TRUE ELSE e.description_id in (SELECT id FROM description WHERE description MATCH (SELECT var FROM variables)) END);";

        String insertKeywordsQuery = "INSERT INTO variables (var) VALUES (?)";
        String clearVariablesQuery = "DELETE FROM variables";

        try (Connection connection = SqliteConnection.getConnection();) {

            if (!filters.getKeywords().equals("")) {
                PreparedStatement insertKeywords = connection.prepareStatement(insertKeywordsQuery);
                insertKeywords.setString(1, filters.getKeywords());
                insertKeywords.executeUpdate();
                insertKeywords.close();
            }

            NamedParamStatement filterBookings = new NamedParamStatement(connection, filterQuery);

            String startDate = null;
            String endDate = null;
            if (filters.getStartDate()!=null) {
                startDate = filters.getStartDate().toString();
                endDate = filters.getEndDate().toString();
            }

            filterBookings.setString("user_id", id);
            filterBookings.setString("event_title", filters.getTitle());
            filterBookings.setInt("event_type", filters.getType());
            filterBookings.setString("event_place", filters.getPlace());
            filterBookings.setString("start_date", startDate);
            filterBookings.setString("end_date", endDate);
            filterBookings.setInt("available_spaces", filters.getAvailableSpaces());
            filterBookings.setString("keywords", filters.getKeywords());

            ResultSet resultSet = filterBookings.executeQuery();

            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                events.add(event);
            }
            filterBookings.close();

            PreparedStatement clearVariables = connection.prepareStatement(clearVariablesQuery);
            clearVariables.executeUpdate();
            clearVariables.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Event> findNonRepeatableEventsWithFiltersNotBookings(String id, Filters filters) {
        List<Event> events = new ArrayList<>();
        String filterQuery = "SELECT DISTINCT e.id, e.repetition_id, e.description, e.type_id, e.title, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id != (CASE WHEN :user_id = '' THEN b.user_id ELSE :user_id END) " +
                "AND e.repetition_id IS NULL " +
                "AND (CASE WHEN :event_title = '' THEN TRUE ELSE e.title LIKE '%'|| :event_title ||'%' END) " +
                "AND e.type_id = (CASE WHEN :event_type = -1 THEN e.type_id ELSE :event_type END) " +
                "AND (CASE WHEN :event_place = '' THEN TRUE ELSE e.place LIKE '%'|| :event_place ||'%' END) " +
                "AND (CASE WHEN :start_date IS NULL THEN TRUE ELSE e.start BETWEEN :start_date AND :end_date END) " +
                "AND e.available_spaces >= (CASE WHEN :available_spaces = -1 THEN TRUE ELSE :available_spaces END) " +
                "AND (CASE WHEN :keywords = '' THEN TRUE ELSE e.description_id in (SELECT id FROM description WHERE description MATCH (SELECT var FROM variables)) END);";

        String insertKeywordsQuery = "INSERT INTO variables (var) VALUES (?)";
        String clearVariablesQuery = "DELETE FROM variables";

        try (Connection connection = SqliteConnection.getConnection();) {

            if (!filters.getKeywords().equals("")) {
                PreparedStatement insertKeywords = connection.prepareStatement(insertKeywordsQuery);
                insertKeywords.setString(1, filters.getKeywords());
                insertKeywords.executeUpdate();
                insertKeywords.close();
            }

            NamedParamStatement filterBookings = new NamedParamStatement(connection, filterQuery);

            String startDate = null;
            String endDate = null;
            if (filters.getStartDate()!=null) {
                startDate = filters.getStartDate().toString();
                endDate = filters.getEndDate().toString();
            }

            filterBookings.setString("user_id", id);
            filterBookings.setString("event_title", filters.getTitle());
            filterBookings.setInt("event_type", filters.getType());
            filterBookings.setString("event_place", filters.getPlace());
            filterBookings.setString("start_date", startDate);
            filterBookings.setString("end_date", endDate);
            filterBookings.setInt("available_spaces", filters.getAvailableSpaces());
            filterBookings.setString("keywords", filters.getKeywords());

            ResultSet resultSet = filterBookings.executeQuery();

            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                events.add(event);
            }
            filterBookings.close();

            PreparedStatement clearVariables = connection.prepareStatement(clearVariablesQuery);
            clearVariables.executeUpdate();
            clearVariables.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<Event> findRepeatableEventsWithFiltersNotBookings(String id, Filters filters) {
        List<Event> events = new ArrayList<>();
        String filterQuery = "SELECT DISTINCT e.id, e.repetition_id, r.time_of_the_day, r.day_of_the_week, r.day_of_the_month, " +
                "e.description, e.type_id, e.title, e.start, e.end, e.available_spaces, e.space_limitations, e.place " +
                "FROM EVENT AS e INNER JOIN REPETITION AS r on e.repetition_id = r.id " +
                "INNER JOIN BOOKING AS b on b.event_id = e.id " +
                "WHERE b.user_id != (CASE WHEN :user_id = '' THEN b.user_id ELSE :user_id END) " +
                "AND e.repetition_id IS NOT NULL " +
                "AND (CASE WHEN :event_title = '' THEN TRUE ELSE e.title LIKE '%'|| :event_title ||'%' END) " +
                "AND e.type_id = (CASE WHEN :event_type = -1 THEN e.type_id ELSE :event_type END) " +
                "AND (CASE WHEN :event_place = '' THEN TRUE ELSE e.place LIKE '%'|| :event_place ||'%' END) " +
                "AND (CASE WHEN :start_date IS NULL THEN TRUE ELSE e.start BETWEEN :start_date AND :end_date END) " +
                "AND e.available_spaces >= (CASE WHEN :available_spaces = -1 THEN TRUE ELSE :available_spaces END) " +
                "AND (CASE WHEN :keywords = '' THEN TRUE ELSE e.description_id in (SELECT id FROM description WHERE description MATCH (SELECT var FROM variables)) END);";

        String insertKeywordsQuery = "INSERT INTO variables (var) VALUES (?)";
        String clearVariablesQuery = "DELETE FROM variables";

        try (Connection connection = SqliteConnection.getConnection();) {

            if (!filters.getKeywords().equals("")) {
                PreparedStatement insertKeywords = connection.prepareStatement(insertKeywordsQuery);
                insertKeywords.setString(1, filters.getKeywords());
                insertKeywords.executeUpdate();
                insertKeywords.close();
            }

            NamedParamStatement filterBookings = new NamedParamStatement(connection, filterQuery);

            String startDate = null;
            String endDate = null;
            if (filters.getStartDate()!=null) {
                startDate = filters.getStartDate().toString();
                endDate = filters.getEndDate().toString();
            }

            filterBookings.setString("user_id", id);
            filterBookings.setString("event_title", filters.getTitle());
            filterBookings.setInt("event_type", filters.getType());
            filterBookings.setString("event_place", filters.getPlace());
            filterBookings.setString("start_date", startDate);
            filterBookings.setString("end_date", endDate);
            filterBookings.setInt("available_spaces", filters.getAvailableSpaces());
            filterBookings.setString("keywords", filters.getKeywords());

            ResultSet resultSet = filterBookings.executeQuery();

            while (resultSet.next()) {
                Event event = EventMapper.toJava(resultSet);
                events.add(event);
            }
            filterBookings.close();

            PreparedStatement clearVariables = connection.prepareStatement(clearVariablesQuery);
            clearVariables.executeUpdate();
            clearVariables.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}
