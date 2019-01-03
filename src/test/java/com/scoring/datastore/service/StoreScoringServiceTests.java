package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import com.scoring.datastore.model.ScoringQuery;
import com.scoring.datastore.model.ScoringQueryModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "build/resources/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

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
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "build/resources/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);
        List<ScoringModel> scoringModels = storeScoringService.build(file);

        Assert.assertEquals("2014-04-01", scoringModels.get(0).getDate());
    }

    @Test
    public void testValidateSuccess() {

        ScoringModel scoringModel = new ScoringModel();
        scoringModel.setTitle("Title");
        scoringModel.setStb("ShortSTB");
        scoringModel.setProvider("Provider");
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
        scoringModel.setProvider("Provider");
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
        scoringModel.setProvider("Provider");
        scoringModel.setDate("2014-04-03");
        scoringModel.setViewTime("2:400");
        scoringModel.setRev("12.00");

        StoreScoringService storeScoringService = new StoreScoringService();
        storeScoringService.init(".", "scoring-datastore-default");
        storeScoringService.store(scoringModel, ".", "scoring-datastore-default");

        Assert.assertTrue(new File("scoring-datastore-default/provider/shortstb_title_2014-04-03.json").isFile());
        new File("scoring-datastore-default/provider/shortstb_title_2014-04-03.json").delete();
        new File("scoring-datastore-default/provider").delete();
        new File("scoring-datastore-default").delete();
    }

    @Test
    public void testQueryFilterSuccess() throws Exception {

        File testFile = FileUtil.readResourceFile("/com/scoring/datastore/datastoreInput.txt");
        byte[] fileBytes = Files.readAllBytes(testFile.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "build/resources/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);
        storeScoringService.build(file);
        ScoringQuery scoringQuery = new ScoringQuery();
        StoreScoringService storeScoringService = new StoreScoringService();

        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("DATE", "2014-04-02");

        ScoringQueryModel scoringQueryModel = scoringQuery.generateDefault();
        scoringQueryModel.getFilter().setMap(filterMap);

        List<ScoringModel> scoringModels = storeScoringService.query(scoringQueryModel, ".", "build/resources/test/rootdir/scoring-datastore-test");

        Assert.assertEquals(2, scoringModels.size());
    }

    @Test
    public void testQueryDefaultSuccess() throws Exception {

        File testFile = FileUtil.readResourceFile("/com/scoring/datastore/datastoreInput.txt");
        byte[] fileBytes = Files.readAllBytes(testFile.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "build/resources/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);
        storeScoringService.build(file);
        ScoringQuery scoringQuery = new ScoringQuery();
        StoreScoringService storeScoringService = new StoreScoringService();

        List<ScoringModel> scoringModels = storeScoringService.query(scoringQuery.generateDefault(), ".", "build/resources/test/rootdir/scoring-datastore-test");

        Assert.assertEquals(4, scoringModels.size());
    }

}
