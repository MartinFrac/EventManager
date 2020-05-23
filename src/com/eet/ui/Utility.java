package com.eet.ui;

import com.eet.models.Filters;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class Utility {

    public static final String TITLE = "Name of the event";
    public static final String PLACE = "Place";
    public static final String AVAILABLE_SPACES = "Available spaces";
    public static final String KEYWORDS = "Keywords";

    public static JTextField textFieldGenerator(int x, int y, int width, int height, String text) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(Color.white);
        textField.setForeground(Color.black);
        textField.setEnabled(true);
        textField.setFont(new Font("sansserif",0,12));
        textField.setText(text);
        textField.setVisible(true);
        return textField;
    }

    public static JDatePickerImpl datePickerGenerator(int x, int y, int width, int height) {
        UtilDateModel utilDateModel = new UtilDateModel();
        JDatePanelImpl jDatePanel = new JDatePanelImpl(utilDateModel, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
        datePicker.setBounds(x, y, width, height);
        return datePicker;
    }

    public static void updateData(Object[][] data, DefaultTableModel model) {
        model.getDataVector().removeAllElements();
        if(data!=null) {
            for(int i = 0; i < data.length; i++) {
                model.addRow(data[i]);
            }
        }
        model.fireTableDataChanged();
    }

    public static Timestamp dateToTimestamp(Date date) {
        Timestamp timestamp = null;
        if (date!=null) {
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(date);
            calendarStart.set(Calendar.HOUR_OF_DAY, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);
            date = calendarStart.getTime();
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;
    }

    public static Filters validateFilters(HashMap<String, String> map, Date startDate, Date endDate) {

        Filters filters = new Filters();

        String title = map.get("title");
        String type = map.get("type");
        String place = map.get("place");
        String keywords = map.get("keywords");
        String availableSpaces = map.get("availableSpaces");

        int availableSpacesInteger = -1;
        Timestamp startTimestamp = null;
        Timestamp endTimestamp = null;
        int intType = -1;

        for (String key: map.keySet()) {
            if (map.get(key).length()>=255) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "length of the value in: " + key + " exceeds 254 characters",
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        if (TITLE.equals(title)) {
            title = "";
        }
        if (PLACE.equals(place)) {
            place = "";
        }
        if (AVAILABLE_SPACES.equals(availableSpaces)) {
            availableSpaces = "-1";
        }
        if (KEYWORDS.equals(keywords)) {
            keywords = "";
        } else {
            String[] array = keywords.split(" ");
            if (array.length>1) {
                keywords = String.join("* OR ", array);
            } else {
                keywords = array[0] + "*";
            }
        }

        startTimestamp = Utility.dateToTimestamp(startDate);
        endTimestamp = Utility.dateToTimestamp(endDate);

        if ((startTimestamp == null && endTimestamp != null)
                || (startTimestamp != null && endTimestamp ==null)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Both dates need to be filled up",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (!"None".equals(type)) {
            intType = type.equals("Online") ? 1 : 2;
        }
        try {
            availableSpacesInteger = Integer.parseInt(availableSpaces);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in the Available spaces field is not an Integer",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (availableSpacesInteger<-1) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in the Available spaces field is negative",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        filters.setTitle(title);
        filters.setType(intType);
        filters.setPlace(place);
        filters.setKeywords(keywords);
        filters.setAvailableSpaces(availableSpacesInteger);
        filters.setStartDate(startTimestamp);
        filters.setEndDate(endTimestamp);

        return filters;
    }
}