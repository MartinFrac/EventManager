package com.eet.ui.views;


import com.eet.controllers.EventController;
import com.eet.memory.ActiveUser;
import com.eet.ui.ButtonEditor;
import com.eet.ui.ButtonRenderer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewEventsUI extends JPanel {

    private JButton goBack;
    private JButton bookEvent;
    private JLabel searchEvent;
    private JTextField searchTitle;
    private JTextField searchByKeyword;
    private JTextField searchByLocation;
    private JTextField searchByDate;
    private JTextField searchBySpaceLimit;
    private EventController eventController;
    public String title, keyword, location, date, spaceLimit, model;


    public JButton getBookEvent() {
        return bookEvent;
    }

    //Constructor
    public ViewEventsUI(){

        eventController = new EventController();


        //pane with null layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(900,600));
        this.setBackground(new Color(192,192,192));

        goBack = new JButton();
        goBack.setBounds(371,450,90,35);
        goBack.setBackground(new Color(214,217,223));
        goBack.setForeground(new Color(0,0,0));
        goBack.setEnabled(true);
        goBack.setFont(new Font("sansserif",0,12));
        goBack.setText("Go back");
        goBack.setVisible(true);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//          new x();
//          dispose();
            }
        });

        bookEvent = new JButton();
        bookEvent.setBounds(371,450,90,35);
        bookEvent.setBackground(new Color(214,217,223));
        bookEvent.setForeground(new Color(0,0,0));
        bookEvent.setEnabled(true);
        bookEvent.setFont(new Font("sansserif",0,12));
        bookEvent.setText("Go back");
        bookEvent.setVisible(true);
        bookEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        searchEvent = new JLabel();
        searchEvent.setBounds(5,5,90,35);
        searchEvent.setBackground(new Color(214,217,223));
        searchEvent.setForeground(new Color(0,0,0));
        searchEvent.setEnabled(true);
        searchEvent.setFont(new Font("sansserif",0,12));
        searchEvent.setText("View events");
        searchEvent.setVisible(true);

        searchByKeyword = new JTextField();
        searchByKeyword.setBounds(12,475,150,35);
        searchByKeyword.setBackground(new Color(255,255,255));
        searchByKeyword.setForeground(new Color(0,0,0));
        searchByKeyword.setEnabled(true);
        searchByKeyword.setFont(new Font("sansserif",0,12));
        searchByKeyword.setText("Keyword");
        searchByKeyword.setVisible(true);
        searchByKeyword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchByKeyword.getText().equals("Keyword")) {
                    searchByKeyword.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchByKeyword.getText().equals("")) {
                    searchByKeyword.setText("Keyword");
                }
            }
        });

        searchByLocation = new JTextField();
        searchByLocation.setBounds(12,425,150,35);
        searchByLocation.setBackground(new Color(255,255,255));
        searchByLocation.setForeground(new Color(0,0,0));
        searchByLocation.setEnabled(true);
        searchByLocation.setFont(new Font("sansserif",0,12));
        searchByLocation.setText("Location");
        searchByLocation.setVisible(true);
        searchByLocation.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchByLocation.getText().equals("Location")) {
                    searchByLocation.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchByLocation.getText().equals("")) {
                    searchByLocation.setText("Location");
                }
            }
        });

        searchByDate = new JTextField();
        searchByDate.setBounds(180,425,150,35);
        searchByDate.setBackground(new Color(255,255,255));
        searchByDate.setForeground(new Color(0,0,0));
        searchByDate.setEnabled(true);
        searchByDate.setFont(new Font("sansserif",0,12));
        searchByDate.setText("Date");
        searchByDate.setVisible(true);
        searchByDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchByDate.getText().equals("Date")) {
                    searchByDate.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchByDate.getText().equals("")) {
                    searchByDate.setText("Date");
                }
            }
        });

        searchBySpaceLimit = new JTextField();
        searchBySpaceLimit.setBounds(180,475,150,35);
        searchBySpaceLimit.setBackground(new Color(255,255,255));
        searchBySpaceLimit.setForeground(new Color(0,0,0));
        searchBySpaceLimit.setEnabled(true);
        searchBySpaceLimit.setFont(new Font("sansserif",0,12));
        searchBySpaceLimit.setText("Space limit");
        searchBySpaceLimit.setVisible(true);
        searchBySpaceLimit.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBySpaceLimit.getText().equals("Space limit")) {
                    searchBySpaceLimit.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBySpaceLimit.getText().equals("")) {
                    searchBySpaceLimit.setText("Space limit");
                }
            }
        });


        //creating a table
        JTable table = new JTable();
        table.setRowHeight(40);
        table.setForeground(Color.black);
        Font font = new Font("Arial",1,15);
        table.setFont(font);
        DefaultTableModel model = new DefaultTableModel();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 75, 900, 300);

        String[] columnNames = {"Title","Type","Description","Date and time","Available spaces","Place","Book the event"};

        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor());
        Object[][] events = (Object[][]) eventController.cancelEvents();
        if(events!=null) {
            for(int i = 0; i < events.length; i++) {
                model.addRow(events[i]);
            }
        }

        //adding components to contentPane panel
        this.add(goBack);
        this.add(searchEvent);
        this.add(searchByKeyword);
        this.add(searchByLocation);
        this.add(searchByDate);
        this.add(searchBySpaceLimit);
        this.add(scrollPane);


    }}