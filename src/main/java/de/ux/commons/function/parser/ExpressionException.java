package de.ux.commons.function.parser;

public class ExpressionException extends RuntimeException {

    public ExpressionException() {
    }

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionException(Throwable cause) {
        super(cause);
    }
}
