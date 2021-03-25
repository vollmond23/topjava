package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(FORMATTER);
    }

    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }

    public static LocalDateTime truncate(LocalDateTime dateTime) {
        return dateTime.truncatedTo(ChronoUnit.MINUTES);
    }
}
