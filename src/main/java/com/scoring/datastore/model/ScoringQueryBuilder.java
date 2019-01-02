package com.scoring.datastore.model;

import java.util.List;

public interface ScoringQueryBuilder {

    ScoringQueryModel generateDefault();

    List<ScoringModel> loadStore(String pathStore, String datastore);

    List<ScoringModel> select(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);

    List<ScoringModel> order(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);

    List<ScoringModel> filter(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);
}
