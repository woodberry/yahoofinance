package au.net.woodberry.services.yahoofinance.integration.tests;

import au.net.woodberry.services.yahoofinance.YahooFinanceService;
import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.enums.Frequency;
import au.net.woodberry.services.yahoofinance.impl.YahooFinanceServiceImpl;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoricalPricesIntegrationTest {

    private YahooFinanceService yahooFinanceService;

    @Before
    public void before() {
        yahooFinanceService = new YahooFinanceServiceImpl();
    }
    
    @Test
    public void testAsxStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("BHP.AX", Frequency.MONTH);
        assertThat(result).isNotNull();
        assertThat(!result.isEmpty());
    }

    @Test(expected = RemoteServiceInvalidResponseException.class)
    public void testAsxStockInvalid() {
        yahooFinanceService.getHistoricalPrices("a1b2c3d4", Frequency.MONTH);
    }

    @Test
    public void testAustralianEquitiesIndex() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("^AXNJ", Frequency.DAY);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @Test(expected = RemoteServiceInvalidResponseException.class)
    public void testAustralianEquitiesIndexInvalid() {
        yahooFinanceService.getHistoricalPrices("^A", Frequency.DAY);
    }

    @Test
    public void testNasdaqStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("AAPL", Frequency.WEEK);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }
    
    @Test
    public void testUsEquitiesIndex() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("^GSPC", Frequency.MONTH);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }
    
    @Test
    public void testNyseStock() {
        List<HistoricalPrice> result = yahooFinanceService.getHistoricalPrices("MCD", Frequency.MONTH);
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }
}
