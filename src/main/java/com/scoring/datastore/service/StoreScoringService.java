package com.scoring.datastore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StoreScoringService implements ScoringService {

    @Override
    public boolean process(MultipartFile multipartFile) {


        return true;
    }
}
