package au.net.woodberry.services.yahoofinance.impl.domain.csv;

import au.net.woodberry.services.yahoofinance.domain.csv.QuoteDelayed;
import au.net.woodberry.services.yahoofinance.impl.utils.DateUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteDelayedTest {

    @Test
    public void tc1() throws IOException {
        InputStream testData = QuoteDelayedTest.class.getResourceAsStream("/QUOTE_DELAYED_TC1.csv");
        QuoteDelayed quoteDelayed = QuoteDelayed.InputStreamConverter.convert(testData);
        assertThat(quoteDelayed).isNotNull();
        assertThat(quoteDelayed.getSymbol()).isEqualTo("^FCHI");
        assertThat(quoteDelayed.getLast()).isEqualTo(5079.42);
        LocalDate date = LocalDate.parse("2015-03-31");
        LocalTime time = LocalTime.parse("11:40"); // AM
        assertThat(quoteDelayed.getDate()).isEqualTo(date);
        assertThat(quoteDelayed.getTime()).isEqualTo(time);
        assertThat(quoteDelayed.getDateTime()).isEqualTo(DateUtils.construct(date, time, DateTimeZone.UTC));
        assertThat(quoteDelayed.getChange()).isEqualTo(-4.10); // Negative value
        assertThat(quoteDelayed.getOpen()).isEqualTo(5073.99);
        assertThat(quoteDelayed.getDaysRangeLow()).isEqualTo(5098.86);
        assertThat(quoteDelayed.getDaysRangeHigh()).isEqualTo(5071.32);
        assertThat(quoteDelayed.getVolume()).isEqualTo(0);
    }

    @Test
    public void tc2() throws IOException {
        InputStream testData = QuoteDelayedTest.class.getResourceAsStream("/QUOTE_DELAYED_TC2.csv");
        QuoteDelayed quoteDelayed = QuoteDelayed.InputStreamConverter.convert(testData);
        assertThat(quoteDelayed).isNotNull();
        assertThat(quoteDelayed.getSymbol()).isEqualTo("BHP.AX");
        assertThat(quoteDelayed.getLast()).isEqualTo(31.03);
        LocalDate date = LocalDate.parse("2015-03-31");
        LocalTime time = LocalTime.parse("16:10"); // PM
        assertThat(quoteDelayed.getDate()).isEqualTo(date);
        assertThat(quoteDelayed.getTime()).isEqualTo(time);
        assertThat(quoteDelayed.getDateTime()).isEqualTo(DateUtils.construct(date, time, DateTimeZone.UTC));
        assertThat(quoteDelayed.getChange()).isEqualTo(0.93); // Positive value
        assertThat(quoteDelayed.getOpen()).isEqualTo(30.80);
        assertThat(quoteDelayed.getDaysRangeLow()).isEqualTo(31.10);
        assertThat(quoteDelayed.getDaysRangeHigh()).isEqualTo(30.80);
        assertThat(quoteDelayed.getVolume()).isEqualTo(7356402);
    }

    @Test(expected = NumberFormatException.class)
    public void tc3() throws IOException {
        InputStream testData = QuoteDelayedTest.class.getResourceAsStream("/QUOTE_DELAYED_TC3.csv");
        QuoteDelayed.InputStreamConverter.convert(testData);
    }

    @Test
    public void tc4() throws IOException {
        InputStream testData = QuoteDelayedTest.class.getResourceAsStream("/QUOTE_DELAYED_TC4.csv");
        QuoteDelayed quoteDelayed = QuoteDelayed.InputStreamConverter.convert(testData);
        assertThat(quoteDelayed).isNotNull();
        assertThat(quoteDelayed.getSymbol()).isEqualTo("AAPL");
        assertThat(quoteDelayed.getLast()).isEqualTo(126.37);
        LocalDate date = LocalDate.parse("2015-03-30");
        LocalTime time = LocalTime.parse("16:00");
        assertThat(quoteDelayed.getDate()).isEqualTo(date);
        assertThat(quoteDelayed.getTime()).isEqualTo(time);
        assertThat(quoteDelayed.getDateTime()).isEqualTo(DateUtils.construct(date, time, DateTimeZone.UTC));
        assertThat(quoteDelayed.getChange()).isEqualTo(0);
        assertThat(quoteDelayed.getOpen()).isNull();
        assertThat(quoteDelayed.getDaysRangeLow()).isNull();
        assertThat(quoteDelayed.getDaysRangeHigh()).isNull();
        assertThat(quoteDelayed.getVolume()).isEqualTo(110101);
    }
}
