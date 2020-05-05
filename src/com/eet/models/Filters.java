package com.eet.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Filters {

    private String title;
    private Type type;
    private String place;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> keywords = new ArrayList<>();
    private int spaceLimit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLocation() {
        return place;
    }

    public void setLocation(String place) {
        this.place = place;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public int getSpaceLimit() {
        return spaceLimit;
    }

    public void setSpaceLimit(int spaceLimit) {
        this.spaceLimit = spaceLimit;
    }
}