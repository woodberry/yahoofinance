package au.net.woodberry.services.yahoofinance.domain.csv.adapters;

import fr.ybonnel.csvengine.adapter.AdapterCsv;

import java.math.BigInteger;

public class AdapterBigInteger extends AdapterCsv<BigInteger> {

    /**
     * Transform a String into Integer.
     *
     * @param string the string to transform.
     * @return the Integer transformed.
     */
    public BigInteger parse(String string) {
        return BigInteger.valueOf(Long.valueOf(string));
    }

    /**
     * Transform an Integer into String.
     *
     * @param object Integer to transform.
     * @return the resulting string.
     */
    public String toString(BigInteger object) {
        return object.toString();
    }
}
