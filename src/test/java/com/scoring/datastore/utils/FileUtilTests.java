package com.scoring.datastore.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;


@RunWith(SpringRunner.class)
public class FileUtilTests {

    @Test
    public void testReadResourceToString() throws Exception {

        File file = FileUtil.readResourceFile("/datastoreInput.txt");
        String testString = FileUtil.readFileToString(file);

        Assert.assertTrue(testString.startsWith("STB|TITLE|PROVIDER|DATE|REV|VIEW_TIME"));
    }

}
