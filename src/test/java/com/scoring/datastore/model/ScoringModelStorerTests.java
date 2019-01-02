package com.scoring.datastore.model;

import org.junit.Assert;
import org.junit.Test;

public class ScoringModelStorerTests {

    final String testDir = "./out/test-datastore";

    @Test
    public void testInitSuccess() throws Exception {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("12.00");

        ScoringStorer scoringStorer = new ScoringModelStorer();
        Assert.assertTrue(scoringStorer.init(testDir));
    }

    @Test
    public void testMakeRecordSuccess() throws Exception {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("12.00");

        ScoringStorer scoringStorer = new ScoringModelStorer();
        Assert.assertTrue(scoringStorer.init(testDir));
        scoringStorer.makeRecord(scoringModel, testDir);

    }


}
