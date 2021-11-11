package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddShoe extends JFrame implements ActionListener{
Vector<Shoe> shoeVector;
JLabel titleLabel, shoeIdLabel, shoeNameLabel, shoeDescLabel, shoePriceLabel;
JPanel formPanel;
JButton addButton, backButton;
private JTextField shoeIdTextField;
private JTextField shoeNameTextField;
private JTextField shoeDescTextField;
private JTextField shoePriceTextField;
private DatabaseConnection db;

	public AddShoe(Vector<Shoe> shoeVector) {
		this.shoeVector = shoeVector;
		setFrame();
		db = new DatabaseConnection();

		
		titleLabel = new JLabel("Add Shoe");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		titleLabel.setBounds(321, 82, 153, 84);
		titleLabel.setForeground(Color.decode("#B5FFD9"));
		getContentPane().add(titleLabel);
		
		formPanel = new JPanel();
		formPanel.setBounds(113, 199, 569, 332);
		formPanel.setLayout(null);
		formPanel.setBackground(Color.decode("#72147E"));
		getContentPane().add(formPanel);
		
		shoeIdLabel = new JLabel("Shoe ID :");
		shoeIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeIdLabel.setBounds(51, 53, 179, 34);
		shoeIdLabel.setForeground(Color.decode("#B5FFD9"));
		formPanel.add(shoeIdLabel);
		
		shoeNameLabel = new JLabel("Shoe Name :");
		shoeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeNameLabel.setBounds(51, 107, 179, 34);
		shoeNameLabel.setForeground(Color.decode("#B5FFD9"));
		formPanel.add(shoeNameLabel);
		
		shoeDescLabel = new JLabel("Shoe Description :");
		shoeDescLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeDescLabel.setBounds(51, 159, 179, 34);
		shoeDescLabel.setForeground(Color.decode("#B5FFD9"));
		formPanel.add(shoeDescLabel);
		
		shoePriceLabel = new JLabel("Shoe Price :");
		shoePriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoePriceLabel.setBounds(51, 214, 179, 34);
		shoePriceLabel.setForeground(Color.decode("#B5FFD9"));
		formPanel.add(shoePriceLabel);
		
		shoeIdTextField = new JTextField();
		shoeIdTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeIdTextField.setBounds(283, 57, 226, 34);
		formPanel.add(shoeIdTextField);
		shoeIdTextField.setColumns(10);
		
		shoeNameTextField = new JTextField();
		shoeNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeNameTextField.setColumns(10);
		shoeNameTextField.setBounds(283, 107, 226, 34);
		formPanel.add(shoeNameTextField);
		
		shoeDescTextField = new JTextField();
		shoeDescTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoeDescTextField.setColumns(10);
		shoeDescTextField.setBounds(283, 159, 226, 34);
		formPanel.add(shoeDescTextField);
		
		shoePriceTextField = new JTextField();
		shoePriceTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shoePriceTextField.setColumns(10);
		shoePriceTextField.setBounds(283, 214, 226, 34);
		formPanel.add(shoePriceTextField);
		
		addButton = new JButton("Add");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		addButton.setBounds(154, 580, 185, 55);
		addButton.addActionListener(this);
		getContentPane().add(addButton);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		backButton.setBounds(418, 580, 185, 55);
		backButton.addActionListener(this);
		getContentPane().add(backButton);
		
	}
	
	void setFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(600, 150);
		getContentPane().setLayout(null);
		this.setSize(800, 800);
		this.getContentPane().setBackground(Color.decode("#170055"));
		this.setTitle("Just Do It");
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == backButton) {
			this.dispose();
			new MainMenu(shoeVector);
		}else
			if (e.getSource() == addButton) {
				add();
			}
	}
	
	public void add() {
		
		
		if (shoeIdTextField.getText().equals("") || 
				shoeNameTextField.getText().equals("") || 
				shoeDescTextField.getText().equals("") ||
				shoePriceTextField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "please insert data correctly!");
		}else {
			
			String id = shoeIdTextField.getText();
			String name = shoeNameTextField.getText();
			String desc = shoeDescTextField.getText();
			int price = validateNumber();
			
			if (validation(id,name,desc,price)==true) {
				Shoe newShoe = new Shoe(id,name,desc,price);
				shoeVector.add(newShoe);
				
				//add to database
				db.addShoe(id,name,desc,price);
				
				JOptionPane.showMessageDialog(this, "Shoe successfully added!");
				
				this.dispose();
				new MainMenu(shoeVector);
				
			}
		}
		
	}
	
	public int validateNumber() {
		int temp = 0;
		try {
			temp = Integer.parseInt(shoePriceTextField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Price must be integer", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		return temp;
	}
	
	
	public boolean validation(String id, String name, String desc, int price) {
		Vector<String> idVector = new Vector<>();
		
		for (int i = 0; i < shoeVector.size(); i++) {
			idVector.add(shoeVector.get(i).getShoeId());
		}	
		
		for (int i = 0; i < idVector.size(); i++) {
			System.out.println(idVector.get(i));
		}
		
		
		if (idVector.indexOf(id) == -1) {
			if (id.matches("SH[0-9][0-9][0-9]")) {
				if (name.length() > 3) {
					if (price > 1000) {
						return true;
					}else {
						JOptionPane.showMessageDialog(this, "Price must above 1000!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(this, "shoe name must up to 3 character!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Shoe ID must begin with SH and 3 random number, \n example : SH001", "Error", JOptionPane.ERROR_MESSAGE);
			}		
		}else {
			JOptionPane.showMessageDialog(this, "Please input another ID", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return false;
	}
}
