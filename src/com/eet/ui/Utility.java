package com.eet.ui;

import com.eet.controllers.EventController;
import com.eet.controllers.UserController;
import com.eet.mappers.UserMapper;
import com.eet.models.Event;
import com.eet.models.Filters;
import com.eet.models.Role;
import com.eet.models.User;
import com.eet.ui.views.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class Utility {

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

    public static JDatePickerImpl datePickerGenerator(int x, int y, int width, int height, LocalDate localDate) {
        UtilDateModel utilDateModel = new UtilDateModel();
        utilDateModel.setDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        utilDateModel.setSelected(true);
        JDatePanelImpl jDatePanel = new JDatePanelImpl(utilDateModel, new Properties());
        JDatePickerImpl datePicker = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
        datePicker.setBounds(x, y, width, height);
        return datePicker;
    }

    public static void updateData(Object[][] data, int size, int noOfButtons, DefaultTableModel model) {
        model.getDataVector().removeAllElements();
        if(data!=null) {
            Object[][] formattedData = formatData(data, size, noOfButtons);
            for(int i = 0; i < data.length; i++) {
                model.addRow(formattedData[i]);
            }
        }
        model.fireTableDataChanged();
    }

    public static Object[][] formatData(Object[][] data, int size, int noOfButtons) {
        Object[][] formattedData = new Object[data.length][size];
        for (int j = 0; j < data.length; j++) {
            for (int i = 0; i < size; i++) {
                if (i < (size-noOfButtons-1)) {
                    formattedData[j][i] = data[j][i];
                } else if (i == size-1) {
                    formattedData[j][i] = data[j][i-noOfButtons];
                }
                else {
                    formattedData[j][i] = "";
                }
            }
        }
        return formattedData;
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

    public static boolean validateRegister(Map<String, String> map, UserController userController) {
        if (RegisterUI.NAME.equals(map.get("name"))) {
            map.put("name", "");
        }
        if (RegisterUI.SURNAME.equals(map.get("surname"))) {
            map.put("surname", "");
        }
        if (RegisterUI.ID.equals(map.get("id"))) {
            map.put("id", "");
        }
        String password1 = new String(RegisterUI.PASSWORD_ARRAY_1);
        if (password1.equals(map.get("password1"))) {
            map.put("password1", "");
        }
        String password2 = new String(RegisterUI.PASSWORD_ARRAY_2);
        if (password2.equals(map.get("password2"))) {
            map.put("password2", "");
        }
        for (String key: map.keySet()) {
            if ((map.get(key).length()>=50) || (map.get(key).length()==0)) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "length of the value in: " + key + " should be between 0 and 50 characters",
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (map.get("password1").length()<8) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Your password should be at least 8 characters long",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!map.get("password1").equals(map.get("password2"))) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Passwords are not matching",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (userController.checkIfUserExists(map.get("id"))) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Student with this id already exists",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static User validateUserFilters(HashMap<String, String> map) {
        String id = map.get("id");
        String name = map.get("name");
        String surname = map.get("surname");
        String role = map.get("role");

        if (AdminSearchStudentsUI.ID.equals(id)) {
            map.put("id", "");
        }
        if (AdminSearchStudentsUI.NAME.equals(name)) {
            map.put("name", "");
        }
        if (AdminSearchStudentsUI.SURNAME.equals(surname)) {
            map.put("surname", "");
        }
        if (role.equals(Role.USER.toString())) {
            map.put("role", "3");
        } else{
            map.put("role", "2");
        }
        User user = UserMapper.fromUI(map);
        return user;
    }

    public static Filters validateEventFilters(HashMap<String, String> map, Date startDate, Date endDate) {

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
        if (Filters.TITLE.equals(title)) {
            title = "";
        }
        if (Filters.PLACE.equals(place)) {
            place = "";
        }
        if (Filters.AVAILABLE_SPACES.equals(availableSpaces)) {
            availableSpaces = "-1";
        }
        if (Filters.KEYWORDS.equals(keywords)) {
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

    public static HashMap<String, Object> validateEventData(HashMap<String, Object> map, EventController eventController) {
        String name = (String) map.get("name");
        String type = (String) map.get("type");
        String place = (String) map.get("place");
        String spaceLimit = (String) map.get("spaceLimit");
        Date startDate = (Date) map.get("startDate");
        Date endDate = (Date) map.get("endDate");

        String startHour = (String) map.get("startHour");
        String startMinute = (String) map.get("startMinute");

        Boolean isRepeatable = (Boolean) map.get("isRepeatable");
        String weekDay = (String) map.get("weekDay");
        String monthDay = (String) map.get("monthDay");
        String description = (String) map.get("description");
        int spacelimitInt = 0;

        String time = startHour + ":" + startMinute;
        map.put("time", time);

        if (CreateEventUI.NAME.equals(name)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Name cannot be empty",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (map.get("id") == null) {
            if (eventController.checkIfExistsByName(name)) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Event with that name already exists",
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        if (CreateEventUI.PLACE.equals(place)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in place cannot be empty",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (CreateEventUI.SPACE_LIMIT.equals(spaceLimit)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Space Limit cannot be empty",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            spacelimitInt = Integer.parseInt(spaceLimit);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Space Limit field is not an Integer",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (spacelimitInt<-1) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Space Limit field is negative",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (spacelimitInt>30) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Space Limit cannot exceed 30",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (map.get("id") != null) {
            Event event = eventController.getEvent((int) map.get("id"));
            if (spacelimitInt < (event.getSpaceLimitations() - event.getAvailableSpaces())) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Value in Space Limit field is lesser than amount of spaces already booked",
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }
        }
        map.put("spaceLimit", spacelimitInt);
        if (startDate==null) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in Start Date is not selected",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (endDate==null) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Value in End Date is not selected",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (isRepeatable && (weekDay.equals("-") && monthDay.equals("-"))) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Repetition needs to be specified",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (weekDay.equals("-")) {
            map.put("weekDay", null);
        }
        if (monthDay.equals("-")) {
            map.put("monthDay", null);
        } else {
            map.put("monthDay", monthDay.substring(0,1));
        }
        if (CreateEventUI.DESCRIPTION.equals(description)) {
            map.put("description", "");
        }

        return map;
    }

    public static User validateAuthorisation(String id, char[] password, UserController userController) {
        if (LoginUI.STUDENT_ID.equals(id)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Student ID cannot be empty",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (Arrays.equals(LoginUI.PASSWORD_ARRAY, password)) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Password cannot be empty",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        User user = userController.authenticate(id, password);
        if (user == null) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Wrong credentials",
                    "Inane warning",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return user;
    }

    public static void changeToUsersView(Role role) {
        JPanel jPanel = null;
        switch (role.getLevel()) {
            case 1:
                jPanel = new AdminUI();
                break;
            case 2:
                jPanel = new EventOrganiserUI();
                break;
            case 3:
                jPanel = new StudentUI();
                break;
        }
        Frames.getJFrame(Frame.Small).changePanel(jPanel);
    }

    public static FocusListener getPlaceholder(String placeholder) {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                placeholderDisappear(textField, placeholder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                placeholderAppear(textField, placeholder);
            }
        };
    }

    public static void placeholderDisappear(JTextField textField, String placeHolder) {
        if (textField.getText().equals(placeHolder)) {
            textField.setText("");
        }
    }

    public static void placeholderAppear(JTextField textField, String placeHolder) {
        if (textField.getText().equals("")) {
            textField.setText(placeHolder);
        }
    }
}