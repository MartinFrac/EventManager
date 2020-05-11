package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.memory.ActiveUser;
import com.eet.ui.ButtonEditor;
import com.eet.ui.ButtonRenderer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewBookingsUI extends JPanel {

	private JButton goBack;
	private JLabel searchEvent;
	private JTextField searchTitle;
	private JTextField searchByKeyword;
	private JTextField searchByLocation;
	private JTextField searchByDate;
	private JTextField searchBySpaceLimit;
	private JButton searchButton;
	private JButton filterButton;
	private DefaultTableModel model;
	private EventController eventController;

	public JButton getGoBack() {
		return goBack;
	}

	//Constructor
	public ViewBookingsUI(){

		eventController = new EventController();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(new Color(192,192,192));

		filterButton = new JButton();
		filterButton.setBounds(360, 425, 140, 85);
		filterButton.setBackground(new Color(214, 217, 223));
		filterButton.setForeground(new Color(0,0,0));
		filterButton.setEnabled(true);
		filterButton.setFont(new Font("sansserif", 0, 18));
		filterButton.setText("Filter");
		filterButton.setVisible(true);
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		goBack = new JButton();
		goBack.setBounds(650,425,140,85);
		goBack.setBackground(new Color(214,217,223));
		goBack.setForeground(new Color(0,0,0));
		goBack.setEnabled(true);
		goBack.setFont(new Font("sansserif",0,18));
		goBack.setText("Go back");
		goBack.setVisible(true);
		goBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

			}
		});

		searchEvent = new JLabel();
		searchEvent.setBounds(5,5,90,35);
		searchEvent.setBackground(new Color(214,217,223));
		searchEvent.setForeground(new Color(0,0,0));
		searchEvent.setEnabled(true);
		searchEvent.setFont(new Font("sansserif",0,12));
		searchEvent.setText("Search event");
		searchEvent.setVisible(true);

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
				String name = searchTitle.getText();
				Object[][] data = eventController.getBookings(ActiveUser.getUser().getId(), name);
				updateData(data);
				model.fireTableDataChanged();
			}
		});

		searchTitle = textFieldGenerator(50,40,180,35, "Name of the event");
		searchTitle.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchTitle.getText().equals("Name of the event")) {
					searchTitle.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTitle.getText().equals("")) {
					searchTitle.setText("Name of the event");
				}
			}
		});

		searchByKeyword = textFieldGenerator(12,475,150,35, "Keyword");
		searchByKeyword.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchByKeyword.getText().equals("Keyword")) {
					searchByKeyword.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchByKeyword.getText().equals("")) {
					searchByKeyword.setText("Keyword");
				}
			}
		});

		searchByLocation = textFieldGenerator(12,425,150,35, "Location");
		searchByLocation.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchByLocation.getText().equals("Location")) {
					searchByLocation.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchByLocation.getText().equals("")) {
					searchByLocation.setText("Location");
				}
			}
		});

		searchByDate = textFieldGenerator(180,425,150,35, "Date");
		searchByDate.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchByDate.getText().equals("Date")) {
					searchByDate.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchByDate.getText().equals("")) {
					searchByDate.setText("Date");
				}
			}
		});

		searchBySpaceLimit = textFieldGenerator(180,475,150,35, "Space limit");
		searchBySpaceLimit.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchBySpaceLimit.getText().equals("Space limit")) {
					searchBySpaceLimit.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchBySpaceLimit.getText().equals("")) {
					searchBySpaceLimit.setText("Space limit");
				}
			}
		});

		String[] columnNames = {"Title",
				"Type",
				"Description",
				"Start Date",
				"Duration",
				"Spaces",
				"Place",
				"Click to cancel"
		};

		Object[][] data = eventController.getBookings(ActiveUser.getUser().getId());

		JTable table = new JTable();
		table.setRowHeight(40);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		updateData(data);
		table.setModel(model);
		table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
		table.getColumnModel().getColumn(1).setMaxWidth(70);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(3).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		table.getColumnModel().getColumn(5).setMaxWidth(70);
		table.getColumnModel().getColumn(7).setMaxWidth(100);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 900, 300);



		//adding components to contentPane panel
		this.add(goBack);
		this.add(filterButton);
		this.add(searchButton);
		this.add(searchEvent);
		this.add(searchTitle);
		this.add(searchByKeyword);
		this.add(searchByLocation);
		this.add(searchByDate);
		this.add(searchBySpaceLimit);
		this.add(scrollPane);
	}

	private void updateData(Object[][] data) {
		model.getDataVector().removeAllElements();
		if(data!=null) {
			for(int i = 0; i < data.length; i++) {
				model.addRow(data[i]);
			}
		}
		model.fireTableDataChanged();
	}

	private static JTextField textFieldGenerator(int x, int y, int width, int height, String text) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.setBackground(Color.white);
		textField.setForeground(Color.black);
		textField.setEnabled(true);
		textField.setFont(new Font("sansserif",0,12));
		textField.setText(text);
		textField.setVisible(true);
		return textField;
	}
}