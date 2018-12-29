package com.scoring.datastore.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Properties;


@RunWith(SpringRunner.class)
public class FileUtilTests {

    @Test
    public void testReadResourceToString() throws Exception {

        File file = FileUtil.readResourceFile("/com/scoring/datastore/datastoreInput.txt");
        String testString = FileUtil.readFileToString(file);

        Assert.assertTrue(testString.startsWith("STB|TITLE|PROVIDER|DATE|REV|VIEW_TIME"));
    }

    @Test
    public void testReadPropertiesFileToStream() throws Exception {

        Properties properties = FileUtil.readPropertiesFileToStream("/application.properties");

        Assert.assertEquals("./scoring-datastore", properties.getProperty("scoring.store.root"));

    }


}
