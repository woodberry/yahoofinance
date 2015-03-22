package au.net.woodberry.services.yahoofinance.domain.page;

import au.net.woodberry.services.yahoofinance.YahooFinancePage;
import au.net.woodberry.services.yahoofinance.domain.HistoricalPrice;
import au.net.woodberry.services.yahoofinance.impl.exceptions.RemoteServiceInvalidResponseException;
import fr.ybonnel.csvengine.CsvEngine;
import fr.ybonnel.csvengine.exception.CsvErrorsExceededException;
import fr.ybonnel.csvengine.model.Result;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HistoricalPricesPage implements YahooFinancePage<List<HistoricalPrice>> {

    private final CsvEngine csvEngine;

    public HistoricalPricesPage(CsvEngine csvEngine) {
        this.csvEngine = csvEngine;
    }
    
    public HistoricalPricesPage() {
        this.csvEngine = new CsvEngine(HistoricalPrice.class);
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

