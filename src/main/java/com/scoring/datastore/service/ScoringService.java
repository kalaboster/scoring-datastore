package com.scoring.datastore.service;

import org.springframework.web.multipart.MultipartFile;

public interface ScoringService {

    boolean process(MultipartFile multipartFile);

}
