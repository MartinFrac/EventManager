package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.models.User;
import com.eet.ui.SmallFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


public class RegisterUI extends JPanel {

    private JTextField idTextField;
    private JButton confirmButton;
    private JTextField nameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField surnameTextField;
    private static final char[] PASSWORD_ARRAY_1 = new char[]{'P','a','s','s','w','o','r','d'};
    private static final char[] PASSWORD_ARRAY_2 = new char[]{'R','e','-','e','n','t','e','r',' ','P','a','s','s','w','o','r','d'};
    private static final String NAME = "Enter Name";
    private static final String SURNAME = "Enter Surname";
    private static final String ID = "Enter ID";
    private UserController userController;

    public JButton getConfirmButton() {
        return confirmButton;
    }

    //Constructor
    public RegisterUI(){

        userController = new UserController();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(139,217,169));

        nameTextField = new JTextField();
        nameTextField.setBounds(100,40,200,35);
        nameTextField.setBackground(new Color(255,255,255));
        nameTextField.setForeground(new Color(0,0,0));
        nameTextField.setEnabled(true);
        nameTextField.setFont(new Font("sansserif",0,12));
        nameTextField.setText(NAME);
        nameTextField.setVisible(true);
        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, NAME);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, NAME);
            }
        });

        surnameTextField = new JTextField();
        surnameTextField.setBounds(100,100,200,35);
        surnameTextField.setBackground(new Color(255,255,255));
        surnameTextField.setForeground(new Color(0,0,0));
        surnameTextField.setEnabled(true);
        surnameTextField.setFont(new Font("sansserif",0,12));
        surnameTextField.setText(SURNAME);
        surnameTextField.setVisible(true);
        surnameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, SURNAME);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, SURNAME);
            }
        });

        idTextField = new JTextField();
        idTextField.setBounds(100,160,200,35);
        idTextField.setBackground(new Color(255,255,255));
        idTextField.setForeground(new Color(0,0,0));
        idTextField.setEnabled(true);
        idTextField.setFont(new Font("sansserif",0,12));
        idTextField.setText(ID);
        idTextField.setVisible(true);
        idTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                placeholderDisappear(e, ID);
            }

            @Override
            public void focusLost(FocusEvent e) {
                placeholderAppear(e, ID);
            }
        });

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(100,220,200,35);
        passwordField1.setBackground(new Color(214,217,223));
        passwordField1.setForeground(new Color(0,0,0));
        passwordField1.setEnabled(true);
        passwordField1.setFont(new Font("sansserif",0,12));
        passwordField1.setText(new String(PASSWORD_ARRAY_1));
        passwordField1.setEchoChar((char)0);
        passwordField1.setVisible(true);
        passwordField1.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        char[] inputArray = passwordField1.getPassword();
                        if(Arrays.equals(inputArray, PASSWORD_ARRAY_1)) {
                            passwordField1.setText("");
                            passwordField1.setEchoChar('*');
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        char[] inputArray = passwordField1.getPassword();
                        char[] emptyArray = new char[0];
                        if(Arrays.equals(inputArray, emptyArray)) {
                            passwordField1.setEchoChar((char)0);
                            passwordField1.setText(new String(PASSWORD_ARRAY_1));
                        }
                    }
                }
        );

        passwordField2 = new JPasswordField();
        passwordField2.setBounds(100,280,200,35);
        passwordField2.setBackground(new Color(214,217,223));
        passwordField2.setForeground(new Color(0,0,0));
        passwordField2.setEnabled(true);
        passwordField2.setFont(new Font("sansserif",0,12));
        passwordField2.setText(new String(PASSWORD_ARRAY_2));
        passwordField2.setEchoChar((char)0);
        passwordField2.setVisible(true);
        passwordField2.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        char[] inputArray = passwordField2.getPassword();
                        if(Arrays.equals(inputArray, PASSWORD_ARRAY_2)) {
                            passwordField2.setText("");
                            passwordField2.setEchoChar('*');
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        char[] inputArray = passwordField2.getPassword();
                        char[] emptyArray = new char[0];
                        if(Arrays.equals(inputArray, emptyArray)) {
                            passwordField2.setEchoChar((char)0);
                            passwordField2.setText(new String(PASSWORD_ARRAY_2));
                        }
                    }
                }
        );

        confirmButton = new JButton();
        confirmButton.setBounds(140,370,120,35);
        confirmButton.setBackground(new Color(214,72,105));
        confirmButton.setForeground(new Color(51,255,255));
        confirmButton.setEnabled(true);
        confirmButton.setFont(new Font("SansSerif",0,20));
        confirmButton.setText("Register");
        confirmButton.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> map = new HashMap<>();
                map.put("name", nameTextField.getText());
                map.put("surname", surnameTextField.getText());
                map.put("id", idTextField.getText());
                map.put("password1", new String(passwordField1.getPassword()));
                map.put("password2", new String(passwordField2.getPassword()));
                if (checkData(map)) {
                    User user = new User();
                    user.setId(map.get("id"));
                    user.setName(map.get("name"));
                    user.setSurname(map.get("surname"));
                    user.setPassword(map.get("password1"));
                    userController.create(user);
                    LoginUI loginUI = new LoginUI();
                    SmallFrame.getjFrame().changePanel(loginUI);
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Account created");
                }
            }
        });

        //adding components to contentPane panel
        this.add(idTextField);
        this.add(confirmButton);
        this.add(nameTextField);
        this.add(passwordField1);
        this.add(passwordField2);
        this.add(surnameTextField);
    }

    private boolean checkData(Map<String, String> map) {
        if (NAME.equals(map.get("name"))) {
            map.put("name", "");
        }
        if (SURNAME.equals(map.get("surname"))) {
            map.put("surname", "");
        }
        if (ID.equals(map.get("id"))) {
            map.put("id", "");
        }
        String password1 = new String(PASSWORD_ARRAY_1);
        if (password1.equals(map.get("password1"))) {
            map.put("password1", "");
        }
        String password2 = new String(PASSWORD_ARRAY_2);
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

    private void placeholderDisappear(AWTEvent event, String placeHolder) {
        JTextField textField = (JTextField) event.getSource();
        if (textField.getText().equals(placeHolder)) {
            textField.setText("");
        }
    }

    private void placeholderAppear(AWTEvent event, String placeHolder) {
        JTextField textField = (JTextField) event.getSource();
        if (textField.getText().equals("")) {
            textField.setText(placeHolder);
        }
    }
}
