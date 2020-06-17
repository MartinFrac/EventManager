package com.eet.ui;

public enum Frame {

    Small(400, 500),
    Big(600, 900);

    private int width;
    private int height;

    Frame(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
