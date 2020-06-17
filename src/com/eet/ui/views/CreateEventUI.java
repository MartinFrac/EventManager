package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.memory.ActiveUser;
import com.eet.ui.DesignatedFrame;
import com.eet.ui.Utility;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import java.util.HashMap;

public class CreateEventUI extends JPanel {

    private JButton createButton;
    private JButton cancelButton;
    private JRadioButton onlineRadioButton;
    private JRadioButton physicalRadioButton;
    private JTextArea descriptionTextArea;
    private JDatePickerImpl startDate;
    private JDatePickerImpl endDate;
    private JTextField spaceLimitTextField;
    private JRadioButton repeatableRadioButton;
    private JRadioButton nonRepeatableRadioButton;
    private JTextField nameTextField;
    private JTextField placeTextField;
    private JComboBox<String> startHours;
    private JComboBox<String> startMinutes;
    private JComboBox<String> endHours;
    private JComboBox<String> endMinutes;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel colon1;
    private JLabel colon2;
    private JComboBox<String> weekDay;
    private JComboBox<String> monthDay;
    private JLabel everyLabel;
    private JLabel orLabel;
    private JLabel ofAMonthLabel;

    private EventController eventController;

    public static final String NAME = "Name";
    public static final String PLACE = "Place";
    public static final String SPACE_LIMIT = "Space Limit";
    public static final String DESCRIPTION = "Description...";

    //Constructor
    public CreateEventUI(){

        eventController = new EventController();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(192,192,192));

