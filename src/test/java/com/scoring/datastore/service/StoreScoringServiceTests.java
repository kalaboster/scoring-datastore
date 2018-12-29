package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import com.scoring.datastore.utils.FileUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@RunWith(SpringRunner.class)
public class StoreScoringServiceTests {

    static StoreScoringService storeScoringService;

    @BeforeClass
    public static void setup() {
        storeScoringService = new StoreScoringService();
    }

    @Test
    public void testTransformSuccess() throws Exception {
        File testFile = FileUtil.readResourceFile("/com/scoring/datastore/datastoreInput.txt");
        byte[] fileBytes = Files.readAllBytes(testFile.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "out/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);

        Assert.assertNotNull(file);
        Assert.assertEquals(234, file.length());
    }

    @Test(expected = ScoringServiceException.class)
    public void testTransformNullFile() {
        storeScoringService.transform(null);
    }

    @Test
    public void testBuildSuccess() throws Exception {
        File testFile = FileUtil.readResourceFile("/com/scoring/datastore/datastoreInput.txt");
        byte[] fileBytes = Files.readAllBytes(testFile.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "out/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);
        List<ScoringModel> scoringModels = storeScoringService.build(file);

        Assert.assertEquals("2014-04-01", scoringModels.get(0).getDate());
    }

    @Test
    public void testValidateSuccess() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvidor("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:40");
        scoringModel.setRev("12.00");

        Assert.assertTrue(storeScoringService.validate(scoringModel));
    }

    @Test
    public void testValidateFailure() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvidor("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:400");
        scoringModel.setRev("12.00");

        Assert.assertFalse(storeScoringService.validate(scoringModel));
    }

    @Test
    public void testInitStoreSuccessful() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvidor("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:400");
        scoringModel.setRev("12.00");

        StoreScoringService storeScoringService = new StoreScoringService();
        storeScoringService.init();
        storeScoringService.store(scoringModel);

        Assert.assertTrue(new File("./scoring-datastore/provider/shortstb_title_2014-04-03.json").isFile());
        new File("./scoring-datastore/provider/shortstb_title_2014-04-03.json").delete();
        new File("./scoring-datastore/provider").delete();
        new File("./scoring-datastore").delete();
    }

}
