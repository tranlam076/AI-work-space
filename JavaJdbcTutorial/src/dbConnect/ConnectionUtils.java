package dbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtils {
	

	public static Connection getMyConnection() throws SQLException, ClassNotFoundException {
		return MySQLConnUtils.getMySQLConnection();
	}

	//
	// Test Connection ...
	//
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		PreparedStatement pst;
		ResultSet rs;
		System.out.println("Get connection ... ");
		Class.forName("com.mysql.jdbc.Driver");
		String username = "\"1@gmail.com\"";
		Connection conn = ConnectionUtils.getMyConnection();
		String sql = "SELECT * FROM " + "users" + " WHERE username = "+username;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("id_user")+ rs.getString("username")+ rs.getString("password")+ rs.getString("fullname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Done!");
	}

}
