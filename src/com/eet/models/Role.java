package com.eet.models;

public enum Role {

    ADMIN(1),
    EVENT_ORGANISER(2),
    USER(3);

    private int level;


    Role(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
