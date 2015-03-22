package au.net.woodberry.services.yahoofinance.impl.utils;

import au.net.woodberry.services.yahoofinance.enums.Frequency;

public class URLUtils {

    public static final String YAHOO_ICHART_CSV_URL = "http://ichart.finance.yahoo.com/table.csv";
    
    private URLUtils() {
        // Prevent this class from being instantiated
    }

    public static String buildHistoricalPricesURL(String symbol, Frequency frequency) {
        StringBuilder builder = new StringBuilder();
        builder.append(YAHOO_ICHART_CSV_URL);
        builder.append("?s=").append(symbol);
        builder.append("&g=").append(frequency.getG());
        builder.append("&ignore=.csv");
        return builder.toString();
    }
}
