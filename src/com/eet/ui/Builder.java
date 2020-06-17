package com.eet.ui;

import java.awt.*;

public interface Builder<SELF, T> {

    SELF withBounds (int width, int height, int x, int y);

    SELF withBackground (Color background);

    SELF withForeground (Color foreground);

    SELF enabled (boolean isEnabled);

    SELF visible (boolean isVisible);

    SELF withFont (Font font);

    T build();
}
