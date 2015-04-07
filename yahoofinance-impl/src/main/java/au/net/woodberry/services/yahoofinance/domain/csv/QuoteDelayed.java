package au.net.woodberry.services.yahoofinance.domain.csv;

import au.net.woodberry.services.yahoofinance.impl.utils.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class QuoteDelayed {

    private String symbol;
    
    private double last;

    private LocalDate date;

    private LocalTime time;
    
    private DateTime dateTime;

    private double change;

    private Double open;

    private Double daysRangeHigh;

    private Double daysRangeLow;

    private double volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getDaysRangeHigh() {
        return daysRangeHigh;
    }

    public void setDaysRangeHigh(Double daysRangeHigh) {
        this.daysRangeHigh = daysRangeHigh;
    }

    public Double getDaysRangeLow() {
        return daysRangeLow;
    }

    public void setDaysRangeLow(Double daysRangeLow) {
        this.daysRangeLow = daysRangeLow;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public static class InputStreamConverter {
        
        private static final DateTimeFormatter DATE_DTF = DateTimeFormat.forPattern("MM/dd/YYYY");
        private static final DateTimeFormatter TIME_DTF = DateTimeFormat.forPattern("hh:mmaa");
        private static final DateTimeZone DATE_TIME_ZONE = DateTimeZone.UTC;
        private static final String NA = "N/A";
        private static final int SYMBOL = 0;
        private static final int LAST = 1;
        private static final int DATE = 2;
        private static final int TIME = 3;
        private static final int CHANGE = 4;
        private static final int OPEN = 5;
        private static final int DAYS_RANGE_HIGH = 6;
        private static final int DAYS_RANGE_LOW = 7;
        private static final int VOLUME = 8;

        public static QuoteDelayed convert(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            QuoteDelayed quoteDelayed = new QuoteDelayed();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> lineData = Arrays.asList(line.split(","));
                quoteDelayed.setSymbol(lineData.get(SYMBOL).replaceAll("\"", ""));
                quoteDelayed.setLast(Double.valueOf(lineData.get(LAST)));
                LocalDate date = DATE_DTF.parseLocalDate(lineData.get(DATE).replaceAll("\"", ""));
                LocalTime time = TIME_DTF.parseLocalTime(lineData.get(TIME).replaceAll("\"", ""));
                quoteDelayed.setDate(date);
                quoteDelayed.setTime(time);
                quoteDelayed.setDateTime(DateUtils.construct(date, time, DATE_TIME_ZONE));
                quoteDelayed.setChange(Double.valueOf(lineData.get(CHANGE)));
                String open = lineData.get(OPEN);
                String daysRangeHigh = lineData.get(DAYS_RANGE_HIGH);
                String daysRangeLow = lineData.get(DAYS_RANGE_LOW);
                quoteDelayed.setOpen(open.equals(NA) ? null : Double.valueOf(open));
                quoteDelayed.setDaysRangeLow(daysRangeLow.equals(NA) ? null : Double.valueOf(daysRangeLow));
                quoteDelayed.setDaysRangeHigh(daysRangeHigh.equals(NA) ? null : Double.valueOf(daysRangeHigh));
                quoteDelayed.setVolume(Double.valueOf(lineData.get(VOLUME)));
            }
            return quoteDelayed;
        }
    }
}
