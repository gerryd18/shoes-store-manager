package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class ManageShoe extends JFrame implements ActionListener, MouseListener{
	Vector<Shoe> shoeVector;
	JLabel titleLabel;
	
	//table
	JScrollPane scrollPane;
	JTable dataTable;
	Vector<Object> columnName = new Vector<>();
	Vector<Vector<Object>>data = new Vector<Vector<Object>>();
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField descTextField;
	private DatabaseConnection db;
	
	//form
	JLabel idLabel, nameLabel, priceLabel, descLabel;
	JSpinner priceSpinner;
	
	//button
	JButton deleteButton, updateButton, backButton;
	

	public ManageShoe(Vector<Shoe> shoeVector) {
		setFrame();
		this.shoeVector = shoeVector;
		db = new DatabaseConnection();
		
		//title
		titleLabel = new JLabel("Manage Shoe");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleLabel.setBounds(326, 317, 144, 37);
		titleLabel.setForeground(Color.decode("#B5FFD9"));
		getContentPane().add(titleLabel);
		
		
		//table
		columnName.add("Shoe ID");
		columnName.add("Shoe Name");
		columnName.add("Description");
		columnName.add("Price");
		
		dataTable = new JTable() {
			// to make user cant edit the table directly
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
		};
		
		for (int i = 0; i < shoeVector.size(); i++) {
			Vector<Object>row = new Vector<>();
			
			String shoeId = shoeVector.get(i).getShoeId();
			String shoeName = shoeVector.get(i).getShoeName();
			String shoeDesc = shoeVector.get(i).getShoeDescription();
			int shoePrice = shoeVector.get(i).getShoePrice();
			
			row.add(shoeId);
			row.add(shoeName);
			row.add(shoeDesc);
			row.add(shoePrice);
			data.add(row);
		}
		
		dataTable.setModel(new DefaultTableModel(data,columnName));
		dataTable.addMouseListener(this);
		dataTable.setFont(getFont().deriveFont(15f));;
		scrollPane = new JScrollPane(dataTable);
		scrollPane.setBounds(64, 26, 667, 270);
		getContentPane().add(scrollPane);
		
		idLabel = new JLabel("ID :");
		idLabel.setForeground(new Color(181, 255, 217));
		idLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		idLabel.setBounds(64, 392, 144, 37);
		getContentPane().add(idLabel);
		
		nameLabel = new JLabel("Name :");
		nameLabel.setForeground(new Color(181, 255, 217));
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		nameLabel.setBounds(64, 440, 144, 37);
		getContentPane().add(nameLabel);
		
		priceLabel = new JLabel("Price :");
		priceLabel.setForeground(new Color(181, 255, 217));
		priceLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		priceLabel.setBounds(64, 488, 144, 37);
		getContentPane().add(priceLabel);
		
		descLabel = new JLabel("Description :");
		descLabel.setForeground(new Color(181, 255, 217));
		descLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		descLabel.setBounds(64, 539, 144, 37);
		getContentPane().add(descLabel);
		
		idTextField = new JTextField();
		idTextField.setEditable(false);
		idTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		idTextField.setBounds(383, 392, 287, 37);
		getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTextField.setColumns(10);
		nameTextField.setBounds(383, 440, 287, 37);
		getContentPane().add(nameTextField);
		
		descTextField = new JTextField();
		descTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		descTextField.setColumns(10);
		descTextField.setBounds(383, 539, 287, 37);
		getContentPane().add(descTextField);
		
		priceSpinner = new JSpinner();
		priceSpinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		priceSpinner.setModel(new SpinnerNumberModel(1000, 1000, 1000000, 10));
		priceSpinner.setBounds(383, 488, 287, 37);
		getContentPane().add(priceSpinner);
		
		updateButton = new JButton("Update");
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		updateButton.setBounds(37, 625, 219, 42);
		updateButton.addActionListener(this);
		getContentPane().add(updateButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		deleteButton.setBounds(288, 625, 219, 42);
		deleteButton.addActionListener(this);
		getContentPane().add(deleteButton);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		backButton.setBounds(533, 625, 219, 42);
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
	
	
	void clearForm() {
		idTextField.setText("");
		nameTextField.setText("");
		priceSpinner.setValue(0);
		descTextField.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel tblModel = (DefaultTableModel)dataTable.getModel();
		
		String shoeId = shoeVector.get(dataTable.getSelectedRow()).getShoeId();
		String shoeName = shoeVector.get(dataTable.getSelectedRow()).getShoeName();
		int shoePrice = shoeVector.get(dataTable.getSelectedRow()).getShoePrice();
		String shoeDesc = shoeVector.get(dataTable.getSelectedRow()).getShoeDescription();
		
		idTextField.setText(shoeId);
		nameTextField.setText(shoeName);
		priceSpinner.setValue(shoePrice);
		descTextField.setText(shoeDesc);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == backButton) {
			this.dispose();
			new MainMenu(shoeVector);
		}else
			if (e.getSource() == deleteButton) {
				delete();
		}else
			if (e.getSource() == updateButton) {
				update();
			}
	}
	
	
	public void delete() {
		DefaultTableModel tblModel = (DefaultTableModel)dataTable.getModel(); //get table model
		
		if (shoeVector.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please add shoe first !!!");
		}else 
			if (dataTable.getSelectedRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Please select any row on table !!!");
		}else {
			
			String shoeId = shoeVector.get(dataTable.getSelectedRow()).getShoeId();
			
			//delete in database
			db.deleteShoe(shoeId);
			
			//delete in vector
			shoeVector.remove(dataTable.getSelectedRow());
			tblModel.removeRow(dataTable.getSelectedRow());
			
			
			
			JOptionPane.showMessageDialog(this, "Shoo successfully deleted!");
			clearForm();
		}
	}
	
	public void update() {
		DefaultTableModel tblModel = (DefaultTableModel)dataTable.getModel(); //get table model
		
		if (shoeVector.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please add shoe first !!!");
		}else
			if (dataTable.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Please select any row on table !!!");
		}else {
			
			String shoeId = idTextField.getText();
			String shoeName = nameTextField.getText();
			int shoePrice = (int) priceSpinner.getValue();
			String shoeDesc = descTextField.getText();
			
			if (validation(shoeName, shoePrice) == true) {
				
				// update table
				tblModel.setValueAt(shoeName, dataTable.getSelectedRow(), 1);
				tblModel.setValueAt(shoePrice, dataTable.getSelectedRow(), 3);
				tblModel.setValueAt(shoeDesc, dataTable.getSelectedRow(), 2);
				
				// update vector
				Shoe updateShoe = new Shoe(shoeId, shoeName, shoeDesc, shoePrice);
				shoeVector.set(dataTable.getSelectedRow(), updateShoe);
				
				//update database
				db.updateShoe(shoeId, shoeName, shoeDesc, shoePrice);
				
				clearForm();
				JOptionPane.showMessageDialog(this, "Shoe successfully updated!");
			}
			
		}
		
	}
	
	public boolean validation(String shoeName, int shoePrice) {
		
		if (	nameTextField.getText().equals("") || 
				descTextField.getText().equals("") ||
				priceSpinner.getValue().equals(0)) {
			JOptionPane.showMessageDialog(this, "please insert data correctly!");
		}else {
			
			if (shoeName.length()>3) {
				if (shoePrice>=1000) {
					return true;
				}else {
					JOptionPane.showMessageDialog(this, "Price must between 1000 - 1.000.000 !!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(this, "Shoe name length must above 3 characters !!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		return false;
	}
	
	
	

	
	
}
