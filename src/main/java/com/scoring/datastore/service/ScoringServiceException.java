package com.scoring.datastore.service;

class ScoringServiceException extends RuntimeException {

    ScoringServiceException(String message) {
        super(message);
    }

    ScoringServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
