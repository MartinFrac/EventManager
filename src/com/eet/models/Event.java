package com.eet.models;

import java.time.LocalDateTime;

public class Event {

    private int id;
    private Repetition repetition;
    private Type type;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private int spaceLimitations;
    private String place;

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Repetition getRepetition() {
        return repetition;
    }

    public void setRepetition(Repetition repetition) {
        this.repetition = repetition;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getSpaceLimitations() {
        return spaceLimitations;
    }

    public void setSpaceLimitations(int spaceLimitations) {
        this.spaceLimitations = spaceLimitations;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", repetition=").append(repetition);
        sb.append(", type=").append(type);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", dateTime=").append(dateTime);
        sb.append(", spaceLimitations=").append(spaceLimitations);
        sb.append(", place='").append(place).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
