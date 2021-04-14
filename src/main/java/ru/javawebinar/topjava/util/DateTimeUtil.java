package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenDate(LocalDate ld, LocalDate startDate, LocalDate endDate) {
        return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) <= 0;
    }

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static LocalDate toDate(String ld) {
        if (!ld.equals("")) {
            return LocalDate.parse(ld);
        }
        return null;
    }

    public static LocalTime toTime(String lt) {
        if (!lt.equals("")) {
            return LocalTime.parse(lt);
        }
        return null;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

