package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

@Service
public class StoreScoringService implements ScoringService {

    @Override
    public void init() {

    }

    @Override
    public File transform(MultipartFile multipartFile) {

        if (multipartFile == null) {
            throw new ScoringServiceException("Posted multipart file is null.");
        }

        if (multipartFile.getOriginalFilename() == null) {
            throw new ScoringServiceException("Posted multipart file is null.");
        }

        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (Exception e) {
            throw new ScoringServiceException("Could not read file: " + file.getAbsolutePath(), e);
        }

        return file;
    }

    @Override
    public List<ScoringModel> build(File file) {

        LinkedList<ScoringModel> scoringModels = new LinkedList<>();

        try {

            char delimit = '|';
            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.DEFAULT
                    .withDelimiter(delimit)
                    .withHeader("STB", "TITLE", "PROVIDER", "DATE", "REV", "VIEW_TIME")
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim());

            for (CSVRecord csvRecord : parser) {
                ScoringModel scoringModel = new ScoringModel();

                scoringModel.setStb(csvRecord.get("STB"));
                scoringModel.setTitle(csvRecord.get("TITLE"));
                scoringModel.setProvidor(csvRecord.get("PROVIDER"));
                scoringModel.setDate(csvRecord.get("DATE"));
                scoringModel.setRev(csvRecord.get("REV"));
                scoringModel.setRev(csvRecord.get("VIEW_TIME"));

                scoringModels.add(scoringModel);
            }

        } catch (IOException e) {
            throw new ScoringServiceException("Error transforming file: " + file.getName());
        }

        return scoringModels;
    }

    @Override
    public boolean validate(ScoringModel scoringModel) {

        return true;
    }


    @Override
    public boolean store(ScoringModel scoringModel) {


        return true;
    }

}
