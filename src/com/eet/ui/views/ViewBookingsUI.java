package com.eet.ui.views;

import com.eet.controllers.EventController;
import com.eet.memory.ActiveUser;
import com.eet.models.Filters;
import com.eet.models.Type;
import com.eet.ui.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewBookingsUI extends JPanel {

	private static final String TITLE = "Name of the event";
	private static final String PLACE = "Place";
	private static final String AVAILABLE_SPACES = "Available spaces";
	private static final String KEYWORDS = "Keywords";

	private JButton goBackButton;
	private JLabel searchEventLabel;
	private JTextField titleTextField;
	private JTextField keywordsTextField;
	private JTextField locationTextField;
	private JTextField availableSpacesTextField;
	private JButton searchButton;
	private JButton repeatableButton;
	private JButton filterButton;
	private JTable table;
	private DefaultTableModel model;
	private JDatePickerImpl datePickerStart;
	private JDatePickerImpl datePickerEnd;
	private JComboBox<String> eventTypesComboBox;

	private EventController eventController;

	public JButton getGoBackButton() {
		return goBackButton;
	}

	//Constructor
	public ViewBookingsUI(){

		eventController = new EventController();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(900,600));
		this.setBackground(new Color(192,192,192));

		datePickerStart = datePickerGenerator(15, 510, 150, 25);

		datePickerEnd = datePickerGenerator(185, 510, 150, 25);

		String[] options = {"None", "Online", "Physical"};
		eventTypesComboBox = new JComboBox<>(options);
		eventTypesComboBox.setBounds(355, 510, 150, 25);

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
					String title = titleTextField.getText();
					String place = locationTextField.getText();
					String availableSpaces = availableSpacesTextField.getText();
					String keywords = keywordsTextField.getText();
					Date startDate = (Date) datePickerStart.getModel().getValue();
					Date endDate = (Date) datePickerEnd.getModel().getValue();
					String type = (String) eventTypesComboBox.getSelectedItem();
					map.put("title", title);
					map.put("place", place);
					map.put("availableSpaces", availableSpaces);
					map.put("keywords", keywords);
					map.put("type", type);

					Filters filters = validateFilters(map, startDate, endDate);
					if (filters==null) {
						System.out.println("wrong filters");
					} else {
						Object[][] data;
						if (repeatableButton.getText().equals("Repeatable Events")) {
							data = eventController.getNonRepeatableBookingsWithFilters(ActiveUser.getUser().getId(), filters);
						} else {
							data = eventController.getRepeatableBookingswithFilters(ActiveUser.getUser().getId(), filters);
						}
						updateData(data);
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
				ActiveUser.setUser(null);
				LoginUI loginUI = new LoginUI();
				new SmallFrame(loginUI, loginUI.getLoginButton());
				BigFrame.getjFrame().dispose();
			}
		});

		searchEventLabel = new JLabel();
		searchEventLabel.setBounds(5,5,90,35);
		searchEventLabel.setBackground(new Color(214,217,223));
		searchEventLabel.setForeground(new Color(0,0,0));
		searchEventLabel.setEnabled(true);
		searchEventLabel.setFont(new Font("sansserif",0,12));
		searchEventLabel.setText("Search event");
		searchEventLabel.setVisible(true);

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
				String userId = ActiveUser.getUser().getId();
				String name = titleTextField.getText();
				if (repeatableButton.getText().equals("Repeatable Events")) {
					data = eventController.getNonRepeatableBookings(userId, name);
				} else {
					data = eventController.getRepeatableBookings(userId, name);
				}
				updateData(data);
			}
		});

		repeatableButton = new JButton();
		repeatableButton.setBounds(380, 40, 180, 35);
		repeatableButton.setBackground(new Color(214, 217, 223));
		repeatableButton.setForeground(new Color(0, 0, 0));
		repeatableButton.setEnabled(true);
		repeatableButton.setFont(new Font("sansserif", 0, 12));
		repeatableButton.setText("Repeatable Events");
		repeatableButton.setVisible(true);
		repeatableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatableButton.getText().equals("Repeatable Events")) {
					Object[][] data = eventController.getRepeatableBookings(ActiveUser.getUser().getId());
					table.getColumnModel().getColumn(3).setHeaderValue("Repetition");
					table.getTableHeader().repaint();
					updateData(data);
					repeatableButton.setText("One time Events");
					int[] widths = {150, 60, 110, 200, 60, 70, 150, 100, 0};
					for (int i = 0; i<widths.length; i++) {
						table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
						table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
					}
				}
				else {
					Object[][] data = eventController.getNonRepeatableBookings(ActiveUser.getUser().getId());
					table.getColumnModel().getColumn(3).setHeaderValue("Start Date");
					table.getTableHeader().repaint();
					updateData(data);
					repeatableButton.setText("Repeatable Events");
					int[] widths = {150, 60, 190, 120, 60, 70, 150, 100, 0};
					for (int i = 0; i<widths.length; i++) {
						table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
						table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
					}
				}
			}
		});

		titleTextField = textFieldGenerator(50,40,180,35, TITLE);
		titleTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (titleTextField.getText().equals(TITLE)) {
					titleTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (titleTextField.getText().equals("")) {
					titleTextField.setText(TITLE);
				}
			}
		});

		keywordsTextField = textFieldGenerator(355,460,150,35, KEYWORDS);
		keywordsTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (keywordsTextField.getText().equals(KEYWORDS)) {
					keywordsTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (keywordsTextField.getText().equals("")) {
					keywordsTextField.setText(KEYWORDS);
				}
			}
		});

		locationTextField = textFieldGenerator(15,460,150,35, PLACE);
		locationTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (locationTextField.getText().equals(PLACE)) {
					locationTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (locationTextField.getText().equals("")) {
					locationTextField.setText(PLACE);
				}
			}
		});

		availableSpacesTextField = textFieldGenerator(185,460,150,35, AVAILABLE_SPACES);
		availableSpacesTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (availableSpacesTextField.getText().equals(AVAILABLE_SPACES)) {
					availableSpacesTextField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (availableSpacesTextField.getText().equals("")) {
					availableSpacesTextField.setText(AVAILABLE_SPACES);
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
				"Click to cancel",
				"id"
		};

		Object[][] data = eventController.getNonRepeatableBookings(ActiveUser.getUser().getId());

		table = new JTable();
		table.setRowHeight(40);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		updateData(data);
		table.setModel(model);
		int[] widths = {150, 60, 190, 120, 60, 70, 150, 100, 0};
		for (int i = 0; i<widths.length; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(widths[i]);
			table.getColumnModel().getColumn(i).setMaxWidth(widths[i]);
		}
		table.getColumnModel().getColumn(7).setCellRenderer(new RendererAndEditor(table));
		table.getColumnModel().getColumn(7).setCellEditor(new RendererAndEditor(table));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() != 7) {
					Vector row = model.getDataVector().elementAt(table.getSelectedRow());
					EventDetailsUI eventDetailsUI = new EventDetailsUI();
					EventFrame eventFrame = EventFrame.getjFrame();
					if (eventFrame == null) {
						new EventFrame(eventDetailsUI, eventDetailsUI.getCreateButton(), row);
					} else {
						EventFrame.getjFrame().changePanel(eventDetailsUI, row);
					}
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 100, 900, 330);

		//adding components to contentPane panel
		this.add(goBackButton);
		this.add(filterButton);
		this.add(searchButton);
		this.add(repeatableButton);
		this.add(searchEventLabel);
		this.add(titleTextField);
		this.add(keywordsTextField);
		this.add(locationTextField);
		this.add(availableSpacesTextField);
		this.add(scrollPane);
		this.add(datePickerStart);
		this.add(datePickerEnd);
		this.add(eventTypesComboBox);
	}

	private Filters validateFilters(HashMap<String, String> map, Date startDate, Date endDate) {

		Filters filters = new Filters();

		String title = map.get("title");
		String type = map.get("type");
		String place = map.get("place");
		String keywords = map.get("keywords");
		String availableSpaces = map.get("availableSpaces");

		int availableSpacesInteger = -1;
		Timestamp startTimestamp = null;
		Timestamp endTimestamp = null;
		int intType = -1;

		for (String key: map.keySet()) {
			if (map.get(key).length()>=255) {
				JOptionPane.showMessageDialog(new JFrame(),
						"length of the value in: " + key + " exceeds 254 characters",
						"Inane warning",
						JOptionPane.WARNING_MESSAGE);
				return null;
			}
		}
		if (TITLE.equals(title)) {
			title = "";
		}
		if (PLACE.equals(place)) {
			place = "";
		}
		if (AVAILABLE_SPACES.equals(availableSpaces)) {
			availableSpaces = "-1";
		}
		if (KEYWORDS.equals(keywords)) {
			keywords = "";
		} else {
			String[] array = keywords.split(" ");
			if (array.length>1) {
				keywords = String.join("* OR ", array);
			} else {
				keywords = array[0] + "*";
			}
		}

		startTimestamp = dateToTimestamp(startDate);
		endTimestamp = dateToTimestamp(endDate);

		if ((startTimestamp == null && endTimestamp != null)
			|| (startTimestamp != null && endTimestamp ==null)) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Both dates need to be filled up",
					"Inane warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		if (!"None".equals(type)) {
			intType = type.equals("Online") ? 1 : 2;
		}
		try {
			availableSpacesInteger = Integer.parseInt(availableSpaces);
		} catch (NumberFormatException numberFormatException) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Value in the Available spaces field is not an Integer",
					"Inane warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		if (availableSpacesInteger<-1) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Value in the Available spaces field is negative",
					"Inane warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
		filters.setTitle(title);
		filters.setType(intType);
		filters.setPlace(place);
		filters.setKeywords(keywords);
		filters.setAvailableSpaces(availableSpacesInteger);
		filters.setStartDate(startTimestamp);
		filters.setEndDate(endTimestamp);

		return filters;
	}

	private Timestamp dateToTimestamp(Date date) {
		Timestamp timestamp = null;
		if (date!=null) {
			Calendar calendarStart = Calendar.getInstance();
			calendarStart.setTime(date);
			calendarStart.set(Calendar.HOUR_OF_DAY, 0);
			calendarStart.set(Calendar.MINUTE, 0);
			calendarStart.set(Calendar.MILLISECOND, 0);
			date = calendarStart.getTime();
			timestamp = new Timestamp(date.getTime());
		}
		return timestamp;
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

	private static JDatePickerImpl datePickerGenerator(int x, int y, int width, int height) {
		UtilDateModel utilDateModel = new UtilDateModel();
		JDatePanelImpl jDatePanel = new JDatePanelImpl(utilDateModel, new Properties());
		JDatePickerImpl datePicker = new JDatePickerImpl(jDatePanel, new DateLabelFormatter());
		datePicker.setBounds(x, y, width, height);
		return datePicker;
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