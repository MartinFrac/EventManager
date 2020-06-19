package com.eet.ui.views;

import com.eet.controllers.UserController;
import com.eet.models.User;
import com.eet.ui.*;
import com.eet.ui.Frame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

import static com.eet.ui.Utility.*;


public class LoginUI extends JPanel {

	private JButton loginButton;
	private JLabel noAccount;
	private JPasswordField password;
	private JButton register;
	private JTextField studentId;
	private UserController userController;

	public static final String STUDENT_ID = "Student ID";
	public static final String PASSWORD = "Password";
	public static final char[] PASSWORD_ARRAY = new char[]{'P','a','s','s','w','o','r','d'};

	private static final Color buttonBackground = new Color(214,72,105);
	private static final Color buttonForeground = new Color(51,255,255);
	private static final Font buttonFont = new Font("SansSerif",0,20);

	public JButton getLoginButton() {
		return loginButton;
	}

	//Constructor
	public LoginUI(){

		userController = new UserController();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(400,500));
		this.setBackground(new Color(139,217,169));

		ActionListener loginActionListener = e -> {
			char[] arrayPassword = password.getPassword();
			String id = studentId.getText();
			User user = Utility.validateAuthorisation(id, arrayPassword, userController);
			if (user != null) {
				Utility.changeToUsersView(user.getRole());
			}
		};

		loginButton = new ButtonBuilder("Login")
				.withBounds(150, 300, 100, 35)
				.withBackground(buttonBackground)
				.withForeground(buttonForeground)
				.withFont(buttonFont)
				.withActionListener(loginActionListener)
				.build();

		noAccount = new LabelBuilder("No account?")
				.withBounds(150, 370, 100, 35)
				.withBackground(new Color(214,217,223))
				.withForeground(new Color(0,0,0))
				.withFont(new Font("SansSerif",0,12))
				.build();
		noAccount.setHorizontalAlignment(SwingConstants.CENTER);

		password = new JPasswordField();
		password.setBounds(100,200,200,35);
		password.setBackground(new Color(214,217,223));
		password.setForeground(new Color(0,0,0));
		password.setEnabled(true);
		password.setFont(new Font("sansserif",0,12));
		password.setText(PASSWORD);
		password.setEchoChar((char)0);
		password.setVisible(true);
		password.addFocusListener(
				new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
						char[] inputArray = password.getPassword();
						if(Arrays.equals(inputArray, PASSWORD_ARRAY)) {
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
							password.setText(PASSWORD);
						}
					}
				}
		);

		ActionListener registerActionListener = e -> {
			RegisterUI registerUI = new RegisterUI();
			Frames.getJFrame(Frame.Small).changePanel(registerUI);
		};

		register = new ButtonBuilder("Register")
				.withBounds(150, 400, 100, 30)
				.withBackground(new Color(204, 92, 189))
				.withForeground(new Color(0,0,0))
				.withFont(new Font("SansSerif",0,10))
				.withActionListener(registerActionListener)
				.build();

		studentId = new TextFieldBuilder(STUDENT_ID)
				.withBounds(100,100, 200, 35)
				.withBackground(new Color(255,255,255))
				.withForeground(new Color(0,0,0))
				.withFont(new Font("sansserif",0,12))
				.build();
		studentId.addFocusListener(getPlaceholder(STUDENT_ID));

		//adding components to contentPane panel
		this.add(loginButton);
		this.add(noAccount);
		this.add(password);
		this.add(register);
		this.add(studentId);
	}
}