package com.scoring.datastore.model;

import java.util.List;

public interface ScoringQueryBuilder {

    ScoringQueryModel generateDefault();

    List<ScoringModel> loadStore(String pathStore);

    List<ScoringQueryModel> query(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);

}
