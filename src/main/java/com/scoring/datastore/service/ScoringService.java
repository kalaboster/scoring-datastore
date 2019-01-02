package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import com.scoring.datastore.model.ScoringQueryModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ScoringService {

    void init(String root, String dataStoreName);

    File transform(MultipartFile multipartFile);

    List<ScoringModel> build(File file);

    boolean validate(ScoringModel scoringModel);

    boolean store(ScoringModel scoringModel, String root, String dataStoreName);

    List<ScoringModel> query(ScoringQueryModel scoringQueryModel, String root, String dataStoreName);
}
