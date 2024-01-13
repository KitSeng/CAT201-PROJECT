// Import necessary packages and classes
package HotelManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableCellRenderer;

public class Employee extends JFrame {
	// Panel to hold the components
	JPanel contentPane;

	// Table to display employee data
	JTable table;

	// Buttons for navigation and actions
	JButton btnBack, btnSave, btnShowInfo, btnDelete, btnSearch, btnShowAll;

	// Add a JTextField for search input
	JTextField txtSearch;

	// Labels for column headers
	JLabel LblName, LblIC, LblAge, LblPhone, LblGender, LblEmail, LblAddress, LblJob, LblSalary;

	String role;

	// Main method to launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee("receptionist");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Method to close the current frame
	public void close() {
		this.dispose();
	}

	// Constructor for the Employee class
	public Employee(String role) throws SQLException {
		this.role = role;
		// Set frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 200, 900, 600);
		setTitle("EMPLOYEE INFORMATION");

		// Create and set properties for the content pane
		contentPane = new JPanel() {
			// Override paintComponent to set background image
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Image image = new ImageIcon(ClassLoader.getSystemResource("icons/Employee02.jpeg")).getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); // Remove layout employee

		// Create a table to display employee data
		table = new JTable();
		// Set the background color of the table to a transparent color
		table.setBackground(new Color(0, 0, 0, 0));
		table.setBounds(0, 175, 885, 400); // Adjusted bounds
		table.setRowHeight(30);
		contentPane.add(table);

		// Load employee data into the table
		loadManagerData();

		// Labels for column headers
		LblName = new JLabel("Name");
		LblName.setFont(new Font("sans serif", Font.BOLD, 14));
		LblName.setBounds(30, 151, 80, 14);
		contentPane.add(LblName);

		LblIC = new JLabel("IC");
		LblIC.setFont(new Font("sans serif", Font.BOLD, 14));
		LblIC.setBounds(140, 151, 80, 14);
		contentPane.add(LblIC);

		LblAge = new JLabel("Age");
		LblAge.setFont(new Font("sans serif", Font.BOLD, 14));
		LblAge.setBounds(230, 150, 80, 17);
		contentPane.add(LblAge);

		LblPhone = new JLabel("Phone");
		LblPhone.setFont(new Font("sans serif", Font.BOLD, 14));
		LblPhone.setBounds(320, 151, 80, 14);
		contentPane.add(LblPhone);

		LblGender = new JLabel("Gender");
		LblGender.setFont(new Font("sans serif", Font.BOLD, 14));
		LblGender.setBounds(415, 151, 80, 14);
		contentPane.add(LblGender);

		LblEmail = new JLabel("Email");
		LblEmail.setFont(new Font("sans serif", Font.BOLD, 14));
		LblEmail.setBounds(520, 151, 80, 14);
		contentPane.add(LblEmail);

		LblAddress = new JLabel("Address");
		LblAddress.setFont(new Font("sans serif", Font.BOLD, 14));
		LblAddress.setBounds(608, 151, 80, 14);
		contentPane.add(LblAddress);

		LblJob = new JLabel("Job");
		LblJob.setFont(new Font("sans serif", Font.BOLD, 14));
		LblJob.setBounds(722, 151, 80, 14);
		contentPane.add(LblJob);

		LblSalary = new JLabel("Salary (RM)");
		LblSalary.setFont(new Font("sans serif", Font.BOLD, 14));
		LblSalary.setBounds(795, 151, 80, 16);
		contentPane.add(LblSalary);

		// Create a panel for the title section and set its background color
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0,0,0,0));
		titlePanel.setBounds(210, 0, 900, 400); // Set bounds for the title section
		titlePanel.setLayout(null);
		contentPane.add(titlePanel);

		// Create a label for the title and set its properties
		JLabel titleLabel = new JLabel("EMPLOYEE INFORMATION");
		titleLabel.setForeground(Color.BLACK); // Set text color to white
		titleLabel.setFont(new Font("sans serif", Font.BOLD, 30));
		titleLabel.setBounds(50, 30, 500, 30); // Adjust the x, y, width, and height as needed
		titlePanel.add(titleLabel);

		// Create a JPanel for the colored region
		JPanel coloredRegionPanel = new JPanel();
		coloredRegionPanel.setBounds(0,145,885,30);
		coloredRegionPanel.setBackground(new Color(142, 192, 239,255));

		// Add the colored region panel to the contentPane
		contentPane.add(coloredRegionPanel);

		// Buttons for actions and navigation
		btnSave = new JButton("Save Changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		btnSave.setBounds(200, 510, 150, 30); // Adjusted bounds
		btnSave.setBackground(new Color(218, 229, 240,255));
		btnSave.setForeground(Color.BLACK);
		contentPane.add(btnSave);

		// Image icon button
		ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Employee01.png"));
		Image image = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);

		btnShowInfo = new JButton(scaledIcon);
		btnShowInfo.addActionListener(e -> showInfoPopup());
		btnShowInfo.setBounds(175, 515, 20, 20);
		btnShowInfo.setOpaque(false);
		btnShowInfo.setContentAreaFilled(false);
		btnShowInfo.setBorderPainted(false);
		contentPane.add(btnShowInfo);

		// Back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
				setVisible(false);
			}
		});
