package com.scoring.datastore.model;

public class ScoringValidator {

    private ScoringValidatorBuilder validScoreBuilder;


    public ScoringValidator(ScoringModel scoringModel) {
        this.validScoreBuilder = new ScoringValidatorBuilder(scoringModel);
    }

    public void validate(){
        validScoreBuilder.validateStb();
        validScoreBuilder.validateTitle();
        validScoreBuilder.validateProvidor();
        validScoreBuilder.validateDate();
        validScoreBuilder.validateViewTime();
        validScoreBuilder.validateRev();
    }
}
