package com.scoring.datastore.service;

import com.scoring.datastore.model.ScoringModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface ScoringService {

    void init();

    File transform(MultipartFile multipartFile);

    List<ScoringModel> build(File file);

    boolean validate(ScoringModel scoringModel);

    boolean store(ScoringModel scoringModel);

}
