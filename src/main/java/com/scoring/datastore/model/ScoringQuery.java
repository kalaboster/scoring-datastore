package com.scoring.datastore.model;

import com.google.gson.Gson;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ScoringModel> loadStore(String pathToStore, String datastore) {

        List<ScoringModel> scoringModels = new LinkedList<>();


        File folder = new File(pathToStore + datastore);
        try {
            for (File file : folder.listFiles()) {
                for (File f : file.listFiles()) {
                    ScoringModel scoringModel = new ScoringModel();

                    loadFilePath(scoringModel, f.toString(), datastore);
                    loadFile(scoringModel, f.toString());

                    scoringModels.add(scoringModel);
                }
            }

        } catch (Exception e) {
            throw new ScoringQueryException("Error loadStore has error.");
        }

        return scoringModels;
    }

    @Override
    public List<ScoringModel> select(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {

        if (!scoringQueryModel.getSelect().isDate()) {
            scoringModels.forEach(s -> s.setDate(null));
        }
        if (!scoringQueryModel.getSelect().isProvider()) {
            scoringModels.forEach(s -> s.setProvider(null));
        }
        if (!scoringQueryModel.getSelect().isRev()) {
            scoringModels.forEach(s -> s.setRev(null));
        }
        if (!scoringQueryModel.getSelect().isStb()) {
            scoringModels.forEach(s -> s.setStb(null));
        }
        if (!scoringQueryModel.getSelect().isTitle()) {
            scoringModels.forEach(s -> s.setTitle(null));
        }
        if (!scoringQueryModel.getSelect().isViewTime()) {
            scoringModels.forEach(s -> s.setViewTime(null));
        }

        return scoringModels;
    }

    @Override
    public List<ScoringModel> order(ScoringQueryModel scoringQueryModel, List<ScoringModel> scoringModels) {

        return new LinkedList<ScoringModel>();
    }

    @Override
    public List<ScoringModel> filter(ScoringQueryModel scoringQueryModel, List<ScoringModel> filteredScoringModels) {

        if (scoringQueryModel.getSelect().isDate() && scoringQueryModel.getFilter().getMap().keySet().contains("DATE")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getDate().equals(scoringQueryModel.getFilter().getMap().get("DATE")))).collect(Collectors.toList()));
        }
        if (scoringQueryModel.getSelect().isProvider() && scoringQueryModel.getFilter().getMap().keySet().contains("PROVIDER")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getProvider().equals(scoringQueryModel.getFilter().getMap().get("PROVIDER")))).collect(Collectors.toList()));
        }
        if (scoringQueryModel.getSelect().isRev() && scoringQueryModel.getFilter().getMap().keySet().contains("REV")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getRev().equals(scoringQueryModel.getFilter().getMap().get("REV")))).collect(Collectors.toList()));
        }
        if (scoringQueryModel.getSelect().isStb() && scoringQueryModel.getFilter().getMap().keySet().contains("STB")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getStb().equals(scoringQueryModel.getFilter().getMap().get("STB")))).collect(Collectors.toList()));
        }
        if (scoringQueryModel.getSelect().isTitle() && scoringQueryModel.getFilter().getMap().keySet().contains("TITLE")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getTitle().equals(scoringQueryModel.getFilter().getMap().get("TITLE")))).collect(Collectors.toList()));
        }
        if (scoringQueryModel.getSelect().isViewTime() && scoringQueryModel.getFilter().getMap().keySet().contains("VIEW_TIME")) {
            filteredScoringModels.removeAll(filteredScoringModels.stream().filter((f -> !f.getViewTime().equals(scoringQueryModel.getFilter().getMap().get("VIEW_TIME")))).collect(Collectors.toList()));
        }

        return filteredScoringModels;
    }


    protected void loadFilePath(ScoringModel scoringModel, String file, String dataStore) {

        String[] parts = file.split(dataStore);
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
