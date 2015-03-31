package au.net.woodberry.services.yahoofinance.impl;

import au.net.woodberry.services.yahoofinance.YahooFinanceData;
import au.net.woodberry.services.yahoofinance.YahooFinanceService;
import au.net.woodberry.services.yahoofinance.domain.DownloadQuoteDelayed;
import au.net.woodberry.services.yahoofinance.domain.HistoricalPrices;
import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.utils.DateUtils;
import au.net.woodberry.services.yahoofinance.impl.utils.URLUtils;
import org.joda.time.DateTimeZone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class YahooFinanceServiceImpl implements YahooFinanceService {
    
    private final YahooFinanceData<List<HistoricalPrice>> historicalPrices;
    private final YahooFinanceData<QuoteDelayed> quoteDelayed;

    public YahooFinanceServiceImpl() {
        this(new HistoricalPrices(), 
             new DownloadQuoteDelayed());
    }
    
    YahooFinanceServiceImpl(YahooFinanceData<List<HistoricalPrice>> historicalPrices, 
                            YahooFinanceData<QuoteDelayed> quoteDelayed) {
        this.historicalPrices = historicalPrices;
        this.quoteDelayed = quoteDelayed;
    }
    
    @Override
    public List<HistoricalPrice> getHistoricalPrices(String symbol, Frequency frequency) {
        
        checkArgument(symbol != null, "Symbol required");
        checkArgument(!symbol.isEmpty(), "Symbol is not valid");
        checkArgument(frequency != null, "Frequency required");
        
        URL URL = null;
        try {
            URL = URLUtils.buildHistoricalPricesURL(symbol, frequency);
            return historicalPrices.fetch(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while creating URL: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        }
    }

    @Override
    public QuoteDelayed getQuoteDelayed(String symbol, DateTimeZone dateTimeZone) {
        
        checkArgument(symbol != null, "Symbol required");
        checkArgument(dateTimeZone != null, "DateTimeZone required");
        
        QuoteDelayed quoteDelayed = getQuoteDelayed(symbol);
        quoteDelayed.setDateTime(DateUtils.construct(quoteDelayed.getDate(), quoteDelayed.getTime(), dateTimeZone));
        return quoteDelayed;
    }

    @Override
    public QuoteDelayed getQuoteDelayed(String symbol) {
        checkArgument(symbol != null, "Symbol required");
        URL URL = null;
        try {
            URL = URLUtils.buildDownloadDataDelayedURL(symbol);
            return quoteDelayed.fetch(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while creating URL: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        }
    }
}
