package au.net.woodberry.services.yahoofinance.integration.tests;

import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.YahooFinanceServiceImpl;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HistoricalPricesIntegrationTest {

    private YahooFinanceServiceImpl yahooFinanceService;

    @Before
    public void before() {
        yahooFinanceService = new YahooFinanceServiceImpl();
    }
    
    @Test
    public void testAsxStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("BHP.AX", Frequency.MONTH);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test(expected = RemoteServiceInvalidResponseException.class)
    public void testAsxStockInvalid() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("a1b2c3d4", Frequency.MONTH);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testAustralianEquitiesIndex() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("^AXNJ", Frequency.DAY);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testAustralianEquitiesIndexInvalid() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("^A", Frequency.DAY);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testNasdaqStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("AAPL", Frequency.WEEK);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testUsEquitiesIndex() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("^GSPC", Frequency.MONTH);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testNyseStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("MCD", Frequency.MONTH);
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }
}
