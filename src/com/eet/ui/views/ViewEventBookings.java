package com.eet.ui.views;

import com.eet.controllers.BookingController;
import com.eet.controllers.EventController;
import com.eet.controllers.UserController;
import com.eet.models.Role;
import com.eet.models.User;
import com.eet.ui.BigFrame;
import com.eet.ui.RendererAndEditor;
import com.eet.ui.Utility;

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

    private static final String[] columnNames = {"ID",
            "Name",
            "Surname",
            "Role",
            "Cancel",
            "id"
    };
    private static final int[] widths = {100, 170, 200, 180, 250, 0};

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


        filterButton = new JButton();
        filterButton.setBounds(440, 500, 140, 85);
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
                    Utility.updateData(data, columnNames.length, 1, model);
                } catch (ClassCastException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });

        privileged = new JButton();
        privileged.setBounds(380, 40, 180, 35);
        privileged.setBackground(new Color(214, 217, 223));
        privileged.setForeground(new Color(0, 0, 0));
        privileged.setEnabled(true);
        privileged.setFont(new Font("sansserif", 0, 12));
        privileged.setText(GET_NON_PRIVILEGED);
        privileged.setVisible(true);
        privileged.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data;
                if (GET_NON_PRIVILEGED.equals(privileged.getText())) {
                    privileged.setText(GET_PRIVILEGED);
                    data = userController.getNonPrivilegedByBookingEventId(eventId);
                } else {
                    privileged.setText(GET_NON_PRIVILEGED);
                    data = userController.getPrivilegedByBookingEventId(eventId);
                }
                Utility.updateData(data, columnNames.length, 1, model);
            }
        });

        goBackButton = new JButton();
        goBackButton.setBounds(720,500,140,85);
        goBackButton.setBackground(new Color(214,217,223));
        goBackButton.setForeground(new Color(0,0,0));
        goBackButton.setEnabled(true);
        goBackButton.setFont(new Font("sansserif",0,18));
        goBackButton.setText("Go back");
        goBackButton.setVisible(true);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BigFrame.getjFrame().dispose();
            }
        });

        searchStudentsLabel = new JLabel();
        searchStudentsLabel.setBounds(5,5,90,35);
        searchStudentsLabel.setBackground(new Color(214,217,223));
        searchStudentsLabel.setForeground(new Color(0,0,0));
        searchStudentsLabel.setEnabled(true);
        searchStudentsLabel.setFont(new Font("sansserif",0,12));
        searchStudentsLabel.setText("Search Student");
        searchStudentsLabel.setVisible(true);

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
                String id = idTextField.getText();
                if (ID.equals(id)) {
                    id = "";
                }
                if (privileged.getText().equals(GET_PRIVILEGED)) {
                    data = userController.getNonPrivilegedByIdBookingEventId(id, eventId);
                } else {
                    data = userController.getPrivilegedByIdBookingEventId(id, eventId);
                }
                Utility.updateData(data, columnNames.length, 1, model);
            }
        });

        idTextField = Utility.textFieldGenerator(50,40,180,35, ID);
        idTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ID.equals(idTextField.getText())) {
                    idTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idTextField.getText().equals("")) {
                    idTextField.setText(ID);
                }
            }
        });

        nameTextField = Utility.textFieldGenerator(15,500,150,35, NAME);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (NAME.equals(nameTextField.getText())) {
                    nameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameTextField.getText().equals("")) {
                    nameTextField.setText(NAME);
                }
            }
        });

        surnameTextField = Utility.textFieldGenerator(200,500,150,35, SURNAME);
        surnameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SURNAME.equals(surnameTextField.getText())) {
                    surnameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (surnameTextField.getText().equals("")) {
                    surnameTextField.setText(SURNAME);
                }
            }
        });


        Object[][] data = userController.getPrivilegedByBookingEventId(eventId);

        table = new JTable();
        table.setRowHeight(40);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        Utility.updateData(data, columnNames.length, 1, model);
        table.setModel(model);
        for (int i = 0; i<widths.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
            table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
        }

        rendererAndEditorCancel = new RendererAndEditor("Cancel Booking");
        ActionListener actionListenerBookings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                String userId = (String) tableModel.getValueAt(rendererAndEditorCancel.getRow(), columnNames.length-1);
                tableModel.removeRow(rendererAndEditorCancel.getRow());
                eventController.deleteBooking(eventId, userId);
            }
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
