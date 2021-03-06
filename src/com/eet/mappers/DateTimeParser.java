package com.eet.mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime toJava(String date) {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            dateTime = LocalDate.parse(date, dateFormatter).atStartOfDay();
        }
        return dateTime;
    }

    public static String toSql(LocalDateTime dateTime) {
        String date = dateTime.toString();
        date = date.substring(0, date.indexOf('T')) + " " + date.substring(date.indexOf('T') + 1, date.indexOf('T') + 6);
        return date;
    }
}
