package au.net.woodberry.services.yahoofinance.impl.utils;

import au.net.woodberry.services.yahoofinance.enums.Frequency;

import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {

    private static final String YAHOO_ICHART_CSV_URL = "http://ichart.finance.yahoo.com/table.csv";
    private static final String YAHOO_DOWNLOAD_DATA_DELAYED_URL = "http://download.finance.yahoo.com/d/quotes.csv";

    private URLUtils() {
        // Prevent this class from being instantiated
    }

    public static URL buildHistoricalPricesURL(String symbol, Frequency frequency) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();
        builder.append(YAHOO_ICHART_CSV_URL);
        builder.append("?s=").append(symbol);
        builder.append("&g=").append(frequency.getG());
        builder.append("&ignore=.csv");
        return new URL(builder.toString());
    }
    
    public static URL buildDownloadDataDelayedURL(String symbol) throws MalformedURLException {
        StringBuilder builder = new StringBuilder();
        builder.append(YAHOO_DOWNLOAD_DATA_DELAYED_URL);
        builder.append("?s=").append(symbol);
        builder.append("&f=sl1d1t1c1ohgv&e=.csv");
        return new URL(builder.toString());
    }
}
