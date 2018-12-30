package com.scoring.datastore.model;

public class ScoringModel {

    private String stb;
    private String title;
    private String provider;
    private String date;
    private String rev;
    private String viewTime;

    public String getStb() {
        return stb;
    }

    public void setStb(String stb) {
        this.stb = stb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public String toString() {
        return "{ \"rev\":\"" + rev + "\", " +
                "\"viewTime\":\"" + viewTime + "\" " +
                "}";
    }
}
