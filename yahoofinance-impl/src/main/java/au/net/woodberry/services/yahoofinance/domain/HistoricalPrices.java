package au.net.woodberry.services.yahoofinance.domain;

import au.net.woodberry.services.yahoofinance.YahooFinanceData;
import au.net.woodberry.services.yahoofinance.domain.csv.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;
import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import fr.ybonnel.csvengine.model.Result;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HistoricalPrices implements YahooFinanceData<List<HistoricalPrice>> {

    private final CsvEngine csvEngine;

    public HistoricalPrices() {
        this.csvEngine = new CsvEngine(HistoricalPrice.class);
    }

    HistoricalPrices(CsvEngine csvEngine) {
        this.csvEngine = csvEngine;
    }

    @Override
    public List<HistoricalPrice> fetch(URL URL) {
        try {
            Result<HistoricalPrice> result = csvEngine.parseInputStream(URL.openStream(), HistoricalPrice.class);
            return result.getObjects();
        } catch (IOException e) {
            throw new RemoteServiceInvalidResponseException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while fetching URL: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        } catch (CsvErrorsExceededException e) {
            throw new RemoteServiceInvalidResponseException("An exception of type: " + e.getClass().getCanonicalName()
                    + " has occurred while parsing the URL response: " + URL
                    + " with message: " + (e.getMessage() == null ? "No message" : e.getMessage())
                    + " Caused by: " + (e.getCause() == null ? "No cause" : e.getCause()), e);
        }
    }
}

