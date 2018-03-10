package de.ux.commons.function.replacer;

public class ReplacementException extends RuntimeException {

    public ReplacementException() {
    }

    public ReplacementException(String message) {
        super(message);
    }

    public ReplacementException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReplacementException(Throwable cause) {
        super(cause);
    }
}
