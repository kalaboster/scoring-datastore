package com.scoring.datastore.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScoringModelStorer implements ScoringStorer {

    @Override
    public boolean init(String root) {

        boolean complete = false;

        if (!Paths.get(root).toFile().isDirectory()) {
            Paths.get(root).toFile();
            try {
                Files.createDirectory(Paths.get(root));
            } catch (IOException io) {
                throw new ScoringStorerException("Failed to create root dir.");
            }
            complete = true;
        } else {
            complete = true;
        }

        return complete;
    }

    @Override
    public boolean makeRecord(ScoringModel scoringModel, String root) {

        boolean state = false;

        try {
            if(!new File(root + File.separator + scoringModel.getProvider().toLowerCase()).isDirectory()) {
                Files.createDirectory(Paths.get(root + File.separator + scoringModel.getProvider().toLowerCase()));
            }
        } catch (IOException io) {
            throw new ScoringStorerException("Failed to create provider dir.");
        }

        String record = scoringModel.getStb().toLowerCase() + "_" + scoringModel.getTitle().toLowerCase() + "_" + scoringModel.getDate().toLowerCase() + ".json";

        try {
            Files.write(Paths.get(root + File.separator + scoringModel.getProvider().toLowerCase() + File.separator + record.toLowerCase()), scoringModel.toString().getBytes());
            state = true;
        } catch (IOException e) {
            throw new ScoringStorerException("Failed to write record.");
        }
        return state;
    }

}
