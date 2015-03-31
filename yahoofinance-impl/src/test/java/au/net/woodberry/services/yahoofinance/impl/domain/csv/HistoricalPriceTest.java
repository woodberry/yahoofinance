package au.net.woodberry.services.yahoofinance.impl.domain.csv;

import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class HistoricalPriceTest {

    private static final CsvEngine CSV_ENGINE = new CsvEngine(HistoricalPrice.class);

    @Test
    public void tc1() throws CsvErrorsExceededException {
        InputStream testData = HistoricalPriceTest.class.getResourceAsStream("/HISTORICAL_PRICES_TC1.csv");
        List<HistoricalPrice> historicalPrices = CSV_ENGINE.parseInputStream(testData, HistoricalPrice.class).getObjects();
        for (HistoricalPrice historicalPrice: historicalPrices) {
            assertHistoricalPrice(historicalPrice);
        }
    }

    @Test
    public void tc2() throws CsvErrorsExceededException {
        InputStream testData = HistoricalPriceTest.class.getResourceAsStream("/HISTORICAL_PRICES_TC2.csv");
        List<HistoricalPrice> historicalPrices = CSV_ENGINE.parseInputStream(testData, HistoricalPrice.class).getObjects();
        for (HistoricalPrice historicalPrice: historicalPrices) {
            assertHistoricalPrice(historicalPrice);
        }
    }

    private static void assertHistoricalPrice(HistoricalPrice historicalPrice) {
        assertNotNull("HistoricalPrice object is invalid: NULL", historicalPrice);
        assertNotNull("Date is invalid", historicalPrice.getDate());
        assertNotNull("Open is invalid ", historicalPrice.getOpen());
        assertNotNull("High is invalid ", historicalPrice.getHigh());
        assertNotNull("Low is invalid ", historicalPrice.getLow());
        assertNotNull("Close is invalid ", historicalPrice.getClose());
        assertNotNull("Volume is invalid ", historicalPrice.getVolume());
        assertNotNull("Adjusted close is invalid ", historicalPrice.getAdjClose());
    }
}
