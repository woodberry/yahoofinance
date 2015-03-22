package au.net.woodberry.services.yahoofinance;

import java.net.URL;

public interface YahooFinancePage<T> {
    
    public T fetch(URL URL);
}
