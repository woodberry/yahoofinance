package au.net.woodberry.services.yahoofinance.impl.domain;

import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class HistoricalPriceTest {

    private InputStream testData;

    @Test
    public void testYahooQuoteTC1() throws CsvErrorsExceededException {
        testData = HistoricalPriceTest.class.getResourceAsStream("/YAHOO_FINANCE_TESTDATA_TC1.csv");
        CsvEngine csvEngine = new CsvEngine(HistoricalPrice.class);
        List<HistoricalPrice> historicalPrices = csvEngine.parseInputStream(testData, HistoricalPrice.class).getObjects();
        for (HistoricalPrice historicalPrice: historicalPrices) {
            assertHistoricalPrice(historicalPrice);
        }
    }

    @Test
    public void testYahooQuoteTC2() throws CsvErrorsExceededException {
        testData = HistoricalPriceTest.class.getResourceAsStream("/YAHOO_FINANCE_TESTDATA_TC2.csv");
        CsvEngine csvEngine = new CsvEngine(HistoricalPrice.class);
        List<HistoricalPrice> historicalPrices = csvEngine.parseInputStream(testData, HistoricalPrice.class).getObjects();
        for (HistoricalPrice historicalPrice: historicalPrices) {
            assertHistoricalPrice(historicalPrice);
        }
    }

    private static void assertHistoricalPrice(HistoricalPrice historicalPrice) {
        assertNotNull("Quote object is invalid: NULL", historicalPrice);
        assertNotNull("Date is invalid", historicalPrice.getDate());
        assertNotNull("Open is invalid ", historicalPrice.getOpen());
        assertNotNull("High is invalid ", historicalPrice.getHigh());
        assertNotNull("Low is invalid ", historicalPrice.getLow());
        assertNotNull("Close is invalid ", historicalPrice.getClose());
        assertNotNull("Volume is invalid ", historicalPrice.getVolume());
        assertNotNull("Adjusted close is invalid ", historicalPrice.getAdjClose());
    }
}
