package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.controllers.EventOrganisingController;
import com.eet.memory.ActiveUser;
import com.eet.models.Event;
import com.eet.models.Filters;
import com.eet.ui.*;
import com.eet.ui.Frame;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

public class ViewOwnEvents extends JPanel {

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

    private static final String[] COLUMN_NAMES = {"Title",
            "Type",
            "Description",
            "Start Date",
            "Duration",
            "Spaces",
            "Place",
            "Click to cancel",
            "id"
    };
    private static final int[] WIDTHS = {130, 60, 180, 120, 60, 70, 150, 130, 0};

    public JButton getGoBackButton() {
        return goBackButton;
    }

    //Constructor
    public ViewOwnEvents(){

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

        filterButton = new ButtonBuilder("Filter")
                .withBounds(530, 460, 140, 85)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0,0,0))
                .withFont(new Font("sansserif", 0, 18))
                .withActionListener(e -> {
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

                        Filters filters = Utility.validateEventFilters(map, startDate, endDate);
                        if (filters==null) {
                            System.out.println("wrong filters");
                        } else {
                            Object[][] data;
                            if (repeatableButton.getText().equals("Repeatable Events")) {
                                data = eventController.getNonRepeatableEventsWithFiltersOrganised(ActiveUser.getUser().getId(), filters);
                            } else {
                                data = eventController.getRepeatableEventsWithFiltersOrganised(ActiveUser.getUser().getId(), filters);
                            }
                            Utility.updateData(data, COLUMN_NAMES.length, 1, model);
                        }
                    } catch (ClassCastException exception) {
                        System.out.println(exception.getMessage());
                    }
                }).build();

        goBackButton = new ButtonBuilder("Go back")
                .withBounds(720, 460, 140, 85)
                .withBackground(new Color(214,217,223))
                .withForeground(new Color(0,0,0))
                .withFont(new Font("sansserif",0,18))
                .withActionListener(e -> {
                    Utility.changeToUsersView(ActiveUser.getUser().getRole());
                    Frames.getJFrame(Frame.Big).dispose();
                }).build();

        searchEventLabel = new LabelBuilder("Search Event")
                .withBounds(5, 5, 90, 35)
                .withBackground(new Color(214,217,223))
                .withForeground(new Color(0,0,0))
                .withFont(new Font("sansserif",0,12))
                .build();

        searchButton = new ButtonBuilder("Search")
                .withBounds(250, 40, 90, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
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
                    Utility.updateData(data, COLUMN_NAMES.length, 1, model);
                }).build();

        repeatableButton = new ButtonBuilder("Repeatable Events")
                .withBounds(380, 40, 180, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
                    Object[][] data;
                    if(repeatableButton.getText().equals("Repeatable Events")) {
                        data = eventController.getRepeatableEvents(ActiveUser.getUser().getId());
                        table.getColumnModel().getColumn(3).setHeaderValue("Repetition");
                        table.getTableHeader().repaint();
                        repeatableButton.setText("One time Events");
                        for (int i = 0; i< WIDTHS.length; i++) {
                            table.getColumnModel().getColumn(i).setMinWidth(WIDTHS[i]);
                            table.getColumnModel().getColumn(i).setMaxWidth(WIDTHS[i]);
                        }
                    }
                    else {
                        data = eventController.getNonRepeatableEvents(ActiveUser.getUser().getId());
                        table.getColumnModel().getColumn(3).setHeaderValue("Start Date");
                        table.getTableHeader().repaint();
                        repeatableButton.setText("Repeatable Events");
                        for (int i = 0; i< WIDTHS.length; i++) {
                            table.getColumnModel().getColumn(i).setMinWidth(WIDTHS[i]);
                            table.getColumnModel().getColumn(i).setMaxWidth(WIDTHS[i]);
                        }
                    }
                    Utility.updateData(data,  COLUMN_NAMES.length,1, model);
                }).build();

        titleTextField = new TextFieldBuilder(Filters.TITLE)
                .withBounds(50, 40, 180, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .build();
        titleTextField.addFocusListener(Utility.getPlaceholder(Filters.TITLE));

        keywordsTextField = new TextFieldBuilder(Filters.KEYWORDS)
                .withBounds(355, 460, 150, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .build();
        keywordsTextField.addFocusListener(Utility.getPlaceholder(Filters.KEYWORDS));

        locationTextField = new TextFieldBuilder(Filters.PLACE)
                .withBounds(15, 460, 150, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .build();
        locationTextField.addFocusListener(Utility.getPlaceholder(Filters.PLACE));

        availableSpacesTextField = new TextFieldBuilder(Filters.AVAILABLE_SPACES)
                .withBounds(185, 460, 150, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(new Color(0, 0, 0))
                .withFont(new Font("sansserif", 0, 12))
                .build();
        availableSpacesTextField.addFocusListener(Utility.getPlaceholder(Filters.AVAILABLE_SPACES));

        Object[][] data = eventController.getNonRepeatableEvents(ActiveUser.getUser().getId());

        table = new JTable();
        table.setRowHeight(40);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(COLUMN_NAMES);
        Utility.updateData(data, COLUMN_NAMES.length, 1, model);
        table.setModel(model);
        for (int i = 0; i< WIDTHS.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(WIDTHS[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(WIDTHS[i]);
        }

        RendererAndEditor rendererAndEditorCancel = new RendererAndEditor("Cancel Event");
        ActionListener actionListenerCancelEvent = e -> {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            int eventId = (int) tableModel.getValueAt(rendererAndEditorCancel.getRow(), COLUMN_NAMES.length-1);
            tableModel.removeRow(rendererAndEditorCancel.getRow());
            eventOrganisingController.delete(eventId);
        };
        rendererAndEditorCancel.addActionListener(actionListenerCancelEvent);

        table.getColumnModel().getColumn(7).setCellRenderer(rendererAndEditorCancel);
        table.getColumnModel().getColumn(7).setCellEditor(rendererAndEditorCancel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedColumn() != 7) {
                    Vector row = model.getDataVector().elementAt(table.getSelectedRow());
                    Event event = eventController.getEvent((Integer) row.get(COLUMN_NAMES.length-1));
                    boolean isEditable = true;
                    EventDetailsUI eventDetailsUI = new EventDetailsUI(event, isEditable, ViewOwnEvents.this);
                    Frames.getJFrame(Frame.Small).changePanel(eventDetailsUI);
                    Frames.getJFrame(Frame.Big).dispose();
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
