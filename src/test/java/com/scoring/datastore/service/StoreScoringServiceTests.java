package com.scoring.datastore.service;

import com.scoring.datastore.utils.FileUtil;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;

public class StoreScoringServiceTests {

    StoreScoringService storeScoringService;

    @BeforeTest
    public void setup() {
        storeScoringService = new StoreScoringService();
    }

    @Test
    public void testTransformSuccess() throws Exception {
        File testFile = FileUtil.readResourceFile("/datastoreInput.txt");
        byte[] fileBytes = Files.readAllBytes(testFile.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("datastoreInput", "out/test/datastoreInput.txt", MediaType.TEXT_PLAIN_VALUE, fileBytes);

        File file = storeScoringService.transform(mockMultipartFile);

        Assert.assertNotNull(file);
        Assert.assertEquals(234, file.length());
    }

    @Test(expectedExceptions = ScoringServiceException.class)
    public void testTransformNullFile() {
        storeScoringService.transform(null);
    }

}
