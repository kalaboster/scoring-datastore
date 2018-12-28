package com.scoring.datastore.model;

public interface ScoringStorer {

    boolean init(String root);

    boolean makeRecord(ScoringModel scoringModel, String root);

}
