package au.net.woodberry.services.yahoofinance.impl.utils;

import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.MockedConstants;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class URLUtilsTest {

    @Test
    public void testBuildHistoricalPricesURLFrequencyDay() throws MalformedURLException {
        URL url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.DAY);
        URL expected = new URL("http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=d&ignore=.csv");
        assertThat(url).isEqualTo(expected);
    }

    @Test
    public void testBuildHistoricalPricesURLFrequencyMonth() throws MalformedURLException {
        URL url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.MONTH);
        URL expected = new URL("http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=m&ignore=.csv");
        assertThat(url).isEqualTo(expected);
    }

    @Test
    public void testBuildHistoricalPricesURLFrequencyWeek() throws MalformedURLException {
        URL url = URLUtils.buildHistoricalPricesURL(MockedConstants.SYMBOL, Frequency.WEEK);
        URL expected = new URL("http://ichart.finance.yahoo.com/table.csv?s=FOO.AX&g=w&ignore=.csv");
        assertThat(url).isEqualTo(expected);
    }
    
    @Test
    public void testBuildDownloadDataDelayedURL() throws MalformedURLException {
        URL url = URLUtils.buildDownloadDataDelayedURL(MockedConstants.SYMBOL);
        URL expected = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=FOO.AX&f=sl1d1t1c1ohgv&e=.csv");
        assertThat(url).isEqualTo(expected);
    }
}
