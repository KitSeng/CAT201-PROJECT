package HotelManagementSystem;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Employee extends JFrame {
	// Panel to hold the components
	JPanel contentPane;

	// Table to display employee data
	JTable table;

	// Buttons for navigation and actions
	JButton btnBack, btnSave, btnShowInfo;

	// Labels for column headers
	JLabel LblName, LblIC, LblAge, LblPhone, LblGender, LblEmail, LblAddress, LblJob, LblSalary;

	// Main method to launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() throws SQLException {
		// Set frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 200, 1000, 600);
		setTitle("EMPLOYEE INFORMATION");

		// Create and set properties for the content pane
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create a table to display employee data
		table = new JTable();
		table.setBounds(0, 34, 900, 450);
		contentPane.add(table);

		// Load employee data into the table
		loadEmployeeData();

		// Labels for column headers
		LblName = new JLabel("Name");
		LblName.setFont(new Font("sans serif", Font.BOLD, 12));
		LblName.setBounds(30, 11, 46, 14);
		contentPane.add(LblName);

		LblIC = new JLabel("IC");
		LblIC.setFont(new Font("sans serif", Font.BOLD, 12));
		LblIC.setBounds(140, 11, 86, 14);
		contentPane.add(LblIC);

		LblAge = new JLabel("Age");
		LblAge.setFont(new Font("sans serif", Font.BOLD, 12));
		LblAge.setBounds(235, 11, 46, 14);
		contentPane.add(LblAge);

		LblPhone = new JLabel("Phone");
		LblPhone.setFont(new Font("sans serif", Font.BOLD, 12));
		LblPhone.setBounds(328, 11, 86, 14);
		contentPane.add(LblPhone);

		LblGender = new JLabel("Gender");
		LblGender.setFont(new Font("sans serif", Font.BOLD, 12));
		LblGender.setBounds(425, 11, 46, 14);
		contentPane.add(LblGender);

		LblEmail = new JLabel("Email");
		LblEmail.setFont(new Font("sans serif", Font.BOLD, 12));
		LblEmail.setBounds(530, 11, 86, 14);
		contentPane.add(LblEmail);

		LblAddress = new JLabel("Address");
		LblAddress.setFont(new Font("sans serif", Font.BOLD, 12));
		LblAddress.setBounds(620, 11, 86, 14);
		contentPane.add(LblAddress);

		LblJob = new JLabel("Job");
		LblJob.setFont(new Font("sans serif", Font.BOLD, 12));
		LblJob.setBounds(735, 11, 86, 14);
		contentPane.add(LblJob);

		LblSalary = new JLabel("Salary (RM)");
		LblSalary.setFont(new Font("sans serif", Font.BOLD, 12));
		LblSalary.setBounds(810, 11, 86, 14);
		contentPane.add(LblSalary);

		// Buttons for actions and navigation
		btnSave = new JButton("Save Changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveChanges();
			}
		});
		btnSave.setBounds(280, 510, 150, 30);
		btnSave.setBackground(Color.GRAY);
		btnSave.setForeground(Color.WHITE);
		contentPane.add(btnSave);

		// Image icon button
		ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/Employee01.png"));
		Image image = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);

		btnShowInfo = new JButton(scaledIcon);
		btnShowInfo.addActionListener(e -> showInfoPopup());
		btnShowInfo.setBounds(250, 510, 30, 30);
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
		btnBack.setBounds(480, 510, 120, 30);
		btnBack.setBackground(Color.GRAY);
		btnBack.setForeground(Color.WHITE);
		contentPane.add(btnBack);

		// Set frame visibility, location, and size
		setVisible(true);
		setLocation(200, 50);
		setSize(900, 600);
	}

	// Method to load employee data into the table
	private void loadEmployeeData() {
		try {
			conn c = new conn();
			String displayCustomersql = "select * from employee";
			ResultSet rs = c.s.executeQuery(displayCustomersql);
			table.setModel(DbUtils.resultSetToTableModel(rs));
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

			// Reload the data after saving changes
			String displayDepartmentsql = "SELECT * FROM employee";
			ResultSet rs = c.s.executeQuery(displayDepartmentsql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

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
				"4. Click 'Save the changes'</html>";

		JOptionPane.showMessageDialog(this, message, "Editing Steps", JOptionPane.INFORMATION_MESSAGE);
	}
}
