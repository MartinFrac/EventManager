package com.eet.ui.views;

import com.eet.memory.ActiveUser;
import com.eet.ui.*;
import com.eet.ui.Frame;

import javax.swing.*;
import java.awt.*;

public class AdminUI extends JPanel {

    private JButton searchEventsButton;
    private JButton viewBookingsButton;
    private JButton createEventButton;
    private JButton viewOwnEventsButton;
    private JButton searchStudentsButton;
    private JButton logoutButton;

    //Constructor
    public AdminUI(){

        //pane with null layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(139,217,169));

        searchEventsButton = new ButtonBuilder("Search Events")
                .withBounds(100, 40, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(e -> {
                    AdminSearchEvents adminSearchEvents = new AdminSearchEvents();
                    Frames.getJFrame(Frame.Small).dispose();
                    Frames.getJFrame(Frame.Big).changePanel(adminSearchEvents, adminSearchEvents.getGoBackButton());
                }).build();

        viewBookingsButton = new ButtonBuilder("View Bookings")
                .withBounds(100, 110, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(e -> {
                    ViewBookingsUI viewBookingsUI = new ViewBookingsUI();
                    Frames.getJFrame(Frame.Big).changePanel(viewBookingsUI, viewBookingsUI.getGoBackButton());
                }).build();


        createEventButton = new ButtonBuilder("Create Event")
                .withBounds(100, 180, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(e -> {
                    CreateEventUI createEventUI = new CreateEventUI();
                    Frames.getJFrame(Frame.Small).changePanel(createEventUI);
                }).build();

        viewOwnEventsButton = new ButtonBuilder("View Own Events")
                .withBounds(100, 250, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withActionListener(e -> {
                    ViewOwnEvents viewOwnEvents = new ViewOwnEvents();
                    Frames.getJFrame(Frame.Big).changePanel(viewOwnEvents, viewOwnEvents.getGoBackButton());
                }).build();

        searchStudentsButton = new ButtonBuilder("Search Students")
                .withBounds(100, 320, 200, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(e -> {
                    AdminSearchStudentsUI adminSearchStudentsUI = new AdminSearchStudentsUI();
                    Frames.getJFrame(Frame.Big).changePanel(adminSearchStudentsUI, adminSearchStudentsUI.getGoBackButton());
                }).build();

        logoutButton = new ButtonBuilder("Logout")
                .withBounds(125, 420, 150, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(new Font("SansSerif",0,20))
                .withActionListener(e -> {
                    LoginUI loginUI = new LoginUI();
                    ActiveUser.setUser(null);
                    Frames.getJFrame(Frame.Small).changePanel(loginUI);
                }).build();

        //adding components to contentPane panel
        this.add(searchEventsButton);
        this.add(viewBookingsButton);
        this.add(createEventButton);
        this.add(viewOwnEventsButton);
        this.add(searchStudentsButton);
        this.add(logoutButton);
    }
}