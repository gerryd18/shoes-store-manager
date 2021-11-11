package Main;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

public class MainMenu extends JFrame implements ActionListener {
	JLabel titleLabel;
	JButton addShoeButton, manageShoeButton;
	Vector<Shoe> shoeVector;

	public MainMenu(Vector<Shoe> shoeVector) {
		setFrame();
		this.shoeVector = shoeVector;

		titleLabel = new JLabel("Shoe Store Manager");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(236, 82, 313, 84);
		titleLabel.setForeground(Color.decode("#B5FFD9"));
		getContentPane().add(titleLabel);

		addShoeButton = new JButton("Add Shoe");
		addShoeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addShoeButton.setBounds(262, 276, 262, 75);
		addShoeButton.setForeground(Color.decode("#B5FFD9"));
		addShoeButton.setBackground(Color.decode("#AE00FB"));
		addShoeButton.addActionListener(this);
		getContentPane().add(addShoeButton);

		manageShoeButton = new JButton("Manage Shoe");
		manageShoeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		manageShoeButton.setBounds(262, 397, 262, 75);
		manageShoeButton.setForeground(Color.decode("#B5FFD9"));
		manageShoeButton.setBackground(Color.decode("#3E00FF"));
		manageShoeButton.addActionListener(this);
		getContentPane().add(manageShoeButton);
		
	}

	void setFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(600, 150);
		getContentPane().setLayout(null);
		this.setSize(800, 800);
		this.getContentPane().setBackground(Color.decode("#170055"));
		this.setTitle("Just Do It");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addShoeButton) {
			this.dispose();
			new AddShoe(shoeVector);
			
		} else 
			if (e.getSource() == manageShoeButton) {
			this.dispose();
			new ManageShoe(shoeVector);
		}
	}
}
