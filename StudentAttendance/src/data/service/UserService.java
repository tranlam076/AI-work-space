package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.SqliteConnection;
import model.User;
import util.CryptoUtils;

public class UserService extends BaseService {
	SqliteConnection sqlite = SqliteConnection.getInstance();
	public UserService() {
		super();
	}
	
	public String check(String token) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM user"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				if (token.equals(CryptoUtils.md5(rs.getString("username")) + CryptoUtils.hmacSHA256(rs.getString("password")))) {
					return rs.getString("role_id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public User find(String username) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, username, password, token FROM user WHERE username = ?;"
					);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String token = rs.getString("token");
				return new User(id, username, password, token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(connection, ps, rs);
		}
		return null;
	}
	
	public User get(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id FROM user WHERE id = ?;"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new User(id, "", "", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public User find(String username, String password) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, username, password, role_id FROM user WHERE username = ? AND password = ?;"
					);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String roleId = rs.getString("role_id");
				return new User(id, username, password, roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public User findByToken(String token) {
//		Connection connection = sqlite.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			ps = connection.prepareStatement(
//					"SELECT id, username, password, token, type FROM user WHERE token = ?;"
//					);
//			ps.setString(1, token);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				String id = rs.getString("id");
//				String username = rs.getString("username");
//				String password = rs.getString("password");
//				String type = rs.getString("type");
//				return new User(id, username, password, token, type);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			sqlite.close(connection, ps, rs);
//		}
		return null;
	}
	
	public boolean add(User user) {	
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO user(id, username, password, role_id) VALUES (?, ?, ?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRoleId());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
}
