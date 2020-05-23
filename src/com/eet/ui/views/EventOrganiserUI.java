package com.eet.ui.views;

import com.eet.memory.ActiveUser;
import com.eet.ui.BigFrame;
import com.eet.ui.SmallFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventOrganiserUI extends JPanel {

    private JButton searchEventsButton;
    private JButton viewBookingsButton;
    private JButton createEventButton;
    private JButton viewOwnEventsButton;
    private JButton logoutButton;

    //Constructor
    public EventOrganiserUI(){


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
                SearchEventsUI searchEventsUI = new SearchEventsUI();
                SmallFrame.getjFrame().dispose();
                new BigFrame(searchEventsUI, searchEventsUI.getGoBackButton());
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
                SmallFrame.getjFrame().dispose();
                new BigFrame(viewBookingsUI, viewBookingsUI.getGoBackButton());
            }
        });

        createEventButton = new JButton();
        createEventButton.setBounds(100,210,200,35);
        createEventButton.setBackground(new Color(214,72,105));
        createEventButton.setForeground(new Color(51,255,255));
        createEventButton.setEnabled(true);
        createEventButton.setFont(new Font("SansSerif",0,20));
        createEventButton.setText("Create Event");
        createEventButton.setVisible(true);

        viewOwnEventsButton = new JButton();
        viewOwnEventsButton.setBounds(100,280,200,35);
        viewOwnEventsButton.setBackground(new Color(214,72,105));
        viewOwnEventsButton.setForeground(new Color(51,255,255));
        viewOwnEventsButton.setEnabled(true);
        viewOwnEventsButton.setFont(new Font("SansSerif",0,20));
        viewOwnEventsButton.setText("View Own Events");
        viewOwnEventsButton.setVisible(true);

        logoutButton = new JButton();
        logoutButton.setBounds(125,400,150,35);
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
                SmallFrame.getjFrame().changePanel(loginUI);
                ActiveUser.setUser(null);
            }
        });

        //adding components to contentPane panel
        this.add(searchEventsButton);
        this.add(viewBookingsButton);
        this.add(createEventButton);
        this.add(viewOwnEventsButton);
        this.add(logoutButton);
    }
}
