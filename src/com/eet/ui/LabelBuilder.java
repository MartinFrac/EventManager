package com.eet.ui;

import javax.swing.*;

public class LabelBuilder extends AbstractBuilder<LabelBuilder, JLabel> {

    private String text;

    public LabelBuilder(String text) {
        this.text = text;
    }

    @Override
    public JLabel build() {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        if (background != null) {
            label.setBackground(background);
        }
        if (foreground != null) {
            label.setForeground(foreground);
        }
        label.setEnabled(isEnabled);
        label.setVisible(isVisible);
        if (font != null) {
            label.setFont(font);
        }
        return label;
    }
}
