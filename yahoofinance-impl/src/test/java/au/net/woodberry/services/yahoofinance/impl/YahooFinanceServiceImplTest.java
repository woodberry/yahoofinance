package au.net.woodberry.services.yahoofinance.impl;

import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.domain.page.HistoricalPricesPage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooFinanceServiceImplTest {

    private YahooFinanceServiceImpl yahooFinanceServiceImpl;
    private HistoricalPricesPage historicalPricesPage;

    @Before
    public void setUp() {
        historicalPricesPage = Mockito.mock(HistoricalPricesPage.class);
        yahooFinanceServiceImpl = new YahooFinanceServiceImpl(historicalPricesPage);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetHistoricalPricesNullSymbol() {
        yahooFinanceServiceImpl.getHistoricalPrices(null, MockedConstants.FREQUENCY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHistoricalPricesEmptySymbol() {
        yahooFinanceServiceImpl.getHistoricalPrices("", MockedConstants.FREQUENCY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHistoricalPricesNullFrequency() {
        yahooFinanceServiceImpl.getHistoricalPrices(MockedConstants.SYMBOL, null);
    }
    
    @Test
    public void testGetHistoricalPricesVerifyPageFetched() {
        when(historicalPricesPage.fetch(any(URL.class))).thenReturn(new ArrayList<HistoricalPrice>());
        yahooFinanceServiceImpl.getHistoricalPrices(MockedConstants.SYMBOL, MockedConstants.FREQUENCY);
        verify(historicalPricesPage, times(1)).fetch(any(URL.class));
    }
    
}
