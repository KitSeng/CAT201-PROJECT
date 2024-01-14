// Import necessary packages and classes
package HotelManagementSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddEmployee extends JFrame{ //Third Frame
    // Labels for employee details
    JLabel Name, IC, Age, Phone, Gender, Email, Address, Job, Salary, AddEmployee;

    // TextFields for user input
    JTextField TFName, TFIC, TFAge, TFPhone, TFEmail, TFAddress, TFSalary;

    // RadioButtons for gender selection
    JRadioButton RBMale, RBFemale;

    // ButtonGroup for grouping RadioButtons
    ButtonGroup BG;

    // Create button
    JButton Submit, btnBack;

    // ComboBox for job selection
    JComboBox CBJob;

    // Constructor for AddEmployee class
    public AddEmployee() {

        // Set frame properties
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setLayout(null);

        // Background panel with an image
        JPanel background = new JPanel() {
            protected void paintComponent(Graphics gp) {
                super.paintComponent(gp);
                ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("icons/AddEmployee01.jpg"));
                Image image = backgroundImage.getImage();
                gp.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(null);
        setContentPane(background);

        // Labels and TextFields for employee details
        Name = new JLabel("<html>NAME <font color='red'>*</font></html>");
        Name.setFont(new Font("sans serif", Font.PLAIN, 17));
        Name.setBounds(60, 30, 150, 27);
        add(Name);

        TFName = new JTextField();
        TFName.setBounds(200, 30, 150, 27);
        add(TFName);

        IC = new JLabel("<html>IC <font color='red'>*</font></html>");
        IC.setFont(new Font("sans serif", Font.PLAIN, 17));
        IC.setBounds(60, 74, 150, 27);
        add(IC);

        TFIC = new JTextField();
        TFIC.setBounds(200, 74, 150, 27);
        add(TFIC);

        Age = new JLabel("<html>AGE <font color='red'>*</font></html>");
        Age.setFont(new Font("sans serif", Font.PLAIN, 17));
        Age.setBounds(60, 118, 150, 27);
        add(Age);

        TFAge = new JTextField();
        TFAge.setBounds(200, 118, 150, 27);
        add(TFAge);

        Phone = new JLabel("<html>PHONE <font color='red'>*</font></html>");
        Phone.setFont(new Font("sans serif", Font.PLAIN, 17));
        Phone.setBounds(60, 162, 150, 27);
        add(Phone);

        TFPhone = new JTextField();
        TFPhone.setBounds(200, 162, 150, 27);
        add(TFPhone);

        // RadioButtons and ButtonGroup for gender selection
        Gender = new JLabel("<html>GENDER <font color='red'>*</font></html>");
        Gender.setFont(new Font("sans serif", Font.PLAIN, 17));
        Gender.setBounds(60, 206, 150, 27);
        add(Gender);

        RBMale = new JRadioButton("MALE");
        RBMale.setOpaque(false);
        RBMale.setBounds(200, 206, 70, 27);
        add(RBMale);

        RBFemale = new JRadioButton("FEMALE");
        RBFemale.setOpaque(false);
        RBFemale.setBounds(280, 206, 80, 27);
        add(RBFemale);

        BG = new ButtonGroup();
        BG.add(RBMale);
        BG.add(RBFemale);

        Email = new JLabel("<html>EMAIL <font color='red'>*</font></html>");
        Email.setFont(new Font("sans serif", Font.PLAIN, 17));
        Email.setBounds(60, 250, 150, 27);
        add(Email);

        TFEmail = new JTextField();
        TFEmail.setBounds(200, 250, 150, 27);
        add(TFEmail);

        Address = new JLabel("<html>ADDRESS <font color='red'>*</font></html>");
        Address.setFont(new Font("sans serif", Font.PLAIN, 17));
        Address.setBounds(60, 294, 150, 27);
        add(Address);

        TFAddress = new JTextField();
        TFAddress.setBounds(200, 294, 150, 27);
        add(TFAddress);

        // ComboBox for job selection
        Job = new JLabel("<html>JOB <font color='red'>*</font></html>");
        Job.setFont(new Font("sans serif", Font.PLAIN, 17));
        Job.setBounds(60, 338, 150, 27);
        add(Job);

        String course[] = {"Choose a job", "Manager", "Accountant", "Front Desk Clerks", "Porters", "Housekeeping", "Room Service", "Waiter/Waitress", "Chef", "Kitchen Staff", "Maintenance Staff",  "Security Officer"};
        CBJob = new JComboBox(course);
        CBJob.setBackground(Color.WHITE);
        CBJob.setBounds(200,338,150,30);
        add(CBJob);

        Salary = new JLabel("<html>SALARY (RM) <font color='red'>*</font></html>");
        Salary.setFont(new Font("sans serif", Font.PLAIN, 17));
        Salary.setBounds(60, 385, 150, 27);
        add(Salary);

        TFSalary = new JTextField();
        TFSalary.setBounds(200, 385, 150, 27);
        add(TFSalary);

        // Button for subnmitting employee details
        Submit = new JButton("SUBMIT");
        Submit.setBounds(200, 435, 150, 30);
        Submit.setBackground(new Color(202, 221, 239,255));
        Submit.setForeground(Color.BLACK);
        add(Submit);

        // Back button
        btnBack = new JButton("BACK");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setBounds(695, 510, 150, 30); // Adjusted bounds
        btnBack.setBackground(new Color(201, 220, 238,255));
        btnBack.setForeground(Color.BLACK);
        add(btnBack);

        setVisible(true);

        AddEmployee = new JLabel("ADD EMPLOYEE DETAILS");
        AddEmployee.setForeground(Color.DARK_GRAY);
        AddEmployee.setFont(new Font("sans serif", Font.PLAIN, 31));
        AddEmployee.setBounds(450, 24, 442, 35);
        add(AddEmployee);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/AddEmployee02.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 300,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(405,55,480,410);
        add(image);

        Submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String name = TFName.getText();
                String ic = TFIC.getText();
                String age = TFAge.getText();
                String phone = TFPhone.getText();

                String gender = null;

                if(RBMale.isSelected()){
                    gender = "Male";

                }else if(RBFemale.isSelected()){
                    gender = "Female";
                }

                String email = TFEmail.getText();
                String address = TFAddress.getText();
                String salary = TFSalary.getText();
                String job = (String)CBJob.getSelectedItem();

                try {
                    conn c = new conn();

                    if (name.equals("")){
                        JOptionPane.showMessageDialog(null, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (ic.equals("")) {
                        JOptionPane.showMessageDialog(null, "IC cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (ic.length() != 12) {
                        JOptionPane.showMessageDialog(null, "IC must be 12 digits", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (age.equals("")) {
                        JOptionPane.showMessageDialog(null, "Age cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (age.length()>2) {
                        JOptionPane.showMessageDialog(null, "Invalid age", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (phone.equals("")) {
                        JOptionPane.showMessageDialog(null, "Phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (phone.length()<9 || phone.length()>11) {
                        JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (!RBMale.isSelected() && !RBFemale.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (email.equals("")){
                        JOptionPane.showMessageDialog(null, "Email cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(!email.contains("@") || !email.contains(".com")){
                        JOptionPane.showMessageDialog(null, "Invalid email", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (address.equals("")){
                        JOptionPane.showMessageDialog(null, "Address cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (job.equals("Choose a job")) {
                        JOptionPane.showMessageDialog(null, "Please choose a job", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (salary.equals("")){
                        JOptionPane.showMessageDialog(null, "Salary cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        String data = "INSERT INTO employee values( '" + name + "', '" + ic + "', '" + age + "', '" + phone + "','" + gender + "', '" + email + "', '" + address + "','" + job + "', '" + salary + "')";

                        c.s.executeUpdate(data);
                        JOptionPane.showMessageDialog(null, "Employee Added Successfully");
                        setVisible(false);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setVisible(true);
        setSize(900,600);
        setLocation(200,50);

    }

    public static void main(String[] args){
        new AddEmployee();
    }
}