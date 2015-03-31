package au.net.woodberry.services.yahoofinance.impl;

import au.net.woodberry.services.yahoofinance.domain.DownloadQuoteDelayed;
import au.net.woodberry.services.yahoofinance.domain.HistoricalPrices;
import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooFinanceServiceImplTest {

    private YahooFinanceServiceImpl yahooFinanceServiceImpl;
    private HistoricalPrices historicalPrices;
    private DownloadQuoteDelayed downloadQuoteDelayed;

    @Before
    public void before() {
        historicalPrices = Mockito.mock(HistoricalPrices.class);
        downloadQuoteDelayed = Mockito.mock(DownloadQuoteDelayed.class);
        yahooFinanceServiceImpl = new YahooFinanceServiceImpl(historicalPrices, downloadQuoteDelayed);
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
    public void testGetQuoteDelayedNullSymbol() {
        yahooFinanceServiceImpl.getQuoteDelayed(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetQuoteDelayedWithTimeZoneNullDateTimeZone() {
        yahooFinanceServiceImpl.getQuoteDelayed(MockedConstants.SYMBOL, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetQuoteDelayedWithTimeZoneNullSymbol() {
        yahooFinanceServiceImpl.getQuoteDelayed(null, MockedConstants.DATE_TIME_ZONE);
    }
    
    @Test
    public void testGetHistoricalPricesVerifyURLFetched() {
        when(historicalPrices.fetch(any(URL.class))).thenReturn(new ArrayList<HistoricalPrice>());
        yahooFinanceServiceImpl.getHistoricalPrices(MockedConstants.SYMBOL, MockedConstants.FREQUENCY);
        verify(historicalPrices, times(1)).fetch(any(URL.class));
    }

    @Test
    public void testGetQuoteDelayedVerifyURLFetched() {
        when(downloadQuoteDelayed.fetch(any(URL.class))).thenReturn(new QuoteDelayed());
        yahooFinanceServiceImpl.getQuoteDelayed(MockedConstants.SYMBOL);
        verify(downloadQuoteDelayed, times(1)).fetch(any(URL.class));
    }
    
    @Test
    public void testGetQuoteDelayedWithTimeZoneVerifyURLFetched() {
        QuoteDelayed mockedQuoteDelayed = new QuoteDelayed();
        mockedQuoteDelayed.setDate(new LocalDate(2002,12,7));
        mockedQuoteDelayed.setTime(new LocalTime(11,11,0));
        when(downloadQuoteDelayed.fetch(any(URL.class))).thenReturn(mockedQuoteDelayed);
        yahooFinanceServiceImpl.getQuoteDelayed(MockedConstants.SYMBOL, MockedConstants.DATE_TIME_ZONE);
        verify(downloadQuoteDelayed, times(1)).fetch(any(URL.class));
    }
    
    @Test
    public void testGetQuoteDelayedWithTimeZoneVerifyConvertsTimeZone() {
        final DateTimeZone tzRequired = DateTimeZone.forID("Australia/Sydney");
        QuoteDelayed mockedQuoteDelayed = new QuoteDelayed();
        mockedQuoteDelayed.setDate(new LocalDate(2002,12,7));
        mockedQuoteDelayed.setTime(new LocalTime(11,11,0));
        when(downloadQuoteDelayed.fetch(any(URL.class))).thenReturn(mockedQuoteDelayed);
        QuoteDelayed quoteDelayed = yahooFinanceServiceImpl.getQuoteDelayed(MockedConstants.SYMBOL, tzRequired);
        assertThat(quoteDelayed.getDateTime().getZone()).isEqualTo(tzRequired);
    }
}
