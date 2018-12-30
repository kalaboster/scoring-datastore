package com.scoring.datastore.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ScoringValidatorBuilderTests {

    @Test
    public void testValidateSuccess() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("12.00");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);
        scoringValidator.validate();
    }

    @Test(expected = ScoringValidatorException.class)
    public void testValidateTime() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:400");
        scoringModel.setRev("12.00");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);
        scoringValidator.validate();
    }

    @Test(expected = ScoringValidatorException.class)
    public void testValidateRevFailPattern() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("0.000");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);
        scoringValidator.validate();
    }

    @Test(expected = ScoringValidatorException.class)
    public void testValidateFailDatePattern() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("Bad");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("4.00");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);
        scoringValidator.validate();
    }

    @Test(expected = ScoringValidatorException.class)
    public void testValidateFailTitleTooLong() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("titletitletitletitletitletitletitletitletitletitletitletitletitletitle");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("4.00");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);

        scoringValidator.validate();
    }

    @Test(expected = ScoringValidatorException.class)
    public void testValidateFailTitleNull() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("4.00");
        ScoringValidator scoringValidator = new ScoringValidator(scoringModel);

        scoringValidator.validate();
    }

}
