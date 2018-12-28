package com.scoring.datastore.model;

class ScoringValidator {

    private ScoringValidatorBuilder validScoreBuilder;


    ScoringValidator(ScoringModel scoringModel) {
        this.validScoreBuilder = new ScoringValidatorBuilder(scoringModel);
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
