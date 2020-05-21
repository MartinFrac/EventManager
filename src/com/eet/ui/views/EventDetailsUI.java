package com.eet.ui.views;

import javax.swing.*;

public class EventDetailsUI extends JPanel {

    private JButton createButton;

    public JButton getCreateButton() {
        return createButton;
    }

    public EventDetailsUI() {
        createButton = new JButton();
    }
}
