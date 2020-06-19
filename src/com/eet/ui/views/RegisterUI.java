package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.models.User;
import com.eet.ui.*;
import com.eet.ui.Frame;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import static com.eet.ui.Utility.*;


public class RegisterUI extends JPanel {

    private JTextField idTextField;
    private JButton confirmButton;
    private JButton goBack;
    private JTextField nameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField surnameTextField;
    public static final char[] PASSWORD_ARRAY_1 = new char[]{'P','a','s','s','w','o','r','d'};
    public static final char[] PASSWORD_ARRAY_2 = new char[]{'R','e','-','e','n','t','e','r',' ','P','a','s','s','w','o','r','d'};
    public static final String NAME = "Enter Name";
    public static final String SURNAME = "Enter Surname";
    public static final String ID = "Enter ID";
    private static final Color BACKGROUND_TEXT_COLOR = new Color(255,255,255);
    private static final Color FOREGROUND_TEXT_COLOR = new Color(255,255,255);
    private static final Font FONT = new Font("sansserif",0,12);
    private UserController userController;

    //Constructor
    public RegisterUI(){

        userController = new UserController();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(400,500));
        this.setBackground(new Color(139,217,169));

        nameTextField = new TextFieldBuilder(NAME)
                .withBounds(100, 40, 200, 35)
                .withBackground(BACKGROUND_TEXT_COLOR)
                .withForeground(FOREGROUND_TEXT_COLOR)
                .withFont(FONT)
                .build();
        nameTextField.addFocusListener(getPlaceholder(NAME));

        surnameTextField = new TextFieldBuilder(SURNAME)
                .withBounds(100, 100, 200, 35)
                .withBackground(BACKGROUND_TEXT_COLOR)
                .withForeground(FOREGROUND_TEXT_COLOR)
                .withFont(FONT)
                .build();
        surnameTextField.addFocusListener(getPlaceholder(SURNAME));

        idTextField = new TextFieldBuilder(ID)
                .withBounds(100, 160, 200, 35)
                .withBackground(BACKGROUND_TEXT_COLOR)
                .withForeground(FOREGROUND_TEXT_COLOR)
                .withFont(FONT)
                .build();
        idTextField.addFocusListener(getPlaceholder(ID));

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(100,220,200,35);
        passwordField1.setBackground(new Color(214,217,223));
        passwordField1.setForeground(FOREGROUND_TEXT_COLOR);
        passwordField1.setEnabled(true);
        passwordField1.setFont(FONT);
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
        passwordField2.setForeground(FOREGROUND_TEXT_COLOR);
        passwordField2.setEnabled(true);
        passwordField2.setFont(FONT);
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

        confirmButton = new ButtonBuilder("Register")
                .withBounds(140, 370, 120, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(FONT)
                .withActionListener(e -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", nameTextField.getText());
                    map.put("surname", surnameTextField.getText());
                    map.put("id", idTextField.getText());
                    map.put("password1", new String(passwordField1.getPassword()));
                    map.put("password2", new String(passwordField2.getPassword()));
                    if (Utility.validateRegister(map, userController)) {
                        User user = new User();
                        user.setId(map.get("id"));
                        user.setName(map.get("name"));
                        user.setSurname(map.get("surname"));
                        user.setPassword(map.get("password1"));
                        userController.create(user);
                        LoginUI loginUI = new LoginUI();
                        Frames.getJFrame(Frame.Small).changePanel(loginUI);
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Account created");
                    }
                }).build();


        goBack = new ButtonBuilder("Go Back")
                .withBounds(140, 420, 120, 35)
                .withBackground(new Color(214,72,105))
                .withForeground(new Color(51,255,255))
                .withFont(FONT)
                .withActionListener(e -> {
                    LoginUI loginUI = new LoginUI();
                    Frames.getJFrame(Frame.Small).changePanel(loginUI);
                }).build();

        //adding components to contentPane panel
        this.add(idTextField);
        this.add(confirmButton);
        this.add(nameTextField);
        this.add(passwordField1);
        this.add(passwordField2);
        this.add(surnameTextField);
        this.add(goBack);
    }
}
