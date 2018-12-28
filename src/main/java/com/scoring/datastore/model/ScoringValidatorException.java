package com.scoring.datastore.model;

public class ScoringValidatorException extends RuntimeException {

    public ScoringValidatorException(String message) {
        super(message);
    }

    public ScoringValidatorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
