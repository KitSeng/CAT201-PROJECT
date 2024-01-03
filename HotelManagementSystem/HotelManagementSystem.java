package HotelManagementSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HotelManagementSystem extends JFrame implements ActionListener{

        JLabel label1, label2;
        JButton button1;

        public HotelManagementSystem() {
                setSize(900,600);
                setLayout(null);
                setLocation(100,100);

		        label1 = new JLabel("");
                label2 = new JLabel("");
                button1 = new JButton("");

                button1.setBackground(Color.WHITE);
                button1.setForeground(Color.BLACK);

                ImageIcon icon  = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
                Image getImage = icon.getImage().getScaledInstance(450,600, Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(getImage);
                label1 = new JLabel(image);
                
                JLabel text1 = new JLabel("F_F HOTEL MANAGEMENT SYSTEM");
                text1.setBounds(35,10,1500,100);
                text1.setFont(new Font("SansSerif",Font.BOLD,20));
                text1.setBackground(Color.LIGHT_GRAY);
                label2.add(text1);

                JLabel text2 = new JLabel(" Next");
                text2.setBounds(0,0,100,100);
                text2.setFont(new Font("SansSerif",Font.BOLD,15));
                text2.setForeground(Color.BLACK);
                button1.add(text2);

                button1.setBounds(750,500,80,30);
                label1.setBounds(0, 0, 450, 600);
                label2.setBounds(450, 0, 450, 600);

                add(button1);
		        add(label1);
                add(label2);

                button1.addActionListener(this);
                setVisible(true);

	    }
        
        public void actionPerformed(ActionEvent ae){
                new Login().setVisible(true);
                this.setVisible(false);
        }
        
        public static void main(String[] args) {
                HotelManagementSystem window = new HotelManagementSystem();
                window.setVisible(true);			
	    }
}
