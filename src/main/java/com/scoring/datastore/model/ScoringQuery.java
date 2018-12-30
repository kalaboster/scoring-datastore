package com.scoring.datastore.model;

import java.util.LinkedList;
import java.util.List;

public class ScoringQuery implements ScoringQueryBuilder {

    @Override
    public ScoringQueryModel generateDefault() {

        ScoringQueryModel scoringQueryModel = new ScoringQueryModel();

        ScoringQueryModel.Select select = new ScoringQueryModel.Select();
        select.setDate(true);
        select.setProvider(true);
        select.setRev(true);
        select.setStb(true);
        select.setTitle(true);
        select.setViewTime(true);
        scoringQueryModel.setSelect(select);

        ScoringQueryModel.Order order = new ScoringQueryModel.Order();
        order.setDate(0);
        order.setProvider(0);
        order.setViewTime(0);
        order.setTitle(0);
        order.setRev(0);
        order.setTitle(0);
        scoringQueryModel.setOrder(order);

        scoringQueryModel.setFilter(new ScoringQueryModel.Filter());

        return scoringQueryModel;
    }

    @Override
    public List<ScoringModel> loadStore(String pathStore) {


        return new LinkedList<>();
    }

    @Override
    public List<ScoringQueryModel> query(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {


        return new LinkedList<ScoringQueryModel>();
    }

}
