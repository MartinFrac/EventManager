package com.eet.ui;

import javax.swing.*;
import java.awt.*;

public class SmallFrame extends JFrame {

    private static SmallFrame jFrame;

    public SmallFrame(JPanel panel) {

        this.setTitle("GUI_project");
        this.setSize(400,500);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        jFrame = this;
    }

    public SmallFrame(JPanel panel, Component component) {
        this.setTitle("GUI_project");
        this.setSize(400,500);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        component.requestFocusInWindow();
        this.setVisible(true);
        this.setResizable(false);
        jFrame = this;
    }

    public static SmallFrame getjFrame() {
        return jFrame;
    }

    public void changePanel(JPanel panel) {
        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().add(panel);
        jFrame.revalidate();
        jFrame.repaint();
    }
}
