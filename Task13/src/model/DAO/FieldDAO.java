package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Field;

public class FieldDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String table;
	
	public FieldDAO() {
		connectDBLibrary = new ConnectDBLibrary();
		this.table = "field";
	}

	public ArrayList<Field> getItems() {
		ArrayList<Field> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " ORDER BY createdAt DESC";
		Field objField = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objField = new Field(rs.getString("idField"), rs.getString("name"));
				listItems.add(objField);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listItems;
	}

	public int addItem(Field field) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO " + table
				+ " (name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, field.getName());
			pst.executeUpdate();
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Field getItem(String idField) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " WHERE idField = ?";
		Field objField = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idField);
			rs = pst.executeQuery();
			while (rs.next()) {
				objField = new Field(rs.getString("idField"), rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objField;
	}

	public int editItem(Field field) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE news SET name = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, field.getName());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int delItem(String idField) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM " + table + " WHERE idField = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idField);
			pst.executeUpdate();
			result = 3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int countItems() {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) AS countField FROM field ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("countField");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Object getItemsPagition(int offset, int row_count) {
		ArrayList<Field> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM table LIMIT ?,?";
		Field objField = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objField = new Field(rs.getString("idField"), rs.getString("name"));
				listItems.add(objField);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listItems;
	}
}
