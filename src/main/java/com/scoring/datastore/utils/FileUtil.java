package com.scoring.datastore.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class FileUtil {

    public static File readResourceFile(String file) throws Exception {

        File resourceFile;

        if (file.isEmpty()) {
            throw new Exception("No File for readResourceFile.");
        }

        resourceFile = new File(FileUtil.class.getResource(file).getFile());

        return resourceFile;

    }

    public static String readFileToString(File file) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();


        try (Stream<String> stream = Files.lines(Paths.get(file.getPath().replaceAll("%20", " ")), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        return contentBuilder.toString();

    }

    public static Properties readPropertiesFileToStream(String propertiesFile) throws IOException {

        Properties properties = new Properties();

        if (propertiesFile.isEmpty()) {
            throw new FileNotFoundException("Missing properties file.");
        }

        InputStream propertiesStream = FileUtil.class.getResourceAsStream(propertiesFile);

        if (propertiesStream != null) {
            properties.load(propertiesStream);
        }

        return properties;
    }
}
