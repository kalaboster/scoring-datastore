package com.scoring.datastore.model;

public interface ScoringBuilder {
    void buildStb() throws ScoringBuilderException;
    void buildTitle() throws ScoringBuilderException;
    void buildProvidor() throws ScoringBuilderException;
    void buildDate() throws ScoringBuilderException;
    void buildRev() throws ScoringBuilderException;
    void buildViewTime() throws ScoringBuilderException;
    ScoringModel getScoringModel();
}
