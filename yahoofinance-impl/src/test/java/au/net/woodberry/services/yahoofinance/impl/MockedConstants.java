package au.net.woodberry.services.yahoofinance.impl;

import au.net.woodberry.services.yahoofinance.enums.Frequency;
import org.joda.time.DateTimeZone;

public interface MockedConstants {
    
    public static final Frequency FREQUENCY = Frequency.DAY;
    public static final String SYMBOL = "FOO.AX";
    public static final DateTimeZone DATE_TIME_ZONE = DateTimeZone.UTC;
}
