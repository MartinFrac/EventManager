package com.eet.ui.views;

import com.eet.memory.ActiveUser;
import com.eet.ui.ButtonBuilder;
import com.eet.ui.Frame;
import com.eet.ui.Frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StudentUI extends JPanel {

    private JButton searchEventsButton;
    private JButton viewBookingsButton;
    private JButton logoutButton;

    //Constructor
    public StudentUI(){


        //pane with null layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(139,217,169));

        searchEventsButton = new ButtonBuilder("Search Events")
                .withBounds(100, 90, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SearchEventsUI searchEventsUI = new SearchEventsUI();
                        Frames.getJFrame(Frame.Big).changePanel(searchEventsUI, searchEventsUI.getGoBackButton());
                    }
                }).build();

        viewBookingsButton = new ButtonBuilder("View Bookings")
                .withBounds(100, 170, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ViewBookingsUI viewBookingsUI = new ViewBookingsUI();
                        Frames.getJFrame(Frame.Big).changePanel(viewBookingsUI, viewBookingsUI.getGoBackButton());
                    }
                }).build();

        logoutButton = new ButtonBuilder("Logout")
                .withBounds(125, 330, 150, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LoginUI loginUI = new LoginUI();
                        ActiveUser.setUser(null);
                        Frames.getJFrame(Frame.Small).changePanel(loginUI, loginUI.getLoginButton());
                    }
                }).build();

        //adding components to contentPane panel
        this.add(searchEventsButton);
        this.add(viewBookingsButton);
        this.add(logoutButton);
    }
}
