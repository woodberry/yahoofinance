package au.net.woodberry.services.yahoofinance.impl.exceptions;

public class RemoteServiceInvalidResponseException extends RuntimeException {

    public RemoteServiceInvalidResponseException(String message, Exception cause) {
        super(message, cause);
    }
}
