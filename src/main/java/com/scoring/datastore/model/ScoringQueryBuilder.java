package com.scoring.datastore.model;

import java.util.List;

public interface ScoringQueryBuilder {

    ScoringQueryModel generateDefault();

    List<ScoringModel> loadStore(String pathStore, String datastore);

    List<ScoringQueryModel> select(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);

    List<ScoringQueryModel> order(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);

    List<ScoringQueryModel> filter(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels);
}
