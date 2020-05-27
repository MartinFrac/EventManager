package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.controllers.EventOrganisingController;
import com.eet.memory.ActiveUser;
import com.eet.models.Event;
import com.eet.models.Filters;
import com.eet.ui.BigFrame;
import com.eet.ui.RendererAndEditor;
import com.eet.ui.SmallFrame;
import com.eet.ui.Utility;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class ViewEventsUI extends JPanel {

    private JButton goBackButton;
    private JLabel searchEventLabel;
    private JTextField titleTextField;
    private JTextField keywordsTextField;
    private JTextField locationTextField;
    private JTextField availableSpacesTextField;
    private JButton searchButton;
    private JButton repeatableButton;
    private JButton filterButton;
    private JTable table;
    private DefaultTableModel model;
    private JDatePickerImpl datePickerStart;
    private JDatePickerImpl datePickerEnd;
    private JComboBox<String> eventTypesComboBox;

    private EventController eventController;
    private EventOrganisingController eventOrganisingController;

    private static final String[] columnNames = {"Title",
            "Type",
            "Description",
            "Start Date",
            "Duration",
            "Spaces",
            "Place",
            "Click to cancel",
            "id"
    };
    private static final int[] widths = {130, 60, 180, 120, 60, 70, 150, 130, 0};

    public JButton getGoBackButton() {
        return goBackButton;
    }

    //Constructor
    public ViewEventsUI(){

        eventController = new EventController();
        eventOrganisingController = new EventOrganisingController();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(900,600));
        this.setBackground(new Color(192,192,192));

        datePickerStart = Utility.datePickerGenerator(15, 510, 150, 25);

        datePickerEnd = Utility.datePickerGenerator(185, 510, 150, 25);

        String[] options = {"None", "Online", "Physical"};
        eventTypesComboBox = new JComboBox<>(options);
        eventTypesComboBox.setBounds(355, 510, 150, 25);

        filterButton = new JButton();
        filterButton.setBounds(530, 460, 140, 85);
        filterButton.setBackground(new Color(214, 217, 223));
        filterButton.setForeground(new Color(0,0,0));
        filterButton.setEnabled(true);
        filterButton.setFont(new Font("sansserif", 0, 18));
        filterButton.setText("Filter");
        filterButton.setVisible(true);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HashMap<String, String> map = new HashMap<>();
                    String title = titleTextField.getText();
                    String place = locationTextField.getText();
                    String availableSpaces = availableSpacesTextField.getText();
                    String keywords = keywordsTextField.getText();
                    Date startDate = (Date) datePickerStart.getModel().getValue();
                    Date endDate = (Date) datePickerEnd.getModel().getValue();
                    String type = (String) eventTypesComboBox.getSelectedItem();
                    map.put("title", title);
                    map.put("place", place);
                    map.put("availableSpaces", availableSpaces);
                    map.put("keywords", keywords);
                    map.put("type", type);

                    Filters filters = Utility.validateFilters(map, startDate, endDate);
                    if (filters==null) {
                        System.out.println("wrong filters");
                    } else {
                        Object[][] data;
                        if (repeatableButton.getText().equals("Repeatable Events")) {
                            data = eventController.getNonRepeatableEventsWithFiltersOrganised(ActiveUser.getUser().getId(), filters);
                        } else {
                            data = eventController.getRepeatableEventsWithFiltersOrganised(ActiveUser.getUser().getId(), filters);
                        }
                        Utility.updateData(data, columnNames.length, model);
                    }
                } catch (ClassCastException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

        goBackButton = new JButton();
        goBackButton.setBounds(720,460,140,85);
        goBackButton.setBackground(new Color(214,217,223));
        goBackButton.setForeground(new Color(0,0,0));
        goBackButton.setEnabled(true);
        goBackButton.setFont(new Font("sansserif",0,18));
        goBackButton.setText("Go back");
        goBackButton.setVisible(true);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JPanel jPanel = null;
                switch (ActiveUser.getUser().getRole()) {
                    case 1: jPanel = new AdminUI();
                        break;
                    case 2: jPanel = new EventOrganiserUI();
                        break;
                    case 3: jPanel = new StudentUI();
                        break;
                }
                BigFrame.getjFrame().dispose();
                new SmallFrame(jPanel);
            }
        });

        searchEventLabel = new JLabel();
        searchEventLabel.setBounds(5,5,90,35);
        searchEventLabel.setBackground(new Color(214,217,223));
        searchEventLabel.setForeground(new Color(0,0,0));
        searchEventLabel.setEnabled(true);
        searchEventLabel.setFont(new Font("sansserif",0,12));
        searchEventLabel.setText("Search event");
        searchEventLabel.setVisible(true);

        searchButton = new JButton();
        searchButton.setBounds(250, 40, 90, 35);
        searchButton.setBackground(new Color(214, 217, 223));
        searchButton.setForeground(new Color(0, 0, 0));
        searchButton.setEnabled(true);
        searchButton.setFont(new Font("sansserif", 0, 12));
        searchButton.setText("Search");
        searchButton.setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data;
                String userId = ActiveUser.getUser().getId();
                String name = titleTextField.getText();
                if (Filters.TITLE.equals(name)) {
                    name = "";
                }
                if (repeatableButton.getText().equals("Repeatable Events")) {
                    data = eventController.getNonRepeatableEvents(userId, name);
                } else {
                    data = eventController.getRepeatableEvents(userId, name);
                }
                Utility.updateData(data, columnNames.length, model);
            }
        });

        repeatableButton = new JButton();
        repeatableButton.setBounds(380, 40, 180, 35);
        repeatableButton.setBackground(new Color(214, 217, 223));
        repeatableButton.setForeground(new Color(0, 0, 0));
        repeatableButton.setEnabled(true);
        repeatableButton.setFont(new Font("sansserif", 0, 12));
        repeatableButton.setText("Repeatable Events");
        repeatableButton.setVisible(true);
        repeatableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(repeatableButton.getText().equals("Repeatable Events")) {
                    Object[][] data = eventController.getRepeatableEvents(ActiveUser.getUser().getId());
                    table.getColumnModel().getColumn(3).setHeaderValue("Repetition");
                    table.getTableHeader().repaint();
                    Utility.updateData(data, columnNames.length, model);
                    repeatableButton.setText("One time Events");
                    for (int i = 0; i<widths.length; i++) {
                        table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
                        table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
                    }
                }
                else {
                    Object[][] data = eventController.getNonRepeatableEvents(ActiveUser.getUser().getId());
                    table.getColumnModel().getColumn(3).setHeaderValue("Start Date");
                    table.getTableHeader().repaint();
                    Utility.updateData(data, columnNames.length, model);
                    repeatableButton.setText("Repeatable Events");
                    for (int i = 0; i<widths.length; i++) {
                        table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
                        table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
                    }
                }
            }
        });

        titleTextField = Utility.textFieldGenerator(50,40,180,35, Filters.TITLE);
        titleTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (titleTextField.getText().equals(Filters.TITLE)) {
                    titleTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (titleTextField.getText().equals("")) {
                    titleTextField.setText(Filters.TITLE);
                }
            }
        });

        keywordsTextField = Utility.textFieldGenerator(355,460,150,35, Filters.KEYWORDS);
        keywordsTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (keywordsTextField.getText().equals(Filters.KEYWORDS)) {
                    keywordsTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (keywordsTextField.getText().equals("")) {
                    keywordsTextField.setText(Filters.KEYWORDS);
                }
            }
        });

        locationTextField = Utility.textFieldGenerator(15,460,150,35, Filters.PLACE);
        locationTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (locationTextField.getText().equals(Filters.PLACE)) {
                    locationTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (locationTextField.getText().equals("")) {
                    locationTextField.setText(Filters.PLACE);
                }
            }
        });

        availableSpacesTextField = Utility.textFieldGenerator(185,460,150,35, Filters.AVAILABLE_SPACES);
        availableSpacesTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (availableSpacesTextField.getText().equals(Filters.AVAILABLE_SPACES)) {
                    availableSpacesTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (availableSpacesTextField.getText().equals("")) {
                    availableSpacesTextField.setText(Filters.AVAILABLE_SPACES);
                }
            }
        });

        Object[][] data = eventController.getNonRepeatableEvents(ActiveUser.getUser().getId());

        table = new JTable();
        table.setRowHeight(40);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        Utility.updateData(data, columnNames.length, model);
        table.setModel(model);
        for (int i = 0; i<widths.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }

        RendererAndEditor rendererAndEditorCancel = new RendererAndEditor("Cancel Event");
        ActionListener actionListenerCancelEvent = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int eventId = (int) tableModel.getValueAt(rendererAndEditorCancel.getRow(), columnNames.length-1);
                tableModel.removeRow(rendererAndEditorCancel.getRow());
                eventOrganisingController.delete(eventId);
            }
        };
        rendererAndEditorCancel.addActionListener(actionListenerCancelEvent);

        table.getColumnModel().getColumn(7).setCellRenderer(rendererAndEditorCancel);
        table.getColumnModel().getColumn(7).setCellEditor(rendererAndEditorCancel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedColumn() != 7) {
                    Vector row = model.getDataVector().elementAt(table.getSelectedRow());
                    Event event = eventController.getEvent((Integer) row.get(columnNames.length-1));
                    boolean isEditable = false;
                    if (ActiveUser.getUser().getRole()==1) {
                        isEditable = true;
                    } else {
                        isEditable = eventOrganisingController.checkIfExists(ActiveUser.getUser().getId(), event.getId());
                    }
                    EventDetailsUI eventDetailsUI = new EventDetailsUI(event, isEditable);
                    SmallFrame smallFrame = new SmallFrame(eventDetailsUI);
                    smallFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 900, 330);

        //adding components to contentPane panel
        this.add(goBackButton);
        this.add(filterButton);
        this.add(searchButton);
        this.add(repeatableButton);
        this.add(searchEventLabel);
        this.add(titleTextField);
        this.add(keywordsTextField);
        this.add(locationTextField);
        this.add(availableSpacesTextField);
        this.add(scrollPane);
        this.add(datePickerStart);
        this.add(datePickerEnd);
        this.add(eventTypesComboBox);
    }
}
