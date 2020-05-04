package com.eet.ui.views;

/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import com.eet.ui.BigFrame;
import com.eet.ui.SmallFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EventOrganiserUI extends JPanel {

    private JButton searchEvents;
    private JButton viewBookings;
    private JButton createEvent;
    private JButton viewEvents;
    private JButton logout;

    public JButton getlogout() {
        return logout;
    }
    //Constructor
    public EventOrganiserUI(){

        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(139,217,169));;
        //menu generate method

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(400,500));
        contentPane.setBackground(new Color(0,255,255));


        searchEvents = new JButton();
        searchEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchEventsUI searchEventsUI = new SearchEventsUI();
                new BigFrame(searchEventsUI, searchEventsUI.getGoBack());
            }
        });
        searchEvents.setBounds(45,61,120,35);
        searchEvents.setBackground(new Color(214,217,223));
        searchEvents.setForeground(new Color(0,0,0));
        searchEvents.setEnabled(true);
        searchEvents.setFont(new Font("sansserif",0,12));
        searchEvents.setText("Search Events");
        searchEvents.setVisible(true);

        viewBookings = new JButton();
        viewBookings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBookingsUI viewBookingsUI = new ViewBookingsUI();
                new BigFrame(viewBookingsUI, viewBookingsUI.getGoBack());
            }
        });
        viewBookings.setBounds(45,126,120,35);
        viewBookings.setBackground(new Color(214,217,223));
        viewBookings.setForeground(new Color(0,0,0));
        viewBookings.setEnabled(true);
        viewBookings.setFont(new Font("sansserif",0,12));
        viewBookings.setText("View Bookings");
        viewBookings.setVisible(true);

        createEvent = new JButton();
        createEvent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateEventUI createEventUI = new CreateEventUI();
                new BigFrame(createEventUI, createEventUI.getGoBack());
            }
        });
        createEvent.setBounds(235,61,120,35);
        createEvent.setBackground(new Color(214,217,223));
        createEvent.setForeground(new Color(0,0,0));
        createEvent.setEnabled(true);
        createEvent.setFont(new Font("sansserif",0,12));
        createEvent.setText("Create Event");
        createEvent.setVisible(true);

        viewEvents = new JButton();
        viewEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewEventsUI viewEventsUI = new ViewEventsUI();
                new BigFrame(viewEventsUI, viewEventsUI.getGoBack());
            }
        });
        viewEvents.setBounds(235,126,120,35);
        viewEvents.setBackground(new Color(214,217,223));
        viewEvents.setForeground(new Color(0,0,0));
        viewEvents.setEnabled(true);
        viewEvents.setFont(new Font("sansserif",0,12));
        viewEvents.setText("View Events");
        viewEvents.setVisible(true);

        logout = new JButton();
        logout.setBounds(160,300,90,35);
        logout.setBackground(new Color(214,217,223));
        logout.setForeground(new Color(0,0,0));
        logout.setEnabled(true);
        logout.setFont(new Font("sansserif",0,12));
        logout.setText("Logout");
        logout.setVisible(true);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SmallFrame.getjFrame().changePanel(new LoginUI());
            }
        });

        //adding components to contentPane panel
        contentPane.add(searchEvents);
        contentPane.add(viewBookings);
        contentPane.add(createEvent);
        contentPane.add(viewEvents);
        contentPane.add(logout);

        //adding panel to JFrame and seting of window position and close operation
        this.add(viewEvents);
        this.add(searchEvents);
        this.add(viewBookings);
        this.add(createEvent);
        this.add(logout);
    }


    public static void main(String[] args){
        new EventOrganiserUI();
    }

}


