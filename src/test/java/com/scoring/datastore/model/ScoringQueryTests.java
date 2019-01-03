package com.scoring.datastore.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.scoring.datastore.utils.FileUtil.readResourceFile;

public class ScoringQueryTests {

    @Test
    public void testGenerateDefaultSuccess() {

        ScoringQuery scoringQuery = new ScoringQuery();
        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();

        Assert.assertEquals(new Integer(0), scoringQueryModel.getOrder().getDate());
        Assert.assertTrue(scoringQueryModel.getSelect().isDate());

    }

    @Test
    public void testLoadPath() throws Exception {

        File file = readResourceFile("/rootdir/scoring-datastore-test/buena vista/stb1_unbreakable_2014-04-03.json");
        ScoringQuery scoringQuery = new ScoringQuery();
        ScoringModel scoringModel = new ScoringModel();

        scoringQuery.loadFilePath(scoringModel, file.getAbsolutePath(), "rootdir/scoring-datastore-test");

        Assert.assertEquals("2014-04-03", scoringModel.getDate());
    }

    @Test
    public void testLoadFile() throws Exception {

        File file = readResourceFile("/rootdir/scoring-datastore-test/buena vista/stb1_unbreakable_2014-04-03.json");
        ScoringQuery scoringQuery = new ScoringQuery();
        ScoringModel scoringModel = new ScoringModel();

        scoringQuery.loadFile(scoringModel, file.getAbsolutePath());

        Assert.assertEquals("2:05", scoringModel.getViewTime());
    }

    @Test
    public void testLoadStoreSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Assert.assertEquals(4, scoringModels.size());
    }


    @Test
    public void testSelectSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getSelect().setDate(false);
        scoringQueryModel.getSelect().setTitle(false);

        List<ScoringModel> returnList = scoringQuery.select(scoringQueryModel, scoringModels);

        Assert.assertNull(returnList.get(0).getDate());
    }

    @Test
    public void testFilterSTBSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("STB", "stb1");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(2, returnList.size());
    }


    @Test
    public void testFilterDATESuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("DATE", "2014-04-02");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(2, returnList.size());
    }


    @Test
    public void testFilterPROVIDERSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("PROVIDER", "warner bros");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(3, returnList.size());
    }


    @Test
    public void testFilterREVSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("REV", "4.00");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(2, returnList.size());
    }


    @Test
    public void testFilterVIEWTIMESuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("VIEW_TIME", "2:45");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(1, returnList.size());
    }

    @Test
    public void testFilterTITLESuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("TITLE", "the matrix");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(2, returnList.size());
    }

    @Test
    public void testFilterTWOSuccess() throws Exception {

        File file = readResourceFile("/rootdir");
        ScoringQuery scoringQuery = new ScoringQuery();
        List<ScoringModel> scoringModels = scoringQuery.loadStore(file.getAbsolutePath(), "/scoring-datastore-test");

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("TITLE", "the matrix");
        filterMap.put("PROVIDER", "warner bros");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> returnList = scoringQuery.filter(scoringQueryModel, scoringModels);

        Assert.assertEquals(2, returnList.size());
    }

}