//		btnBack.setBounds(560, 510, 150, 30); // Adjusted bounds
		btnBack.setBackground(new Color(218, 229, 240,255));
		btnBack.setForeground(Color.BLACK);
		contentPane.add(btnBack);

		// Create a JTextField for search input
		txtSearch = new JTextField();
		txtSearch.setBounds(20, 100, 150, 30);
		contentPane.add(txtSearch);

		// Create a "Search" button
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchManagerData(txtSearch.getText());
			}
		});
		btnSearch.setBounds(180, 100, 80, 30);
		contentPane.add(btnSearch);

		// Create a "Show All" button
		btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllManagerData();
			}
		});
		btnShowAll.setBounds(715, 100, 150, 30);
		btnShowAll.setBackground(new Color(218, 229, 240,255));
		btnShowAll.setForeground(Color.BLACK);
		btnShowAll.setEnabled(false);
		contentPane.add(btnShowAll);

		// Buttons for actions and navigation
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRow();
			}
		});
		btnDelete.setBounds(380, 510, 150, 30); // Adjusted bounds
		btnDelete.setBackground(new Color(218, 229, 240,255));
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setEnabled(false); // Initially disabled until a row is selected
		contentPane.add(btnDelete);

		// Check the user role and adjust button visibility
		if (this.role.equals("receptionist")) {
			// If the user is a receptionist, hide the buttons
			btnSave.setVisible(false);
			btnDelete.setVisible(false);
			btnShowInfo.setVisible(false);
			btnBack.setBounds(700, 510, 150, 30);
		}
		else if (this.role.equals("admin")) {
			// If the user is an admin, show the buttons
			btnSave.setVisible(true);
			btnDelete.setVisible(true);
			btnShowInfo.setVisible(true);
			btnBack.setBounds(560, 510, 150, 30);
		}

		// Set frame visibility, location, and size
		setVisible(true);
		setLocation(200, 50);
		setSize(900, 600);
	}

	// Method to load employee data into the table
	private void loadManagerData() {
		try {
			conn c = new conn();
			String displayCustomersql = "select * from employee WHERE job !='Manager'";
			ResultSet rs = c.s.executeQuery(displayCustomersql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			// Center-align the data in each column
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
			table.getSelectionModel().addListSelectionListener(e -> btnDelete.setEnabled(true));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	// Method to save changes made to the employee data
	private void saveChanges() {
		try {
			conn c = new conn();
			int rowCount = table.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				String Name = (String) table.getValueAt(i, 0);
				String Ic = (String) table.getValueAt(i, 1);
				String Age = (String) table.getValueAt(i, 2);
				String Phone = (String) table.getValueAt(i, 3);
				String Gender = (String) table.getValueAt(i, 4);
				String Email = (String) table.getValueAt(i, 5);
				String Address = (String) table.getValueAt(i, 6);
				String Job = (String) table.getValueAt(i, 7);
				String Salary = (String) table.getValueAt(i, 8);

				// Update the employee information in the database
				String updateQuery = "UPDATE employee SET ic = '" + Ic + "', age = '" + Age + "', phone = '" + Phone + "', gender = '" + Gender + "', email = '" + Email + "', address = '" + Address + "', job = '" + Job + "', salary = '" + Salary + "' WHERE name = '" + Name + "'";
				c.s.executeUpdate(updateQuery);
			}

			// Remove the existing table
			contentPane.remove(table);

			// Create a new table and set its properties
			table = new JTable();
			table.setBackground(new Color(0, 0, 0, 0));
			table.setBounds(0, 175, 885, 400);
			table.setRowHeight(30);
			contentPane.add(table);

			// Reload the data after saving changes
			String displayEmployeesql = "SELECT * FROM employee WHERE job !='Manager'";
			ResultSet rs = c.s.executeQuery(displayEmployeesql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Repaint the content pane
			contentPane.repaint();

			JOptionPane.showMessageDialog(this, "Changes saved successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error saving changes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	// Method to show information about editing steps
	private void showInfoPopup() {
		String message = "<html><b>To edit the info:</b><br>" +
				"1. Double-click the cell<br>" +
				"2. Make the changes<br>" +
				"3. Click ENTER<br>" +
				"4. Click 'Save Changes'</html>";

		JOptionPane.showMessageDialog(this, message, "Editing Steps", JOptionPane.INFORMATION_MESSAGE);
	}

	// Method to delete the selected row
	private void deleteRow() {
		try {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure to delete this employee information?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					String Name = (String) table.getValueAt(selectedRow, 0);

					// Delete the selected row from the database
					String deleteQuery = "DELETE FROM employee WHERE name = '" + Name + "'";
					conn c = new conn();
					c.s.executeUpdate(deleteQuery);

					// Remove the existing table
					contentPane.remove(table);

					// Create a new table and set its properties
					table = new JTable();
					table.setBackground(new Color(0, 0, 0, 0));
					table.setBounds(0, 175, 885, 400);
					table.setRowHeight(30);
					contentPane.add(table);

					// Reload the data after deletion
					loadManagerData();

					// Repaint the content pane
					contentPane.repaint();

					JOptionPane.showMessageDialog(this, "Deleted successfully!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error deleting row: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Method to search for employee data based on the entered value
	private void searchManagerData(String searchValue) {
		try {
			conn c = new conn();

			// Remove the existing table
			contentPane.remove(table);

			// Create a new table and set its properties
			table = new JTable();
			table.setBackground(new Color(0, 0, 0, 0));
			table.setBounds(0, 175, 885, 400);
			table.setRowHeight(30);
			contentPane.add(table);

			String searchQuery = "SELECT * FROM employee WHERE job !='Manager' AND (name LIKE '%" + searchValue + "%' OR ic LIKE '%" + searchValue + "%' OR age LIKE '%" + searchValue + "%' OR phone LIKE '%" + searchValue + "%' OR gender LIKE '%" + searchValue + "%' OR job LIKE '%" + searchValue + "%' OR salary LIKE '%" + searchValue + "%')";
			ResultSet rs = c.s.executeQuery(searchQuery);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Enable or disable the "Show All" button based on whether a search was performed
			btnShowAll.setEnabled(!searchValue.isEmpty());

			// Repaint the content pane
			contentPane.repaint();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error searching data: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Method to show all employee data
	private void showAllManagerData() {
		try {
			conn c = new conn();

			// Remove the existing table
			contentPane.remove(table);

			// Create a new table and set its properties
			table = new JTable();
			table.setBackground(new Color(0, 0, 0, 0));
			table.setBounds(0, 175, 885, 400);
			table.setRowHeight(30);
			contentPane.add(table);

			// Load all employee data into the new table
			loadManagerData();

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}

			// Disable the "Show All" button after showing all data
			btnShowAll.setEnabled(false);

			// Repaint the content pane
			contentPane.repaint();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error showing all data: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
