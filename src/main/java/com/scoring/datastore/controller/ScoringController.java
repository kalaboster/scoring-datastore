package com.scoring.datastore.controller;

import com.scoring.datastore.model.ScoringModel;
import com.scoring.datastore.model.ScoringQuery;
import com.scoring.datastore.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class ScoringController {

    private transient ScoringService scoringService;

    @Autowired
    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @RequestMapping(value = "/scoring", method = RequestMethod.GET)
    public List<ScoringModel> getStore() {

        ScoringQuery scoringQuery = new ScoringQuery();
        return scoringService.query(scoringQuery.generateDefault());

    }

    @RequestMapping(value = "/scoring", method = RequestMethod.POST)
    public boolean filestate(@RequestPart("file") MultipartFile multipartFile) {

        boolean successful = true;

        scoringService.init();
        File file = scoringService.transform(multipartFile);
        for (ScoringModel scoringModel : scoringService.build(file)) {
            if (!scoringService.validate(scoringModel)) {
                successful = false;
                continue;
            }
            scoringService.store(scoringModel);
        }

        return successful;
    }

}