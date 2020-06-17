package com.eet.ui;

import java.awt.*;

public abstract class AbstractBuilder<SELF, T> implements Builder<SELF, T> {

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected Color background;
    protected Color foreground;
    protected boolean isEnabled = true;
    protected boolean isVisible = true;
    protected Font font;

    public SELF withBounds (int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        return self();
    }

    public SELF withBackground (Color background) {
        this.background = background;
        return self();
    }

    public SELF withForeground (Color foreground) {
        this.foreground = foreground;
        return self();
    }

    public SELF enabled (boolean isEnabled) {
        this.isEnabled = isEnabled;
        return self();
    }

    public SELF visible (boolean isVisible) {
        this.isVisible = isVisible;
        return self();
    }

    public SELF withFont (Font font) {
        this.font = font;
        return self();
    }

    public abstract T build();

    private SELF self() {
        return (SELF) this;
    }
}