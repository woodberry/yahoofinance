package au.net.woodberry.services.yahoofinance.domain;

import au.net.woodberry.services.yahoofinance.YahooFinanceData;
import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;

import java.io.IOException;
import java.net.URL;

public class DownloadQuoteDelayed implements YahooFinanceData<QuoteDelayed> {

    @Override
    public QuoteDelayed fetch(URL URL) {
        try {
            return QuoteDelayed.InputStreamConverter.convert(URL.openStream());
        } catch (IOException | NumberFormatException e) {
            throw new RemoteServiceInvalidResponseException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while fetching URL: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        }
    }
}
