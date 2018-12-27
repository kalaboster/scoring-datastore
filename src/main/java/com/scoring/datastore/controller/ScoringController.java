package com.scoring.datastore.controller;

import com.scoring.datastore.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ScoringController {

    private transient ScoringService scoringService;

    @Autowired
    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @RequestMapping(value = "/scoring", method = RequestMethod.POST)
    public boolean filestate(@RequestPart("file") MultipartFile multipartFile) {

        return scoringService.process(multipartFile);
    }


}