        nameTextField = new JTextField();
        nameTextField.setBounds(115,20,170,35);
        nameTextField.setBackground(new Color(255,255,255));
        nameTextField.setForeground(new Color(0,0,0));
        nameTextField.setEnabled(true);
        nameTextField.setFont(new Font("sansserif",0,12));
        nameTextField.setText(NAME);
        nameTextField.setVisible(true);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, NAME);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, NAME);
            }
        });

        onlineRadioButton = new JRadioButton();
        onlineRadioButton.setBounds(80,70,90,35);
        onlineRadioButton.setBackground(new Color(214,217,223));
        onlineRadioButton.setForeground(new Color(0,0,0));
        onlineRadioButton.setEnabled(true);
        onlineRadioButton.setFont(new Font("sansserif",0,12));
        onlineRadioButton.setText("Online");
        onlineRadioButton.setVisible(true);
        onlineRadioButton.setSelected(true);
        onlineRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicalRadioButton.setSelected(false);
            }
        });

        physicalRadioButton = new JRadioButton();
        physicalRadioButton.setBounds(230,70,90,35);
        physicalRadioButton.setBackground(new Color(214,217,223));
        physicalRadioButton.setForeground(new Color(0,0,0));
        physicalRadioButton.setEnabled(true);
        physicalRadioButton.setFont(new Font("sansserif",0,12));
        physicalRadioButton.setText("Physical");
        physicalRadioButton.setVisible(true);
        physicalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onlineRadioButton.setSelected(false);
            }
        });

        placeTextField = new JTextField();
        placeTextField.setBounds(40,120,200,35);
        placeTextField.setBackground(new Color(255,255,255));
        placeTextField.setForeground(new Color(0,0,0));
        placeTextField.setEnabled(true);
        placeTextField.setFont(new Font("sansserif",0,12));
        placeTextField.setText(PLACE);
        placeTextField.setVisible(true);
        placeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, PLACE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, PLACE);
            }
        });

        spaceLimitTextField = new JTextField();
        spaceLimitTextField.setBounds(280,120,80,35);
        spaceLimitTextField.setBackground(new Color(255,255,255));
        spaceLimitTextField.setForeground(new Color(0,0,0));
        spaceLimitTextField.setEnabled(true);
        spaceLimitTextField.setFont(new Font("sansserif",0,12));
        spaceLimitTextField.setText(SPACE_LIMIT);
        spaceLimitTextField.setVisible(true);
        spaceLimitTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, SPACE_LIMIT);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, SPACE_LIMIT);
            }
        });

        startDateLabel = new JLabel();
        startDateLabel.setBounds(40,160,90,25);
        startDateLabel.setBackground(new Color(214,217,223));
        startDateLabel.setForeground(new Color(0,0,0));
        startDateLabel.setEnabled(true);
        startDateLabel.setFont(new Font("sansserif",0,12));
        startDateLabel.setText("Start Date");
        startDateLabel.setVisible(true);

        endDateLabel = new JLabel();
        endDateLabel.setBounds(210,160,90,25);
        endDateLabel.setBackground(new Color(214,217,223));
        endDateLabel.setForeground(new Color(0,0,0));
        endDateLabel.setEnabled(true);
        endDateLabel.setFont(new Font("sansserif",0,12));
        endDateLabel.setText("End Date");
        endDateLabel.setVisible(true);

        LocalDate localDate = LocalDate.now();

        startDate = Utility.datePickerGenerator(40,185, 150, 25, localDate);

        endDate = Utility.datePickerGenerator(210, 185, 150, 25, localDate);

        String[] hours = new String[24];
        for (int i = 0; i < hours.length; i++) {
            hours[i] = String.valueOf(i);
            if (hours[i].length()==1) {
                hours[i] = "0" + hours[i];
            }
        }

        String[] minutes = new String[60];
        for (int i = 0; i < minutes.length; i++) {
            minutes[i] = String.valueOf(i);
            if (minutes[i].length()==1) {
                minutes[i] = "0" + minutes[i];
            }
        }

        startHours = new JComboBox<>(hours);
        startHours.setBounds(40, 220, 50, 25);
        startHours.setBackground(new Color(255,255,255));
        startHours.setForeground(new Color(0,0,0));
        startHours.setEnabled(true);
        startHours.setFont(new Font("sansserif", 0, 12));
        startHours.setVisible(true);

        colon1 = new JLabel();
        colon1.setBounds(97,220,90,25);
        colon1.setBackground(new Color(214,217,223));
        colon1.setForeground(new Color(0,0,0));
        colon1.setEnabled(true);
        colon1.setFont(new Font("sansserif", Font.BOLD,16));
        colon1.setText(":");
        colon1.setVisible(true);

        startMinutes = new JComboBox<>(minutes);
        startMinutes.setBounds(110, 220, 50, 25);
        startMinutes.setBackground(new Color(255,255,255));
        startMinutes.setForeground(new Color(0,0,0));
        startMinutes.setEnabled(true);
        startMinutes.setFont(new Font("sansserif", 0, 12));
        startMinutes.setVisible(true);

        endHours = new JComboBox<>(hours);
        endHours.setBounds(210, 220, 50, 25);
        endHours.setBackground(new Color(255,255,255));
        endHours.setForeground(new Color(0,0,0));
        endHours.setEnabled(true);
        endHours.setFont(new Font("sansserif", 0, 12));
        endHours.setVisible(true);

        colon2 = new JLabel();
        colon2.setBounds(267,220,90,25);
        colon2.setBackground(new Color(214,217,223));
        colon2.setForeground(new Color(0,0,0));
        colon2.setEnabled(true);
        colon2.setFont(new Font("sansserif", Font.BOLD,16));
        colon2.setText(":");
        colon2.setVisible(true);

        endMinutes = new JComboBox<>(minutes);
        endMinutes.setBounds(280, 220, 50, 25);
        endMinutes.setBackground(new Color(255,255,255));
        endMinutes.setForeground(new Color(0,0,0));
        endMinutes.setEnabled(true);
        endMinutes.setFont(new Font("sansserif", 0, 12));
        endMinutes.setVisible(true);

        nonRepeatableRadioButton = new JRadioButton();
        nonRepeatableRadioButton.setBounds(80,260,130,35);
        nonRepeatableRadioButton.setBackground(new Color(214,217,223));
        nonRepeatableRadioButton.setForeground(new Color(0,0,0));
        nonRepeatableRadioButton.setEnabled(true);
        nonRepeatableRadioButton.setFont(new Font("sansserif",0,12));
        nonRepeatableRadioButton.setText("Non Repeatable");
        nonRepeatableRadioButton.setVisible(true);
        nonRepeatableRadioButton.setSelected(true);
        nonRepeatableRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repeatableRadioButton.setSelected(false);
                weekDay.setEnabled(false);
                monthDay.setEnabled(false);
            }
        });

        repeatableRadioButton = new JRadioButton();
        repeatableRadioButton.setBounds(230,260,90,35);
        repeatableRadioButton.setBackground(new Color(214,217,223));
        repeatableRadioButton.setForeground(new Color(0,0,0));
        repeatableRadioButton.setEnabled(true);
        repeatableRadioButton.setFont(new Font("sansserif",0,12));
        repeatableRadioButton.setText("Repeatable");
        repeatableRadioButton.setVisible(true);
        repeatableRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nonRepeatableRadioButton.setSelected(false);
                weekDay.setEnabled(true);
                monthDay.setEnabled(true);
            }
        });

        everyLabel = new JLabel();
        everyLabel.setBounds(40,305,90,25);
        everyLabel.setBackground(new Color(214,217,223));
        everyLabel.setForeground(new Color(0,0,0));
        everyLabel.setEnabled(true);
        everyLabel.setFont(new Font("sansserif",0,12));
        everyLabel.setText("Every");
        everyLabel.setVisible(true);

        String[] dayOfTheWeek = {"-", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        weekDay = new JComboBox<>(dayOfTheWeek);
        weekDay.setBounds(90,305,100,25);
        weekDay.setBackground(new Color(255,255,255));
        weekDay.setForeground(new Color(0,0,0));
        weekDay.setEnabled(false);
        weekDay.setFont(new Font("sansserif",0,12));
        weekDay.setVisible(true);
        weekDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weekDay.getSelectedIndex()!=0) {
                    monthDay.setSelectedIndex(0);
                }
            }
        });

        orLabel = new JLabel();
        orLabel.setBounds(205,305,40,25);
        orLabel.setBackground(new Color(214,217,223));
        orLabel.setForeground(new Color(0,0,0));
        orLabel.setEnabled(true);
        orLabel.setFont(new Font("sansserif",0,12));
        orLabel.setText("Or");
        orLabel.setVisible(true);

        String[] dayOfTheMonth = new String[29];
        dayOfTheMonth[0] = "-";
        for (int i = 1; i < dayOfTheMonth.length; i++) {
            dayOfTheMonth[i] = String.valueOf(i);
            switch (dayOfTheMonth[i]) {
                case "1": dayOfTheMonth[i] += "st";
                    break;
                case "2": dayOfTheMonth[i] += "nd";
                    break;
                case "3": dayOfTheMonth[i] += "rd";
                    break;
                default: dayOfTheMonth[i] += "th";
            }
        }

        monthDay = new JComboBox<>(dayOfTheMonth);
        monthDay.setBounds(240,305,50,25);
        monthDay.setBackground(new Color(255,255,255));
        monthDay.setForeground(new Color(0,0,0));
        monthDay.setEnabled(true);
        monthDay.setFont(new Font("sansserif",0,12));
        monthDay.setVisible(true);
        monthDay.setEnabled(false);
        monthDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monthDay.getSelectedIndex()!=0) {
                    weekDay.setSelectedIndex(0);
                }
            }
        });

        ofAMonthLabel = new JLabel();
        ofAMonthLabel.setBounds(290,305,80,25);
        ofAMonthLabel.setBackground(new Color(214,217,223));
        ofAMonthLabel.setForeground(new Color(0,0,0));
        ofAMonthLabel.setEnabled(true);
        ofAMonthLabel.setFont(new Font("sansserif",0,12));
        ofAMonthLabel.setText("day of a month");
        ofAMonthLabel.setVisible(true);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setBounds(40,345,320,80);
        descriptionTextArea.setBackground(new Color(255,255,255));
        descriptionTextArea.setForeground(new Color(0,0,0));
        descriptionTextArea.setEnabled(true);
        descriptionTextArea.setFont(new Font("sansserif",0,12));
        descriptionTextArea.setText(DESCRIPTION);
        descriptionTextArea.setBorder(BorderFactory.createBevelBorder(1));
        descriptionTextArea.setVisible(true);
        descriptionTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextArea textArea = (JTextArea) e.getSource();
                if (textArea.getText().equals(textArea.getText())) {
                    textArea.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextArea textArea = (JTextArea) e.getSource();
                if (textArea.getText().equals("")) {
                    textArea.setText(DESCRIPTION);
                }
            }
        });

        createButton = new JButton();
        createButton.setBounds(100,440,90,35);
        createButton.setBackground(new Color(214,217,223));
        createButton.setForeground(new Color(0,0,0));
        createButton.setEnabled(true);
        createButton.setFont(new Font("sansserif",0,12));
        createButton.setText("Create");
        createButton.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", nameTextField.getText());
                map.put("type", onlineRadioButton.isSelected() ? "online" : "physical");
                map.put("place", placeTextField.getText());
                map.put("spaceLimit", spaceLimitTextField.getText());
                map.put("startDate", startDate.getModel().getValue());
                map.put("endDate", endDate.getModel().getValue());
                map.put("startHour", startHours.getSelectedItem());
                map.put("endHour", endHours.getSelectedItem());
                map.put("startMinute", startMinutes.getSelectedItem());
                map.put("endMinute", endMinutes.getSelectedItem());
                map.put("isRepeatable", repeatableRadioButton.isSelected());
                map.put("weekDay", weekDay.getSelectedItem());
                map.put("monthDay", monthDay.getSelectedItem());
                map.put("description", descriptionTextArea.getText());
                map = Utility.validateEventData(map, eventController);
                if (map != null) {
                    eventController.create(map, ActiveUser.getUser().getId());
                    JPanel jPanel = null;
                    switch (ActiveUser.getUser().getRole().getLevel()) {
                        case 1: jPanel = new AdminUI();
                            break;
                        case 2: jPanel = new EventOrganiserUI();
                            break;
                        case 3: jPanel = new StudentUI();
                            break;
                    }
//                    DesignatedFrame.getJFrame().changePanel(jPanel);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Event created"
                    );
                }
            }
        });

        cancelButton = new JButton();
        cancelButton.setBounds(210,440,90,35);
        cancelButton.setBackground(new Color(214,217,223));
        cancelButton.setForeground(new Color(0,0,0));
        cancelButton.setEnabled(true);
        cancelButton.setFont(new Font("sansserif",0,12));
        cancelButton.setText("Cancel");
        cancelButton.setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel jPanel = null;
                switch (ActiveUser.getUser().getRole().getLevel()) {
                    case 1: jPanel = new AdminUI();
                        break;
                    case 2: jPanel = new EventOrganiserUI();
                        break;
                    case 3: jPanel = new StudentUI();
                        break;
                }
