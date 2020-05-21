package com.eet.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class EventFrame extends JFrame {

    private static EventFrame jFrame;

    public EventFrame(JPanel jPanel, Component component, Vector row) {

        this.setTitle("GUI_project");
        this.setSize(900,600);
        this.getContentPane().add(jPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        component.requestFocusInWindow();
        this.setVisible(true);
        this.setResizable(false);
        jFrame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                jFrame = null;
            }
        });
    }

    public static EventFrame getjFrame() {
        return jFrame;
    }

    public void changePanel(JPanel panel, Vector row) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}