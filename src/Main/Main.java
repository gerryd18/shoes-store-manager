package Main;

import java.util.Vector;

import javax.swing.JOptionPane;

public class Main {
Vector<Shoe> shoeVector = new Vector<>();
private DatabaseConnection db;

	public Main() {
		db = new DatabaseConnection();
		addDatabase();

		new MainMenu(shoeVector);
	}
	
	public void addDatabase() {
		db.resultSet = db.view();
		
		try {
			while(db.resultSet.next()) {
				String shoeId = String.valueOf(db.resultSet.getString("shoeId"));
				String shoeName = String.valueOf(db.resultSet.getString("shoeName"));
				String shoeDesc = String.valueOf(db.resultSet.getString("shoeDesc"));
				int shoePrice = db.resultSet.getInt("shoePrice");
				
				Shoe newShoe = new Shoe(shoeId, shoeName, shoeDesc, shoePrice);
				shoeVector.add(newShoe);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