//                DesignatedFrame.getJFrame().changePanel(jPanel);
            }
        });

        //adding components to contentPane panel
        this.add(createButton);
        this.add(cancelButton);
        this.add(onlineRadioButton);
        this.add(physicalRadioButton);
        this.add(descriptionTextArea);
        this.add(nameTextField);
        this.add(spaceLimitTextField);
        this.add(repeatableRadioButton);
        this.add(nonRepeatableRadioButton);
        this.add(startDateLabel);
        this.add(endDateLabel);
        this.add(startDate);
        this.add(startHours);
        this.add(colon1);
        this.add(startMinutes);
        this.add(endDate);
        this.add(endHours);
        this.add(colon2);
        this.add(endMinutes);
        this.add(placeTextField);
        this.add(weekDay);
        this.add(monthDay);
        this.add(everyLabel);
        this.add(orLabel);
        this.add(ofAMonthLabel);
    }

    private void placeholderDisappear(AWTEvent event, String placeHolder) {
        JTextField textField = (JTextField) event.getSource();
        if (textField.getText().equals(placeHolder)) {
            textField.setText("");
        }
    }

    private void placeholderAppear(AWTEvent event, String placeHolder) {
        JTextField textField = (JTextField) event.getSource();
        if (textField.getText().equals("")) {
            textField.setText(placeHolder);
        }
    }
}
