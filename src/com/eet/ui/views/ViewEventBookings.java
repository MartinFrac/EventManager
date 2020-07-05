package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.controllers.UserController;
import com.eet.models.Role;
import com.eet.models.User;
import com.eet.ui.*;
import com.eet.ui.Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

public class ViewEventBookings extends JPanel {

    private JButton goBackButton;
    private JLabel searchStudentsLabel;
    private JButton privileged;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField idTextField;
    private JButton searchButton;
    private JButton filterButton;
    private JTable table;
    private DefaultTableModel model;
    private RendererAndEditor rendererAndEditorCancel;

    private UserController userController;
    private EventController eventController;

    private static final String[] COLUMN_NAMES = {"ID",
            "Name",
            "Surname",
            "Role",
            "Cancel",
            "id"
    };
    private static final int[] WIDTHS = {100, 170, 200, 180, 250, 0};

    public static final String ID = "ID";
    public static final String NAME = "Name";
    public static final String SURNAME = "Surname";
    private static final String GET_PRIVILEGED = "Get Privileged";
    private static final String GET_NON_PRIVILEGED = "Get Non Privileged";

    public JButton getGoBackButton() {
        return goBackButton;
    }

    //Constructor
    public ViewEventBookings(int eventId){

        userController = new UserController();
        eventController = new EventController();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(900,600));
        this.setBackground(new Color(192,192,192));

        filterButton = new ButtonBuilder("Filter")
                .withBounds(440, 500, 140, 85)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 18))
                .withActionListener(e -> {
                    try {
                        HashMap<String, String> map = new HashMap<>();
                        String id = idTextField.getText();
                        String name = nameTextField.getText();
                        String surname = surnameTextField.getText();
                        map.put("id", id);
                        map.put("name", name);
                        map.put("surname", surname);
                        if (privileged.getText().equals(GET_PRIVILEGED)) {
                            map.put("role", Role.USER.toString());
                        } else {
                            map.put("role", Role.EVENT_ORGANISER.toString());
                        }

                        User user = Utility.validateUserFilters(map);
                        Object[][] data = userController.getByBookingEventIdWithFilters(user, eventId);
                        Utility.updateData(data, COLUMN_NAMES.length, 1, model);
                    } catch (ClassCastException exception) {
                        System.out.println(exception.getMessage());
                    }
                }).build();

        privileged = new ButtonBuilder(GET_NON_PRIVILEGED)
                .withBounds(380, 40, 180, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
                    Object[][] data;
                    if (GET_NON_PRIVILEGED.equals(privileged.getText())) {
                        privileged.setText(GET_PRIVILEGED);
                        data = userController.getNonPrivilegedByBookingEventId(eventId);
                    } else {
                        privileged.setText(GET_NON_PRIVILEGED);
                        data = userController.getPrivilegedByBookingEventId(eventId);
                    }
                    Utility.updateData(data, COLUMN_NAMES.length, 1, model);
                }).build();

        goBackButton = new ButtonBuilder("Go back")
                .withBounds(720, 500, 140, 85)
                .withBackground(new Color(214,217,223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif",0,18))
                .withActionListener(e -> Frames.getJFrame(Frame.Big).dispose()).build();

        searchStudentsLabel = new LabelBuilder("Search Student")
                .withBounds(5, 5, 90, 35)
                .withBackground(new Color(214,217,223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif",0,12))
                .build();

        searchButton = new ButtonBuilder("Search")
                .withBounds(250, 40, 90, 35)
                .withBackground(new Color(214, 217, 223))
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .withActionListener(e -> {
                    Object[][] data;
                    String id = idTextField.getText();
                    if (ID.equals(id)) {
                        id = "";
                    }
                    if (privileged.getText().equals(GET_PRIVILEGED)) {
                        data = userController.getNonPrivilegedByIdBookingEventId(id, eventId);
                    } else {
                        data = userController.getPrivilegedByIdBookingEventId(id, eventId);
                    }
                    Utility.updateData(data, COLUMN_NAMES.length, 1, model);
                }).build();

        idTextField = new TextFieldBuilder(ID)
                .withBounds(50, 40, 180, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        idTextField.addFocusListener(Utility.getPlaceholder(ID));

        nameTextField = new TextFieldBuilder(NAME)
                .withBounds(15, 500, 150, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        nameTextField.addFocusListener(Utility.getPlaceholder(NAME));

        surnameTextField = new TextFieldBuilder(SURNAME)
                .withBounds(200, 500, 150, 35)
                .withBackground(Color.WHITE)
                .withForeground(Color.BLACK)
                .withFont(new Font("sansserif", 0, 12))
                .build();
        surnameTextField.addFocusListener(Utility.getPlaceholder(SURNAME));

        Object[][] data = userController.getPrivilegedByBookingEventId(eventId);

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

        rendererAndEditorCancel = new RendererAndEditor("Cancel Booking");
        ActionListener actionListenerBookings = e -> {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            String userId = (String) tableModel.getValueAt(rendererAndEditorCancel.getRow(), COLUMN_NAMES.length-1);
            tableModel.removeRow(rendererAndEditorCancel.getRow());
            eventController.deleteBooking(eventId, userId);
        };
        rendererAndEditorCancel.addActionListener(actionListenerBookings);

        table.getColumnModel().getColumn(4).setCellRenderer(rendererAndEditorCancel);
        table.getColumnModel().getColumn(4).setCellEditor(rendererAndEditorCancel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 900, 380);

        //adding components to contentPane panel
        this.add(privileged);
        this.add(idTextField);
        this.add(goBackButton);
        this.add(filterButton);
        this.add(searchButton);
        this.add(searchStudentsLabel);
        this.add(nameTextField);
        this.add(surnameTextField);
        this.add(scrollPane);
    }
}
