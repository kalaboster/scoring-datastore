package com.scoring.datastore.service;

import com.scoring.datastore.model.*;
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
    public void init(String root, String dataStoreName) {
        ScoringModelStorer scoringModelStorer = new ScoringModelStorer();
        scoringModelStorer.init(root + File.separator + dataStoreName);
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
                    .withHeader(ScoringModel.StoreColumns.STB.getText(),
                            ScoringModel.StoreColumns.TITLE.getText(),
                            ScoringModel.StoreColumns.PROVIDER.getText(),
                            ScoringModel.StoreColumns.DATE.getText(),
                            ScoringModel.StoreColumns.REV.getText(),
                            ScoringModel.StoreColumns.VIEW_TIME.getText())
                    .withSkipHeaderRecord()
                    .withIgnoreHeaderCase()
                    .withTrim());

            for (CSVRecord csvRecord : parser) {
                ScoringModel scoringModel = new ScoringModel();

                scoringModel.setStb(csvRecord.get(ScoringModel.StoreColumns.STB.getText()));
                scoringModel.setTitle(csvRecord.get(ScoringModel.StoreColumns.TITLE.getText()));
                scoringModel.setProvider(csvRecord.get(ScoringModel.StoreColumns.PROVIDER.getText()));
                scoringModel.setDate(csvRecord.get(ScoringModel.StoreColumns.DATE.getText()));
                scoringModel.setRev(csvRecord.get(ScoringModel.StoreColumns.REV.getText()));
                scoringModel.setViewTime(csvRecord.get(ScoringModel.StoreColumns.VIEW_TIME.getText()));

                scoringModels.add(scoringModel);
            }

        } catch (IOException e) {
            throw new ScoringServiceException("Error transforming file: " + file.getName());
        }

        return scoringModels;
    }

    @Override
    public boolean validate(ScoringModel scoringModel) {

        boolean valid = true;

        try {
            ScoringValidator scoringValidator = new ScoringValidator(scoringModel);
            scoringValidator.validate();
        } catch (ScoringValidatorException e) {
            valid = false;
        }

        return valid;
    }


    @Override
    public boolean store(ScoringModel scoringModel, String root, String dataStoreName) {

        ScoringStorer scoringStorer = new ScoringModelStorer();
        scoringStorer.makeRecord(scoringModel, root + File.separator + dataStoreName);

        return true;
    }

    @Override
    public List<ScoringModel> query(ScoringQueryModel scoringQueryModel, String root, String dataStoreName) {

        ScoringQuery scoringQuery = new ScoringQuery();
        scoringQuery.loadStore(root + File.separator, dataStoreName);

        List<ScoringModel> scoringModels = scoringQuery.loadStore(root + File.separator, dataStoreName);
        scoringQuery.select(scoringQueryModel, scoringModels);
        scoringQuery.filter(scoringQueryModel, scoringModels);

        return scoringModels;
    }

}
