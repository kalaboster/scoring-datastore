package com.scoring.datastore.model;

import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        List<ScoringModel> scoringModels = new LinkedList<>();

        try {
            for (Path path : Files.newDirectoryStream(Paths.get(pathStore),
                    path -> path.toString().endsWith(".json")).iterator().next()) {
                ScoringModel scoringModel = new ScoringModel();

                //loadFilePath(scoringModel, "string", pathStore);
                //loodFile(scoringModel, "string");

                scoringModels.add(scoringModel);
            }

        } catch (Exception e) {
            throw new ScoringQueryException("error");
        }

        return scoringModels;
    }

    @Override
    public List<ScoringQueryModel> select(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {


        return new LinkedList<ScoringQueryModel>();
    }

    @Override
    public List<ScoringQueryModel> order(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {


        return new LinkedList<ScoringQueryModel>();
    }

    @Override
    public List<ScoringQueryModel> filter(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {


        return new LinkedList<ScoringQueryModel>();
    }


    protected void loadFilePath(ScoringModel scoringModel, String file, String dbName) {

        String[] parts = file.split(dbName);
        String[] strings = parts[1].split("(/|_)");
        scoringModel.setProvider(strings[1]);
        scoringModel.setStb(strings[2]);
        scoringModel.setTitle(strings[3]);
        scoringModel.setDate(strings[4].replace(".json", ""));

    }

    protected void loadFile(ScoringModel scoringModel, String file) {

        try {
            String carInfoJson = new String(Files.readAllBytes(Paths.get(file.replace("%20", " ")))).replace("%20", " ");
            Gson gson = new Gson();
            ScoringFile scoringFile = gson.fromJson(carInfoJson, ScoringQuery.ScoringFile.class);
            scoringModel.setViewTime(scoringFile.getViewTime());
            scoringModel.setRev(scoringFile.getRev());
        } catch (Exception e) {
            throw new ScoringQueryException("loodFile error.");
        }
    }

    class ScoringFile {
        String rev;
        String viewTime;

        public String getRev() {
            return rev;
        }

        public void setRev(String rev) {
            this.rev = rev;
        }

        public String getViewTime() {
            return viewTime;
        }

        public void setViewTime(String viewTime) {
            this.viewTime = viewTime;
        }
    }

}
