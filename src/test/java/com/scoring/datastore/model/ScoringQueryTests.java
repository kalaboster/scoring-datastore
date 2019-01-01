package com.scoring.datastore.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.scoring.datastore.utils.FileUtil.readResourceFile;

public class ScoringQueryTests {

    @Test
    public void testGenerateDefaultSuccess() {

        ScoringQuery scoringQuery = new ScoringQuery();
        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();

        Assert.assertEquals(new Integer(0), scoringQueryModel.getOrder().getDate());
        Assert.assertEquals(true, scoringQueryModel.getSelect().isDate());

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

        Assert.assertEquals("1:05", scoringModels.get(0).getViewTime());
    }

}
