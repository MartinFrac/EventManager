package com.eet.ui;

import javax.swing.*;
import java.awt.*;

public class DesignatedFrame extends JFrame {

    public DesignatedFrame(int width, int height) {

        this.setTitle("GUI_project");
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void changePanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.pack();
        this.revalidate();
        this.repaint();
    }

    public void changePanel(JPanel panel, Component component) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.pack();
        this.revalidate();
        this.repaint();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                component.requestFocusInWindow();
            }
        });
    }
}
