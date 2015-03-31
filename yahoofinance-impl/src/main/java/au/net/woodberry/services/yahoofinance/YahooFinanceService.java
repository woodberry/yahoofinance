package au.net.woodberry.services.yahoofinance;

import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.enums.Frequency;
import org.joda.time.DateTimeZone;

import java.util.List;

public interface YahooFinanceService {

    /**
     * Retrieve a list of historical prices
     *  
     * @param symbol The financial instrument symbol
     * @param frequency Frequency as supported by YahooFinance
     */
    public List<HistoricalPrice> getHistoricalPrices(String symbol, Frequency frequency);

    /**
     * Retrieve a delayed data quote with additional time zone context information
     *  
     * @param symbol The financial instrument symbol
     * @param dateTimeZone A time zone, e.g. if retrieving a stock from the NYSE, then
     *                     supply a US/Eastern timezone
     */
    public QuoteDelayed getQuoteDelayed(String symbol, DateTimeZone dateTimeZone);

    /**
     * Retrieve a delayed data quote. Use this if the host is on the  same time zone as the exchange
     * of the symbol being requested, or if timezone information is not required.
     *
     * @param symbol The financial instrument symbol
     */
    public QuoteDelayed getQuoteDelayed(String symbol);
}
