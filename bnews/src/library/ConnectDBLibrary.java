package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDBLibrary {
	private Connection conn;
	private String url;
	private String user;
	private String password;
	private String db;
	
	public ConnectDBLibrary() {
		PropertiesLibrary prop = new PropertiesLibrary();
		Properties objProp = prop.readProp();
		System.out.println(objProp.getProperty("url"));
		System.out.println(objProp.getProperty("user"));
		
	
		this.url = objProp.getProperty("url");
		this.user =  objProp.getProperty("user");
		this.password = objProp.getProperty("password");
	}
	
	public Connection getConnectMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args) {
		System.out.println(new ConnectDBLibrary().getConnectMySQL());
	}

}
