package au.net.woodberry.services.yahoofinance.integration.tests;

import au.net.woodberry.services.yahoofinance.YahooFinanceService;
import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import au.net.woodberry.services.yahoofinance.impl.YahooFinanceServiceImpl;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DownloadDataDelayedIntegrationTest {

    private YahooFinanceService yahooFinanceService;

    @Before
    public void before() {
        yahooFinanceService = new YahooFinanceServiceImpl();
    }

    @Test
    public void testAsxStock() {
        QuoteDelayed result = yahooFinanceService.getQuoteDelayed("BHP.AX");
        assertThat(result).isNotNull();
    }

    @Test(expected = RemoteServiceInvalidResponseException.class)
    public void testAsxStockInvalid() {
        yahooFinanceService.getQuoteDelayed("a1b2c3d4");
    }

    @Test
    public void testAustralianEquitiesIndex() {
        QuoteDelayed result = yahooFinanceService.getQuoteDelayed("^AXNJ");
        assertThat(result).isNotNull();
    }

    @Test(expected = RemoteServiceInvalidResponseException.class)
    public void testAustralianEquitiesIndexInvalid() {
        yahooFinanceService.getQuoteDelayed("^A");
    }

    @Test
    public void testNasdaqStock() {
        QuoteDelayed result = yahooFinanceService.getQuoteDelayed("AAPL");
        assertThat(result).isNotNull();
    }

    @Test
    public void testUsEquitiesIndex() {
        QuoteDelayed result = yahooFinanceService.getQuoteDelayed("^GSPC");
        assertThat(result).isNotNull();
    }

    @Test
    public void testNyseStock() {
        QuoteDelayed result = yahooFinanceService.getQuoteDelayed("MCD");
        assertThat(result).isNotNull();
    }
}
