package com.eet.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonBuilder extends AbstractBuilder<ButtonBuilder, JButton> {

    private String text;
    private ActionListener actionListener;

    public ButtonBuilder(String text) {
        this.text = text;
    }

    public ButtonBuilder withActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        return this;
    }

    @Override
    public JButton build() {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        if (background != null) {
            button.setBackground(background);
        }
        if (foreground != null) {
            button.setForeground(foreground);
        }
        button.setEnabled(isEnabled);
        button.setVisible(isVisible);
        if (font != null) {
            button.setFont(font);
        }
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        return button;
    }
}
