package com.eet.ui.views;

import com.eet.memory.ActiveUser;
import com.eet.ui.BigFrame;
import com.eet.ui.DesignatedFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        searchEventsButton = new JButton();
        searchEventsButton.setBounds(100,40,200,35);
        searchEventsButton.setBackground(new Color(214,72,105));
        searchEventsButton.setForeground(new Color(51,255,255));
        searchEventsButton.setEnabled(true);
        searchEventsButton.setFont(new Font("SansSerif",0,20));
        searchEventsButton.setText("Search Events");
        searchEventsButton.setVisible(true);
        searchEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminSearchEvents adminSearchEvents = new AdminSearchEvents();
//                DesignatedFrame.getJFrame().dispose();
                new BigFrame(adminSearchEvents, adminSearchEvents.getGoBackButton());
            }
        });

        viewBookingsButton = new JButton();
        viewBookingsButton.setBounds(100,110,200,35);
        viewBookingsButton.setBackground(new Color(214,72,105));
        viewBookingsButton.setForeground(new Color(51,255,255));
        viewBookingsButton.setEnabled(true);
        viewBookingsButton.setFont(new Font("SansSerif",0,20));
        viewBookingsButton.setText("View Bookings");
        viewBookingsButton.setVisible(true);
        viewBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBookingsUI viewBookingsUI = new ViewBookingsUI();
//                DesignatedFrame.getJFrame().dispose();
                new BigFrame(viewBookingsUI, viewBookingsUI.getGoBackButton());
            }
        });

        createEventButton = new JButton();
        createEventButton.setBounds(100,180,200,35);
        createEventButton.setBackground(new Color(214,72,105));
        createEventButton.setForeground(new Color(51,255,255));
        createEventButton.setEnabled(true);
        createEventButton.setFont(new Font("SansSerif",0,20));
        createEventButton.setText("Create Event");
        createEventButton.setVisible(true);
        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateEventUI createEventUI = new CreateEventUI();
//                DesignatedFrame.getJFrame().changePanel(createEventUI);
            }
        });

        viewOwnEventsButton = new JButton();
        viewOwnEventsButton.setBounds(100,250,200,35);
        viewOwnEventsButton.setBackground(new Color(214,72,105));
        viewOwnEventsButton.setForeground(new Color(51,255,255));
        viewOwnEventsButton.setEnabled(true);
        viewOwnEventsButton.setFont(new Font("SansSerif",0,20));
        viewOwnEventsButton.setText("View Own Events");
        viewOwnEventsButton.setVisible(true);
        viewOwnEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewOwnEvents viewOwnEvents = new ViewOwnEvents();
//                DesignatedFrame.getJFrame().dispose();
                new BigFrame(viewOwnEvents, viewOwnEvents.getGoBackButton());
            }
        });

        searchStudentsButton = new JButton();
        searchStudentsButton.setBounds(100,320,200,35);
        searchStudentsButton.setBackground(new Color(214,72,105));
        searchStudentsButton.setForeground(new Color(51,255,255));
        searchStudentsButton.setEnabled(true);
        searchStudentsButton.setFont(new Font("SansSerif",0,20));
        searchStudentsButton.setText("Search Students");
        searchStudentsButton.setVisible(true);
        searchStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminSearchStudentsUI adminSearchStudentsUI = new AdminSearchStudentsUI();
//                DesignatedFrame.getJFrame().dispose();
                new BigFrame(adminSearchStudentsUI, adminSearchStudentsUI.getGoBackButton());
            }
        });

        logoutButton = new JButton();
        logoutButton.setBounds(125,420,150,35);
        logoutButton.setBackground(new Color(214,72,105));
        logoutButton.setForeground(new Color(51,255,255));
        logoutButton.setEnabled(true);
        logoutButton.setFont(new Font("SansSerif",0,20));
        logoutButton.setText("Logout");
        logoutButton.setVisible(true);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginUI loginUI = new LoginUI();
//                DesignatedFrame.getJFrame().changePanel(loginUI);
                ActiveUser.setUser(null);
            }
        });

        //adding components to contentPane panel
        this.add(searchEventsButton);
        this.add(viewBookingsButton);
        this.add(createEventButton);
        this.add(viewOwnEventsButton);
        this.add(searchStudentsButton);
        this.add(logoutButton);
    }
}