package au.net.woodberry.services.yahoofinance.domain;

import au.net.woodberry.services.yahoofinance.domain.adapters.AdapterBigInteger;
import au.net.woodberry.services.yahoofinance.domain.adapters.AdapterYahooLocalDate;
import fr.ybonnel.csvengine.adapter.AdapterDouble;
import fr.ybonnel.csvengine.annotation.CsvColumn;
import fr.ybonnel.csvengine.annotation.CsvFile;
import org.joda.time.LocalDate;

import java.math.BigInteger;

@CsvFile(separator = ",")
public class HistoricalPrice {

    @CsvColumn(value="Date", adapter = AdapterYahooLocalDate.class, mandatory = true)
    private LocalDate date;

    @CsvColumn(value="Open", adapter = AdapterDouble.class, mandatory = true)
    private Double open;

    @CsvColumn(value="High", adapter = AdapterDouble.class, mandatory = true)
    private Double high;

    @CsvColumn(value="Low", adapter = AdapterDouble.class, mandatory = true)
    private Double low;

    @CsvColumn(value="Close", adapter = AdapterDouble.class, mandatory = true)
    private Double close;

    @CsvColumn(value="Volume", adapter = AdapterBigInteger.class, mandatory = true)
    private BigInteger volume;

    @CsvColumn(value="Adj Close", adapter = AdapterDouble.class, mandatory = true)
    private Double adjClose;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = BigInteger.valueOf(volume);
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

}
