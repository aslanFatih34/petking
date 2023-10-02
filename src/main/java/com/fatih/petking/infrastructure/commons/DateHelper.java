package com.fatih.petking.infrastructure.commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateHelper {

    public static final String MYSQL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String POSTGRESQL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DAY_OF_MONTH_PATTERN = "dd";
    private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);
    private static final int MONDAY = 2;
    private static final int SUNDAY = 1;

    private DateHelper() {
    }

    public static Date now() {
        return new Date();
    }

    public static String formatDate(String pattern, Date date) {
        if (ObjectHelper.isNull(date)) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String toMySQLDateFormat(Date date) {
        return formatDate(MYSQL_DATE_PATTERN, date);
    }

    public static String toPostgreSQLDateFormat(Date date) {
        return formatDate(POSTGRESQL_DATE_PATTERN, date);
    }

    public static Date parseAsMySQLDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return parseDate(MYSQL_DATE_PATTERN, dateStr);
    }

    public static Date parseAsPostgreSQLDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return parseDate(POSTGRESQL_DATE_PATTERN, dateStr);
    }

    public static Date parseDate(String pattern, String dateStr) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            logger.error("Caught exception while parsing date", e);
            return null;
        }
    }

    public static Date parseDateWithException(String pattern, String dateStr) {
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Caught exception while parsing date, message: " + e.getMessage());
        }
    }

    public static Date setHourMinuteSecond(Date date, int hour, int minute, int second) {
        if (ObjectHelper.isNotNull(date)) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTime();
        }
        return null;
    }

    public static Date setHourMinuteSecondMillisecond(Date date, int hour, int minute, int second, int millisecond) {
        if (ObjectHelper.isNotNull(date)) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, minute);
            cal.set(Calendar.SECOND, second);
            cal.set(Calendar.MILLISECOND, millisecond);
            return cal.getTime();
        }
        return null;
    }

    public static Date addDays(Date target, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static Date substractDays(Date target, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.DAY_OF_MONTH, -days);
        return cal.getTime();
    }

    public static Date addMinutes(Date target, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date addSeconds(Date target, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public static Date substractMinutes(Date target, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.MINUTE, -minutes);
        return cal.getTime();
    }

    public static Date substractSeconds(Date target, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(target);
        cal.add(Calendar.SECOND, -seconds);
        return cal.getTime();
    }

    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    public static Date getDateWithCurrentTime(Date date) {
        return setHourMinuteSecond(date, getHour(now()), getMinute(now()), getSecond(now()));
    }

    public static int dayDiff(Date start, Date end) {
        return (int) ChronoUnit.DAYS.between(convertDateToLocalDateTime(start), convertDateToLocalDateTime(end));
    }

    public static int minuteDiff(Date start, Date end) {
        return (int) ChronoUnit.MINUTES.between(convertDateToLocalDateTime(start), convertDateToLocalDateTime(end));
    }

    public static boolean hasSameDayNumber(Date first, Date second) {
        return !ObjectHelper.isAllNotPresent(first, second) && Period.between(convertDateToLocalDate(first), convertDateToLocalDate(second)).getDays() == NumberUtils.INTEGER_ZERO;
    }

    public static boolean isSameDay(Date first, Date second) {
        return ObjectHelper.isAllPresent(first, second) && DateUtils.isSameDay(first, second);
    }

    public static boolean isSameMinute(Date first, Date second) {
        return ObjectHelper.isAllPresent(first, second) && minuteDiff(first, second) == NumberUtils.INTEGER_ZERO;
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return convertDateToLocalDateTime(date).toLocalDate();
    }

    public static Date getPreviousWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == SUNDAY) {
            return substractDays(date, 2);
        }
        if (dayOfWeek == MONDAY) {
            return substractDays(date, 3);
        }
        return substractDays(date, 1);
    }

    public static Integer getPastDayCountOfCurrentMonth() {
        return Integer.valueOf(formatDate(DAY_OF_MONTH_PATTERN, now())) - 1;
    }

    public static Date firstDayOfPreviousMonth(Date date) {
        Date startDate = DateUtils.setDays(DateUtils.addMonths(date, -1), 1);
        return DateHelper.setHourMinuteSecond(startDate, 0, 0, 0);
    }

    public static Date firstDayOfPreviousMonth() {
        return firstDayOfPreviousMonth(now());
    }

    public static Date lastDayOfPreviousMonth(Date date) {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(DateUtils.setDays(DateUtils.addMonths(date, -1), 1));
        endCal.set(Calendar.DAY_OF_MONTH, endCal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = endCal.getTime();
        return DateHelper.setHourMinuteSecond(endDate, 23, 59, 59);
    }

    public static Date lastDayOfPreviousMonth() {
        return lastDayOfPreviousMonth(now());
    }

    public static Date getBeginningOfCurrentDay() {
        return DateHelper.setHourMinuteSecond(DateHelper.now(), 0, 0, 0);
    }

    public static Date getEndOfCurrentDay() {
        return DateHelper.setHourMinuteSecond(DateHelper.now(), 23, 59, 59);
    }

    public static Date getBeginningOfDay(Date date) {
        if (ObjectHelper.isPresent(date)) {
            return DateHelper.setHourMinuteSecond(date, 0, 0, 0);
        }
        return null;
    }

    public static Date getEndOfDay(Date date) {
        if (ObjectHelper.isPresent(date)) {
            return DateHelper.setHourMinuteSecondMillisecond(date, 23, 59, 59, 999);
        }
        return null;
    }
}
