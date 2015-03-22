package au.net.woodberry.services.yahoofinance.enums;

public enum Frequency {
    DAY("d"), 
    WEEK("w"), 
    MONTH("m");
    
    private final String g;

    Frequency(String g) {
        this.g = g;
    }

    public String getG() {
        return g;
    }
}
