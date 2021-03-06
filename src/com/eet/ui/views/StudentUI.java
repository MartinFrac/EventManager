package com.eet.ui.views;

import com.eet.memory.ActiveUser;
import com.eet.ui.BigFrame;
import com.eet.ui.SmallFrame;

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


        searchEventsButton = new JButton();
        searchEventsButton.setBounds(100,90,200,35);
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
        viewBookingsButton.setBounds(100,170,200,35);
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

        logoutButton = new JButton();
        logoutButton.setBounds(125,330,150,35);
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
        this.add(logoutButton);
    }
}
