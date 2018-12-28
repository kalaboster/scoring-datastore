package com.scoring.datastore.model;

public class ScoreValidatorException extends RuntimeException {

    public ScoreValidatorException(String message) {
        super(message);
    }

    public ScoreValidatorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
