package org.ortsevlised;

public class TooManyRetriesException extends Exception {
    public TooManyRetriesException(String message) {
        super(message);
    }
}
