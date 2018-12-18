package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Constants;

public class SqliteConnection {

	private static SqliteConnection instance;
	private Connection connection;
	
	public static void main(String[] args) {
		SqliteConnection.getInstance().getConnection();
	}

	private SqliteConnection() {

	}

	public static SqliteConnection getInstance() {
		if (instance == null) {
			instance = new SqliteConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + Constants.DATA_PATH);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close(AutoCloseable... autoCloseables) {
		try {
			for (AutoCloseable autoCloseable : autoCloseables) {
				if (autoCloseable != null)
					autoCloseable.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
