package com.eet.mappers;

import com.eet.models.Event;
import com.eet.models.Repetition;
import com.eet.models.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EventMapper {

    public static Event toJava(ResultSet resultSet) throws SQLException {
        Event event;
        Repetition repetition = new Repetition();
        repetition.setTime_of_the_day(resultSet.getString("time_of_the_day"));
        repetition.setDay_of_the_week(resultSet.getString("day_of_the_week"));
        repetition.setDay_of_the_month(resultSet.getString("day_of_the_month"));
        repetition.setDay_of_the_year(resultSet.getString("day_of_the_year"));
        event = new Event();
        event.setId(resultSet.getInt("id"));
        event.setRepetition(repetition);
        Type type = resultSet.getInt("type") == 1 ? Type.ONLINE : Type.PHYSICAL;
        event.setType(type);
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        String date = resultSet.getString("date_time");
        LocalDateTime dateTime = DateTimeParser.fromSql(date);
        event.setDateTime(dateTime);
        event.setSpaceLimitations(resultSet.getInt("space_limitations"));
        event.setPlace(resultSet.getString("place"));
        return event;
    }
}
