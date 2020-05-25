package com.eet.mappers;

import com.eet.models.Event;
import com.eet.models.Repetition;
import com.eet.models.Type;
import com.eet.ui.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class EventMapper {

    public static Event fromSql(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        if (resultSet.getInt("repetition_id") != 0) {
            Repetition repetition = new Repetition();
            repetition.setTime_of_the_day(resultSet.getString("time_of_the_day"));
            repetition.setDay_of_the_week(resultSet.getString("day_of_the_week"));
            repetition.setDay_of_the_month(resultSet.getString("day_of_the_month"));
            event.setRepetition(repetition);
        }
        event.setId(resultSet.getInt("id"));
        Type type = resultSet.getInt("type_id") == 1 ? Type.ONLINE : Type.PHYSICAL;
        event.setType(type);
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        String startDate = resultSet.getString("start");
        LocalDateTime start = DateTimeParser.toJava(startDate);
        event.setStartDate(start);
        String endDate = resultSet.getString("end");
        LocalDateTime end = DateTimeParser.toJava(endDate);
        event.setEndDate(end);
        event.setAvailableSpaces(resultSet.getInt("available_spaces"));
        event.setSpaceLimitations(resultSet.getInt("space_limitations"));
        event.setPlace(resultSet.getString("place"));
        return event;
    }

    public static Event fromUI(HashMap<String, Object> map) {
        Event event = new Event();
        if ((Boolean) map.get("isRepeatable")) {
            Repetition repetition = new Repetition();
            repetition.setTime_of_the_day((String) map.get("time"));
            if (map.get("weekDay")!=null) {
                repetition.setDay_of_the_week((String) map.get("weekDay"));
            }
            if (map.get("monthDay")!=null) {
                repetition.setDay_of_the_month((String) map.get("monthDay"));
            }
            event.setRepetition(repetition);
        }
        Type type = map.get("type").equals("online") ? Type.ONLINE : Type.PHYSICAL;
        event.setType(type);
        event.setTitle((String) map.get("name"));
        event.setDescription((String) map.get("description"));
        Date date1 = (Date) map.get("startDate");
        Date date2 = (Date) map.get("endDate");
        LocalDateTime start = new Timestamp(date1.getTime()).toLocalDateTime();
        LocalDateTime end = new Timestamp(date2.getTime()).toLocalDateTime();
        String startHour = (String) map.get("startHour");
        String startMinute = (String) map.get("startMinute");
        String endHour = (String) map.get("endHour");
        String endMinute = (String) map.get("endMinute");
        start = start.withHour(Integer.parseInt(startHour));
        start = start.withMinute(Integer.parseInt(startMinute));
        end = end.withHour(Integer.parseInt(endHour));
        end = end.withMinute(Integer.parseInt(endMinute));
        event.setStartDate(start);
        event.setEndDate(end);
        event.setAvailableSpaces((Integer) map.get("spaceLimit"));
        event.setSpaceLimitations((Integer) map.get("spaceLimit"));
        event.setPlace((String) map.get("place"));

        return event;
    }
}
