package au.net.woodberry.services.yahoofinance.impl.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class DateUtils {
    
    private DateUtils() {
        // Prevent this class from being instantiated
    }

    /**
     * Construct a DateTime from the supplied local date, time and time zone
     *
     * @param localDate the local date
     * @param localTime the local time
     * @param dateTimeZone The time zone
     */
    public static DateTime construct(LocalDate localDate, LocalTime localTime, DateTimeZone dateTimeZone) {
        return new DateTime(
                localDate.getYear(), 
                localDate.getMonthOfYear(), 
                localDate.getDayOfMonth(), 
                localTime.getHourOfDay(), 
                localTime.getMinuteOfHour(), 
                localTime.getSecondOfMinute(), 
                localTime.getMillisOfSecond(), 
                dateTimeZone
        );
    }
}
