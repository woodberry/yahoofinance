package au.net.woodberry.services.yahoofinance;

import java.net.URL;

public interface YahooFinanceData<T> {

    /**
     * Perform a fetch operation on the supplied to retrieve YahooFinance information 
     * 
     * @param URL A yahoo finance URL
     */
    public T fetch(URL URL);
}
