package com.scoring.datastore.model;

import org.junit.Assert;
import org.junit.Test;

public class ScoringQueryTests {

    @Test
    public void testGenerateDefaultSuccess() {

        ScoringQuery scoringQuery = new ScoringQuery();
        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();

        Assert.assertEquals(new Integer(0), scoringQueryModel.getOrder().getDate());
        Assert.assertEquals(true, scoringQueryModel.getSelect().isDate());

    }

}
