package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.dao.UserDao;
import com.eet.models.User;
import com.eet.ui.SmallFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;


public class RegisterUI extends JPanel {

    //declaring components
    private JButton login;
    private static JButton register;
    private JButton exit;
    private JButton goBack;
    private JLabel registration;
    private JLabel haveAnAccount;
    private JTextField enterId;
    private JTextField userName;
    private JTextField userSurname;
    private JPasswordField password;
    private final char[] passwordArray = new char[]{'P','a','s','s','w','o','r','d'};
    private UserController userController;

    public static JButton getRegister() {
        return register;
    }

    //Constructor
    public RegisterUI() {
        userController = new UserController();

        //pane with null layout
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(192, 192, 192));

        //button for login
        login = new JButton();
        login.setBounds(145, 340, 90, 35);
        login.setBackground(new Color(214, 217, 223));
        login.setForeground(new Color(0, 0, 0));
        login.setEnabled(true);
        login.setFont(new Font("sansserif", 0, 12));
        login.setText("Login");
        login.setVisible(true);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SmallFrame.getjFrame().changePanel(new LoginUI());
            }
        });

        //button for confirming input - registration
        register = new JButton();
        register.setBounds(145, 230, 90, 35);
        register.setBackground(new Color(214, 217, 223));
        register.setForeground(new Color(0, 0, 0));
        register.setEnabled(true);
        register.setFont(new Font("sansserif", 0, 12));
        register.setText("Register");
        register.setVisible(true);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = enterId.getText();
                char[] inputArray = password.getPassword();
                String name = userName.getText();
                String surname = userSurname.getText();
                userController.createUser(id, inputArray, name, surname);
            }
        });

        //a button to close the program
        exit = new JButton();
        exit.setBounds(200, 430, 90, 35);
        exit.setBackground(new Color(214, 217, 223));
        exit.setForeground(new Color(0, 0, 0));
        exit.setEnabled(true);
        exit.setFont(new Font("sansserif", 0, 12));
        exit.setText("Exit");
        exit.setVisible(true);
        exit.addActionListener(e -> System.exit(0));

        //a button to go to the previous window
        goBack = new JButton();
        goBack.setBounds(90, 430, 90, 35);
        goBack.setBackground(new Color(214, 217, 223));
        goBack.setForeground(new Color(0, 0, 0));
        goBack.setEnabled(true);
        goBack.setFont(new Font("sansserif", 0, 12));
        goBack.setText("Go back");
        goBack.setVisible(true);
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//				add connection to the class of the main program
//				new x();
//				dispose();
            }
        });

        //a label at the top of the window
        registration = new JLabel();
        registration.setBounds(6, 5, 90, 35);
        registration.setBackground(new Color(214, 217, 223));
        registration.setForeground(new Color(0, 0, 0));
        registration.setEnabled(true);
        registration.setFont(new Font("sansserif", 0, 12));
        registration.setText("Registration");
        registration.setVisible(true);

        //a question to tha user whether they already have an account or not
        haveAnAccount = new JLabel();
        haveAnAccount.setBounds(120, 300, 157, 31);
        haveAnAccount.setBackground(new Color(214, 217, 223));
        haveAnAccount.setForeground(new Color(0, 0, 0));
        haveAnAccount.setEnabled(true);
        haveAnAccount.setFont(new Font("sansserif", 0, 12));
        haveAnAccount.setText("Already have an account?");
        haveAnAccount.setVisible(true);

        userSurname = new JTextField();
        userSurname.setBounds(110, 140, 155, 30);
        userSurname.setBackground(new Color(255, 255, 255));
        userSurname.setForeground(new Color(0, 0, 0));
        userSurname.setEnabled(true);
        userSurname.setFont(new Font("sansserif", 0, 12));
        userSurname.setText("Surname");
        userSurname.setVisible(true);
        userSurname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userSurname.getText().equals("Surname")) {
                    userSurname.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userSurname.getText().equals("")) {
                    userSurname.setText("Surname");
                }
            }
        });

        //a text field where user can input their id
        enterId = new JTextField();
        enterId.setBounds(110, 50, 155, 30);
        enterId.setBackground(new Color(255, 255, 255));
        enterId.setForeground(new Color(0, 0, 0));
        enterId.setEnabled(true);
        enterId.setFont(new Font("sansserif", 0, 12));
        enterId.setText("ID");
        enterId.setVisible(true);
        enterId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (enterId.getText().equals("ID")) {
                    enterId.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (enterId.getText().equals("")) {
                    enterId.setText("ID");
                }
            }
        });

        //a text field where user can input their name
        userName = new JTextField();
        userName.setBounds(110, 95, 155, 30);
        userName.setBackground(new Color(255, 255, 255));
        userName.setForeground(new Color(0, 0, 0));
        userName.setEnabled(true);
        userName.setFont(new Font("sansserif", 0, 12));
        userName.setText("Name");
        userName.setVisible(true);
        userName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userName.getText().equals("Name")) {
                    userName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userName.getText().equals("")) {
                    userName.setText("Name");
                }
            }
        });


        //a text field where user can input their password
        password = new JPasswordField();
        password.setBounds(110, 185, 155, 30);
        password.setBackground(new Color(255, 255, 255));
        password.setForeground(new Color(0, 0, 0));
        password.setEnabled(true);
        password.setFont(new Font("sansserif", 0, 12));
        password.setEchoChar((char) 0);
        password.setText("Password");
        password.setVisible(true);
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] inputArray = password.getPassword();
                if (Arrays.equals(inputArray, passwordArray)) {
                    password.setText("");
                    password.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                char[] inputArray = password.getPassword();
                char[] emptyArray = new char[0];
                if (Arrays.equals(inputArray, emptyArray)) {
                    password.setEchoChar((char) 0);
                    password.setText("Password");
                }
            }
        });

        //adding components to contentPane panel
        this.add(login);
        this.add(register);
        this.add(exit);
        this.add(goBack);
        this.add(registration);
        this.add(haveAnAccount);
        this.add(enterId);
        this.add(userName);
        this.add(userSurname);
        this.add(password);
    }}

