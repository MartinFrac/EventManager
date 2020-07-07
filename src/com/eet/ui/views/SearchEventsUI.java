package com.eet.ui.views;

import com.eet.controllers.BookingController;
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

public class SearchEventsUI extends JPanel {

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
    private BookingController bookingController;

    private static final String[] columnNames = {"Title",
            "Type",
            "Description",
            "Start Date",
            "Duration",
            "Spaces",
            "Place",
            "Book",
            "id"
    };

    public JButton getGoBackButton() {
        return goBackButton;
    }

    //Constructor
    public SearchEventsUI(){

        eventController = new EventController();
        eventOrganisingController = new EventOrganisingController();
        bookingController = new BookingController();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(900,600));
        this.setBackground(new Color(192,192,192));

        searchEventLabel = new LabelBuilder("Search Event")
                .withBounds(5, 5, 90, 35)
                .withBackground(new Color(214,217,223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif",0,12))
                .build();

        titleTextField = new TextFieldBuilder(Filters.TITLE)
                .withBounds(50, 40, 180, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        titleTextField.addFocusListener(Utility.getPlaceholder(Filters.TITLE));

        searchButton = new ButtonBuilder("Search")
                .withBounds(250, 40, 90, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
                    Object[][] data;
                    String userId = ActiveUser.getUser().getId();
                    String name = titleTextField.getText();
                    if (Filters.TITLE.equals(name)) {
                        name = "";
                    }
                    if (repeatableButton.getText().equals("Repeatable Events")) {
                        data = eventController.getNonRepeatableEventsNotBookings(userId, name);
                    } else {
                        data = eventController.getRepeatableEventsNotBookings(userId, name);
                    }
                    Utility.updateData(data, columnNames.length, 1, model);
                }).build();

        repeatableButton = new ButtonBuilder("Repeatable Events")
                .withBounds(380, 40, 180, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
                    Object[][] data;
                    if(repeatableButton.getText().equals("Repeatable Events")) {
                        data = eventController.getRepeatableEventsNotBookings(ActiveUser.getUser().getId());
                        table.getColumnModel().getColumn(3).setHeaderValue("Repetition");
                        table.getTableHeader().repaint();
                        repeatableButton.setText("One time Events");
                        int[] widths = {150, 60, 110, 200, 60, 70, 150, 100, 0};
                        for (int i = 0; i<widths.length; i++) {
                            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
                            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
                        }
                    }
                    else {
                        data = eventController.getNonRepeatableEventsNotBookings(ActiveUser.getUser().getId());
                        table.getColumnModel().getColumn(3).setHeaderValue("Start Date");
                        table.getTableHeader().repaint();
                        repeatableButton.setText("Repeatable Events");
                        int[] widths = {150, 60, 190, 120, 60, 70, 150, 100, 0};
                        for (int i = 0; i<widths.length; i++) {
                            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
                            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
                        }
                    }
                    Utility.updateData(data,columnNames.length, 1, model);
                }).build();

        keywordsTextField = new TextFieldBuilder(Filters.KEYWORDS)
                .withBounds(355, 460, 150, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        keywordsTextField.addFocusListener(Utility.getPlaceholder(Filters.KEYWORDS));

        locationTextField = new TextFieldBuilder(Filters.PLACE)
                .withBounds(15, 460, 150, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        locationTextField.addFocusListener(Utility.getPlaceholder(Filters.PLACE));

        availableSpacesTextField = new TextFieldBuilder(Filters.AVAILABLE_SPACES)
                .withBounds(185, 460, 150, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        availableSpacesTextField.addFocusListener(Utility.getPlaceholder(Filters.AVAILABLE_SPACES));

        datePickerStart = Utility.datePickerGenerator(15, 510, 150, 25);

        datePickerEnd = Utility.datePickerGenerator(185, 510, 150, 25);

        String[] options = {"None", "Online", "Physical"};
        eventTypesComboBox = new JComboBox<>(options);
        eventTypesComboBox.setBounds(355, 510, 150, 25);

        filterButton = new ButtonBuilder("Filter")
                .withBounds(530, 460, 140, 85)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
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
                                data = eventController.getNonRepeatableEventsWithFiltersNotBookings(ActiveUser.getUser().getId(), filters);
                            } else {
                                data = eventController.getRepeatableEventsWithFiltersNotBookings(ActiveUser.getUser().getId(), filters);
                            }
                            Utility.updateData(data,  columnNames.length, 1, model);
                        }
                    } catch (ClassCastException exception) {
                        System.out.println(exception.getMessage());
                    }
                }).build();

        goBackButton = new ButtonBuilder("Go back")
                .withBounds(720, 460, 140, 85)
                .withBackground(new Color(214,217,223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif",0,18))
                .withActionListener(e -> {
                    Utility.changeToUsersView(ActiveUser.getUser().getRole());
                    Frames.getJFrame(Frame.Big).dispose();
                }).build();

        Object[][] data = eventController.getNonRepeatableEventsNotBookings(ActiveUser.getUser().getId());

        table = new JTable();
        table.setRowHeight(40);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        Utility.updateData(data, columnNames.length, 1, model);
        table.setModel(model);
        int[] widths = {150, 60, 190, 120, 60, 70, 150, 100, 0};
        for (int i = 0; i<widths.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }
        RendererAndEditor rendererAndEditor = new RendererAndEditor("Book");
        ActionListener actionListener = e -> {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            int eventId = (int) tableModel.getValueAt(rendererAndEditor.getRow(), columnNames.length-1);
            tableModel.removeRow(rendererAndEditor.getRow());
            bookingController.create(ActiveUser.getUser().getId(), eventId);
        };
        rendererAndEditor.addActionListener(actionListener);
        table.getColumnModel().getColumn(7).setCellRenderer(rendererAndEditor);
        table.getColumnModel().getColumn(7).setCellEditor(rendererAndEditor);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedColumn() != 7) {
                    Vector row = model.getDataVector().elementAt(table.getSelectedRow());
                    Event event = eventController.getEvent((Integer) row.get(columnNames.length-1));
                    boolean isEditable = false;
                    isEditable = eventOrganisingController.checkIfExists(ActiveUser.getUser().getId(), event.getId());
                    EventDetailsUI eventDetailsUI = new EventDetailsUI(event, isEditable, SearchEventsUI.this);
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
