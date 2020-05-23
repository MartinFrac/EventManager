package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.models.User;
import com.eet.ui.BigFrame;
import com.eet.ui.SmallFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;


public class LoginUI extends JPanel {

	private JButton loginButton;
	private JLabel noAccount;
	private JPasswordField password;
	private JButton register;
	private JTextField studentId;
	private final char[] passwordArray = new char[]{'P','a','s','s','w','o','r','d'};
	private UserController userController;

	public JButton getLoginButton() {
		return loginButton;
	}

	//Constructor
	public LoginUI(){

		userController = new UserController();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400,500));
		this.setBackground(new Color(139,217,169));

		loginButton = new JButton();
		loginButton.setBounds(150,300,100,35);
		loginButton.setBackground(new Color(214,72,105));
		loginButton.setForeground(new Color(51,255,255));
		loginButton.setEnabled(true);
		loginButton.setFont(new Font("SansSerif",0,20));
		loginButton.setText("Login");
		loginButton.setVisible(true);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] arrayPassword = password.getPassword();
				String id = studentId.getText();
				User user = userController.authenticate(id, arrayPassword);
				if (user!=null) {
					JPanel jPanel = null;
					switch (user.getRole()) {
						case 1: jPanel = new AdminUI();
						break;
						case 2: jPanel = new EventOrganiserUI();
						break;
						case 3: jPanel = new StudentUI();
						break;
					}
					SmallFrame.getjFrame().changePanel(jPanel);
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Wrong credentials",
							"Inane warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		noAccount = new JLabel();
		noAccount.setBounds(150,370,100,35);
		noAccount.setBackground(new Color(214,217,223));
		noAccount.setForeground(new Color(0,0,0));
		noAccount.setEnabled(true);
		noAccount.setFont(new Font("SansSerif",0,12));
		noAccount.setText("No account?");
		noAccount.setVisible(true);
		noAccount.setHorizontalAlignment(SwingConstants.CENTER);

		password = new JPasswordField();
		password.setBounds(100,200,200,35);
		password.setBackground(new Color(214,217,223));
		password.setForeground(new Color(0,0,0));
		password.setEnabled(true);
		password.setFont(new Font("sansserif",0,12));
		password.setText("Password");
		password.setEchoChar((char)0);
		password.setVisible(true);
		password.addFocusListener(
				new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
						char[] inputArray = password.getPassword();
						if(Arrays.equals(inputArray, passwordArray)) {
							password.setText("");
							password.setEchoChar('*');
						}
					}

					@Override
					public void focusLost(FocusEvent e) {
						char[] inputArray = password.getPassword();
						char[] emptyArray = new char[0];
						if(Arrays.equals(inputArray, emptyArray)) {
							password.setEchoChar((char)0);
							password.setText("Password");
						}
					}
				}
		);

		register = new JButton();
		register.setBounds(150,400,100,30);
		register.setBackground(new Color(204, 92, 189));
		register.setForeground(new Color(0,0,0));
		register.setEnabled(true);
		register.setFont(new Font("SansSerif",0,10));
		register.setText("Register");
		register.setVisible(true);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterUI registerUI = new RegisterUI();
				SmallFrame.getjFrame().changePanel(registerUI);
			}
		});

		studentId = new JTextField();
		studentId.setBounds(100,100,200,35);
		studentId.setBackground(new Color(255,255,255));
		studentId.setForeground(new Color(0,0,0));
		studentId.setEnabled(true);
		studentId.setFont(new Font("sansserif",0,12));
		studentId.setText("Student ID");
		studentId.setVisible(true);
		studentId.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				placeholderDisappear(e);
			}

			@Override
			public void focusLost(FocusEvent e) {
				placeholderAppear(e);
			}
		});

		//adding components to contentPane panel
		this.add(loginButton);
		this.add(noAccount);
		this.add(password);
		this.add(register);
		this.add(studentId);
	}

	private void placeholderDisappear(AWTEvent event) {
		if (studentId.getText().equals("Student ID")) {
			studentId.setText("");
		}
	}

	private void placeholderAppear(AWTEvent event) {
		if (studentId.getText().equals("")) {
			studentId.setText("Student ID");
		}
	}
}