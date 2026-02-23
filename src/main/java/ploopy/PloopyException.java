package ploopy;

/**
 * Represents an exception.
 * Signals errors that occur during command parsing, execution, or storage operations.
 */
public class PloopyException extends Exception {
    public PloopyException(String message) {
        super(message);
    }
}
