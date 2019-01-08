package com.scoring.datastore.controller;

import com.scoring.datastore.model.ScoringModel;
import com.scoring.datastore.model.ScoringQuery;
import com.scoring.datastore.model.ScoringQueryModel;
import com.scoring.datastore.service.ScoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
public class ScoringController {

    private static final Logger log = LoggerFactory.getLogger(ScoringController.class);

    private transient ScoringService scoringService;

    @Autowired
    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @RequestMapping(value = "/scoring/query", method = RequestMethod.POST)
    public List<ScoringModel> query(@RequestBody ScoringQueryModel scoringQueryModel) {

        log.info("Scoring Query: " + scoringQueryModel.getSelect());

        ScoringQuery scoringQuery = new ScoringQuery();
        if (scoringQueryModel == null) {
            scoringQueryModel = scoringQuery.generateDefault();
        }

        return scoringService.query(scoringQueryModel, ".", "scoring-datastore-default");
    }

    @RequestMapping(value = "/scoring/store", method = RequestMethod.POST)
    public boolean store(@RequestPart("file") MultipartFile multipartFile) {

        boolean successful = true;

        scoringService.init(".", "scoring-datastore-default");
        File file = scoringService.transform(multipartFile);
        for (ScoringModel scoringModel : scoringService.build(file)) {
            if (!scoringService.validate(scoringModel)) {
                successful = false;
                continue;
            }
            scoringService.store(scoringModel, ".", "scoring-datastore-default");
        }

        return successful;
    }

}