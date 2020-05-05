package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.controllers.UserController;
import com.eet.ui.ButtonEditor;
import com.eet.ui.ButtonRenderer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ViewStudentsUI extends JPanel {

    private JMenuBar menuBar;
    private JButton goBack;
    private JLabel adminView;
    private JLabel students;
    private JTextField id;
    private JTextField name;
    private UserController userController;

    public JButton getGoBack() {
        return goBack;
    }

    //Constructor
    public ViewStudentsUI() {

        userController = new UserController();
        //pane with null layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(900, 520));
        this.setBackground(new Color(192, 192, 192));


        goBack = new JButton();
        goBack.setBounds(405, 450, 90, 35);
        goBack.setBackground(new Color(214, 217, 223));
        goBack.setForeground(new Color(0, 0, 0));
        goBack.setEnabled(true);
        goBack.setFont(new Font("sansserif", 0, 12));
        goBack.setText("Go back");
        goBack.setVisible(true);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//				new x();
//				dispose();
            }
        });

        adminView = new JLabel();
        adminView.setBounds(7, 4, 90, 35);
        adminView.setBackground(new Color(214, 217, 223));
        adminView.setForeground(new Color(0, 0, 0));
        adminView.setEnabled(true);
        adminView.setFont(new Font("sansserif", 0, 12));
        adminView.setText("Admin view");
        adminView.setVisible(true);

        students = new JLabel();
        students.setBounds(50, 80, 90, 35);
        students.setBackground(new Color(214, 217, 223));
        students.setForeground(new Color(0, 0, 0));
        students.setEnabled(true);
        students.setFont(new Font("sansserif", 0, 12));
        students.setText("List of students");
        students.setVisible(true);

        id = new JTextField();
        id.setBounds(50, 40, 150, 32);
        id.setBackground(new Color(255, 255, 255));
        id.setForeground(new Color(0, 0, 0));
        id.setEnabled(true);
        id.setFont(new Font("sansserif", 0, 12));
        id.setText("Filter by ID");
        id.setVisible(true);
        id.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (id.getText().equals("Filter by ID")) {
                    id.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (id.getText().equals("")) {
                    id.setText("Filter by ID");
                }
            }
        });

        name = new JTextField();
        name.setBounds(220, 40, 150, 32);
        name.setBackground(new Color(255, 255, 255));
        name.setForeground(new Color(0, 0, 0));
        name.setEnabled(true);
        name.setFont(new Font("SansSerif", 0, 12));
        name.setText("Filter by name");
        name.setVisible(true);
        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (name.getText().equals("Filter by name")) {
                    name.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (name.getText().equals("")) {
                    name.setText("Filter by name");
                }
            }
        });

        //creating a table
        JTable table = new JTable();
        table.setRowHeight(40);
        table.setForeground(Color.black);
        Font font = new Font("Arial", 1, 15);
        table.setFont(font);
        DefaultTableModel model = new DefaultTableModel();
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 125, 900, 300);

        String[] columnNames = {"Name", "Surname", "Password", "Organisation rights"};

        model.setColumnIdentifiers(columnNames);

        table.setModel(model);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());
        //Object[][] events = (Object[][]) userController.getStudents();
//        if (events != null) {
//            for (int i = 0; i < events.length; i++) {
//                model.addRow(events[i]);
//            }
//        }

        //adding components to contentPane panel
        this.add(goBack);
        this.add(adminView);
        this.add(students);
        this.add(id);
        this.add(name);
        this.add(pane);

    }
}