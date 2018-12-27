package com.scoring.datastore.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUtilTests {

    @Test
    public void testReadResourceToString() throws Exception {

        File file = FileUtil.readResourceFile("/datastoreInput.txt");
        String testString = FileUtil.readFileToString(file);

        Assert.assertTrue(testString.startsWith("STB|TITLE|PROVIDER|DATE|REV|VIEW_TIME"));
    }

}
