package au.net.woodberry.services.yahoofinance.domain.adapters;

import fr.ybonnel.csvengine.adapter.AdapterCsv;
import org.joda.time.LocalDate;

public class AdapterYahooLocalDate extends AdapterCsv<LocalDate> {

    @Override
    public LocalDate parse(String string) {
        String[] parsedDate = string.split("[-]");  // Support for all flavors of dates received from yahoo
        if (parsedDate.length != 3) {
            parsedDate = string.split("[/]");
        }
        int year  = Integer.parseInt(parsedDate[0]);
        int month = Integer.parseInt(parsedDate[1]);
        int day   = Integer.parseInt(parsedDate[2]);
        return new LocalDate(year, month, day);
    }

    @Override
    public String toString(LocalDate localDate) {
        return localDate.toString();
    }

}
