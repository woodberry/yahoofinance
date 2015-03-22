package au.net.woodberry.services.yahoofinance;

import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.enums.Frequency;

import java.util.List;

public interface YahooFinanceService {

    public List<HistoricalPrice> getHistoricalPrices(String symbol, Frequency frequency);

}
