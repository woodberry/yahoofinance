package au.net.woodberry.services.yahoofinance.impl;

import au.net.woodberry.services.yahoofinance.YahooFinancePage;
import au.net.woodberry.services.yahoofinance.YahooFinanceService;
import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.domain.page.HistoricalPricesPage;
import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.utils.URLUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class YahooFinanceServiceImpl implements YahooFinanceService {
    
    private final YahooFinancePage<List<HistoricalPrice>> historicalPricesPage;

    public YahooFinanceServiceImpl() {
        this(new HistoricalPricesPage());
    }
    
    public YahooFinanceServiceImpl(YahooFinancePage<List<HistoricalPrice>> historicalPricesPage) {
        this.historicalPricesPage = historicalPricesPage;
    }
    
    @Override
    public List<HistoricalPrice> getHistoricalPrices(String symbol, Frequency frequency) {
        
        checkArgument(symbol != null, "Symbol required");
        checkArgument(!symbol.isEmpty(), "Symbol is not valid");
        checkArgument(frequency != null, "Frequency required");
        
        URL URL = null;
        try {
            URL = new URL(URLUtils.buildHistoricalPricesURL(symbol, frequency));
            return historicalPricesPage.fetch(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while creating URL: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        }
    }
}
