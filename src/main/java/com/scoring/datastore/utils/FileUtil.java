package com.scoring.datastore.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    static String readFileToString(File file) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        return contentBuilder.toString();

    }
}
