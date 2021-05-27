package com.example.sample.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/***
 * This class represents date util methods to convert dates from one format to another
 */
public class DateUtil {
    /***
     * This field represents the DateTimeFormatter instance to
     * convert date from and to MM/dd/yyyy[ [HH][:mm][:ss][.SSS]] format
     */
    private static DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MM/dd/yyyy[ [HH][:mm][:ss][.SSS]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();

    /***
     * This method converts given date in format MM/dd/yyyy to epoch millis
     * With time set to 23:59:59:999 for that day
     *
     * @param date Date string
     *
     * @return epoch millis for the given date after setting time
     *
     * @throws DateTimeParseException throws exception if the string is in invalid date format.
     */
    public static Long getEndOfDayTime(String date) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zonedDateTime.toEpochSecond() * 1000;
    }

    /***
     * This method converts given date in format MM/dd/yyyy to epoch millis
     * With time set to 00:00:00:000 for that day
     *
     * @param date Date string
     *
     * @return epoch millis for the given date after setting time
     *
     * @throws DateTimeParseException throws exception if the string is in invalid date format.
     */
    public static Long getStartOfDayTime(String date) throws DateTimeParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zonedDateTime.toEpochSecond() * 1000;
    }

    /***
     * This method converts epoch millis to MM/dd/yyyy hh:mm:ss format
     *
     * @param epochMillis epoch millis for any date
     *
     * @return Date string after converting in the MM/dd/yyyy hh:mm:ss format
     */
    public static String getDate(Long epochMillis) {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epochMillis/1000, 0,
                ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()));
        return formatter.format(localDateTime);
    }

    public static void main(String[] args) {
        System.out.println(getDate(1620846807000L));
    }
}
