package com.eet.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BigFrame extends JFrame {

    private static BigFrame jFrame;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 600;

    public BigFrame(JPanel jPanel) {
        this.setTitle("GUI_project");
        this.setSize(WIDTH,HEIGHT);
        this.getContentPane().add(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
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

    public BigFrame(JPanel jPanel, Component component) {
        this.setTitle("GUI_project");
        this.setSize(900,600);
        this.getContentPane().add(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static BigFrame getjFrame() {
        return jFrame;
    }

    public void changePanel(JPanel panel) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
