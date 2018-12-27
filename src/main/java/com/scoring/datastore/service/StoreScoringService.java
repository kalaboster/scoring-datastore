package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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

        return new LinkedList<ScoringModel>();
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
