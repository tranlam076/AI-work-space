package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBLibrary {
	private Connection conn;
	private String url;
	private String user;
	private String pass;
	private String db;
	
	public ConnectDBLibrary(){
		this.db = "task13";
		this.url = "jdbc:mysql://localhost:3306/"+db;
		this.user = "root";
		this.pass = "709f2dfe";
	}
	
	public Connection getConnectMySQL() {
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		ConnectDBLibrary connectDBLibrary = new ConnectDBLibrary();
		System.out.println(connectDBLibrary.getConnectMySQL());
	}
}
