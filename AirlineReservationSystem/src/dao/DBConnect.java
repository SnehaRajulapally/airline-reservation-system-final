package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	protected Connection connection;
	public Connection getConnection() { return connection; }

	/*static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
	static final String USER = "db510", PASS = "510"; // login credentials
	
	public Connection connect() throws SQLException 
	{
	 return DriverManager.getConnection(DB_URL, USER, PASS);   // error to display if the connection fails
	}*/
	
	private static String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";
	private static String username = "db510";
	private static String password = "510";

	public DBConnect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
}
}