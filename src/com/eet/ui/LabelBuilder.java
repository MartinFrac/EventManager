package com.eet.ui;

import javax.swing.*;

public class LabelBuilder extends AbstractBuilder<LabelBuilder, JLabel> {

    public LabelBuilder(String text) {
        super(text);
    }

    @Override
    public JLabel build() {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        if (background != null) {
            label.setBackground(background);
        }
        if (foreground != null) {
            label.setForeground(foreground);
        }
        label.setEnabled(isEnabled);
        label.setText(text);
        label.setVisible(isVisible);
        if (font != null) {
            label.setFont(font);
        }
        return label;
    }
}
