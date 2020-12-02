package se.skoglycke;

public class NoobiamException extends RuntimeException {

    public NoobiamException(final String message) {
        super("William is a n00b. Message: " + message);
    }
}
