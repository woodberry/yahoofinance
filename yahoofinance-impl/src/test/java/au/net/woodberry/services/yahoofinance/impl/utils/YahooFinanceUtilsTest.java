package au.net.woodberry.services.yahoofinance.impl.utils;

import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.MockedConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YahooFinanceUtilsTest {

    @Test
    public void testCreateQuoteUrlFrequencyDay() {
        String url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.DAY);
        String expected = "http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=d&ignore=.csv";
        assertEquals(url, expected);
    }

    @Test
    public void testCreateQuoteUrlFrequencyMonth() {
        String url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.MONTH);
        String expected = "http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=m&ignore=.csv";
        assertEquals(url, expected);
    }

    @Test
    public void testCreateQuoteUrlFrequencyWeek() {
        String url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.WEEK);
        String expected = "http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=w&ignore=.csv";
        assertEquals(url, expected);
    }
}
