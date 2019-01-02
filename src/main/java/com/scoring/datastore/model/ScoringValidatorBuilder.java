package com.scoring.datastore.model;

public class ScoringValidatorBuilder implements ScoringBuilder {

    private ScoringModel scoringModel;

    ScoringValidatorBuilder(ScoringModel scoringModel) {
        this.scoringModel = scoringModel;
    }

    @Override
    public void validateStb() {
        if (this.scoringModel.getStb() == null) {
            throw new ScoringValidatorException("STB is null.");
        }
        if (this.scoringModel.getStb().length() > 63) {
            throw new ScoringValidatorException("STB length needs to be 64 characgters not: " + this.scoringModel.getStb().length() + 1);
        }
    }

    @Override
    public void validateTitle() {
        if (this.scoringModel.getTitle() == null) {
            throw new ScoringValidatorException("Title is null.");
        }
        if (this.scoringModel.getTitle().length() > 63) {
            throw new ScoringValidatorException("Title length needs to be 64 characgters not: " + this.scoringModel.getTitle().length() + 1);
        }
    }

    @Override
    public void validateProvidor() {
        if (this.scoringModel.getProvider() == null) {
            throw new ScoringValidatorException("Provider is null.");
        }
        if (this.scoringModel.getProvider().length() > 63) {
            throw new ScoringValidatorException("Providor length needs to be 64 characgters not: " + this.scoringModel.getProvider().length() + 1);
        }
    }


    @Override
    public void validateDate() {
        if (this.scoringModel.getDate() == null) {
            throw new ScoringValidatorException("Date is null.");
        }
        if (!this.scoringModel.getDate().matches("(\\d\\d\\d\\d-\\d\\d-\\d\\d)")) {
            throw new ScoringValidatorException("Date doesn't match YYYY-MM-DD");
        }
    }


    @Override
    public void validateRev() {
        if (this.scoringModel.getRev() == null) {
            throw new ScoringValidatorException("Rev is null.");
        }
        if (!this.scoringModel.getRev().matches("(\\d)*\\.\\d\\d")) {
            throw new ScoringValidatorException("Date doesn't match USD money pattern");
        }
    }


    @Override
    public void validateViewTime() {
        if (this.scoringModel.getViewTime() == null) {
            throw new ScoringValidatorException("Rev is null.");
        }
        if (!this.scoringModel.getViewTime().matches("(\\d)*:\\d\\d")) {
            throw new ScoringValidatorException("Date doesn't match time pattern hours:minutes");
        }
    }

}
