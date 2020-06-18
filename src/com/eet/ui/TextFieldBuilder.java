package com.eet.ui;

import javax.swing.*;

public class TextFieldBuilder extends AbstractBuilder<TextFieldBuilder, JTextField> {

    public TextFieldBuilder(String text) {
        super(text);
    }

    @Override
    public JTextField build() {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        if (background != null) {
            textField.setBackground(background);
        }
        if (foreground != null) {
            textField.setForeground(foreground);
        }
        textField.setEnabled(isEnabled);
        textField.setVisible(isVisible);
        if (font != null) {
            textField.setFont(font);
        }
        return textField;
    }
}
