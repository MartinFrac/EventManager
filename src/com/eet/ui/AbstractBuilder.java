package com.eet.ui;

import java.awt.*;

public abstract class AbstractBuilder<SELF, T> implements Builder<SELF, T> {

    protected String text;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected Color background;
    protected Color foreground;
    protected boolean isEnabled = true;
    protected boolean isVisible = true;
    protected Font font;

    public AbstractBuilder(String text) {
        this.text = text;
    }

    @Override
    public SELF withBounds (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return self();
    }

    @Override
    public SELF withBackground (Color background) {
        this.background = background;
        return self();
    }

    @Override
    public SELF withForeground (Color foreground) {
        this.foreground = foreground;
        return self();
    }

    @Override
    public SELF enabled (boolean isEnabled) {
        this.isEnabled = isEnabled;
        return self();
    }

    @Override
    public SELF visible (boolean isVisible) {
        this.isVisible = isVisible;
        return self();
    }

    @Override
    public SELF withFont (Font font) {
        this.font = font;
        return self();
    }

    @Override
    public abstract T build();

    private SELF self() {
        return (SELF) this;
    }
}