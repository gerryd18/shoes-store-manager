package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DatabaseConnection {
	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement;

	public DatabaseConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoesstoremanager", "root", "");
			statement = connection.createStatement();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet view() {
		
		try {
			preparedStatement = connection.prepareStatement("select*from shoe");
			resultSet = preparedStatement.executeQuery();
			resultSetMetaData = resultSet.getMetaData();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public ResultSet addShoe(String shoeId, String shoeName, String shoeDesc, int shoePrice) {
		
		try {
			preparedStatement = connection.prepareStatement("Insert into shoe values (?,?,?,?)");
			preparedStatement.setString(1, shoeId);
			preparedStatement.setString(2, shoeName);
			preparedStatement.setString(3, shoeDesc);
			preparedStatement.setInt(4, shoePrice);
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public ResultSet deleteShoe(String shoeId) {
		
		try {
			preparedStatement = connection.prepareStatement("Delete from shoe where shoeID = '"+shoeId+"'");
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public ResultSet updateShoe (String shoeId, String shoeName, String shoeDesc, int shoePrice) {
		
		try {
			preparedStatement = connection.prepareStatement("UPDATE shoe SET shoeName = '"+shoeName+"', shoeDesc = '"+shoeDesc+"', shoePrice = "+shoePrice+" WHERE shoeId = '"+shoeId+"'");
			preparedStatement.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultSet;
	}

}
