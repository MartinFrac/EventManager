package com.eet.ui;

import javax.swing.*;
import java.awt.*;

public class BigFrame extends JFrame {

    private static JFrame jFrame;

    public BigFrame(JPanel jPanel, Component component) {

        this.setTitle("GUI_project");
        this.setSize(900,600);
        this.getContentPane().add(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        component.requestFocusInWindow();
        this.setVisible(true);
        jFrame = this;
    }

    public static JFrame getjFrame() {
        return jFrame;
    }

    public void changePanel(JPanel panel) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
