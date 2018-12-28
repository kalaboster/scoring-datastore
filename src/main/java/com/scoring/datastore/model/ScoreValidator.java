package com.scoring.datastore.model;

class ScoreValidator {

    private ValidScoreBuilder validScoreBuilder;


    ScoreValidator(ScoringModel scoringModel) {
        this.validScoreBuilder = new ValidScoreBuilder(scoringModel);
    }

    void validate(){
        validScoreBuilder.validateStb();
        validScoreBuilder.validateTitle();
        validScoreBuilder.validateProvidor();
        validScoreBuilder.validateDate();
        validScoreBuilder.validateViewTime();
        validScoreBuilder.validateRev();
    }
}
