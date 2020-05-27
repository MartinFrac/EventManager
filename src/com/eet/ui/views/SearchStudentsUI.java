package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.memory.ActiveUser;
import com.eet.models.Event;
import com.eet.models.Filters;
import com.eet.ui.BigFrame;
import com.eet.ui.RendererAndEditor;
import com.eet.ui.SmallFrame;
import com.eet.ui.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;

public class SearchStudentsUI extends JPanel {

    private JButton goBackButton;
    private JLabel searchStudentsLabel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField idTextField;
    private JComboBox<String> roleJComboBox;
    private JButton searchButton;
    private JButton filterButton;
    private JTable table;
    private DefaultTableModel model;

    private UserController userController;

    private static final String[] columnNames = {"ID",
            "Name",
            "Surname",
            "Role",
            "id"
    };
    private static final int[] widths = {100, 120, 130, 150, 100, 150, 150, 0};

    private static final String ID = "ID";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";

    public JButton getGoBackButton() {
        return goBackButton;
    }

    //Constructor
    public SearchStudentsUI(){

        userController = new UserController();

        this.setLayout(null);
        this.setPreferredSize(new Dimension(900,600));
        this.setBackground(new Color(192,192,192));


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
                    String id = idTextField.getText();
                    String name = nameTextField.getText();
                    String surname = surnameTextField.getText();
                    String role = (String) roleJComboBox.getSelectedItem();
                    map.put("id", id);
                    map.put("name", name);
                    map.put("surname", surname);
                    map.put("role", role);

                    Filters filters = Utility.validateFilters(map);
                    if (filters==null) {
                        System.out.println("wrong filters");
                    } else {
                        Object[][] date = userController.getUsersWithFilters(filters);
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
                data = userController.getUsersById(id);
                Utility.updateData(data, columnNames.length, model);
            }
        });

        nameTextField = Utility.textFieldGenerator(50,40,180,35, Filters.TITLE);
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


        Object[][] data = userController.getAll();

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

        RendererAndEditor rendererAndEditorGrant = new RendererAndEditor("Grant Privileges");
        ActionListener actionListenerGrant = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int userId = (int) tableModel.getValueAt(rendererAndEditorGrant.getRow(), columnNames.length-1);
                tableModel.removeRow(rendererAndEditorGrant.getRow());
                userController.grantPrivileges(userId);
            }
        };
        rendererAndEditorGrant.addActionListener(actionListenerGrant);

        RendererAndEditor rendererAndEditorRevoke = new RendererAndEditor("Revoke Privileges");
        ActionListener actionListenerRevoke = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        rendererAndEditorRevoke.addActionListener(actionListenerRevoke);

        RendererAndEditor rendererAndEditorBookings = new RendererAndEditor("Bookings");
        ActionListener actionListenerBookings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        rendererAndEditorBookings.addActionListener(actionListenerBookings);

        table.getColumnModel().getColumn(5).setCellRenderer(rendererAndEditorGrant);
        table.getColumnModel().getColumn(5).setCellEditor(rendererAndEditorGrant);
        table.getColumnModel().getColumn(6).setCellRenderer(rendererAndEditorRevoke);
        table.getColumnModel().getColumn(6).setCellEditor(rendererAndEditorRevoke);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedColumn() <4) {
                    Vector row = model.getDataVector().elementAt(table.getSelectedRow());
                    Event event = userController.getUser((Integer) row.get(columnNames.length-1));
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
        this.add(searchStudentsLabel);
        this.add(nameTextField);
        this.add(scrollPane);
    }
}
