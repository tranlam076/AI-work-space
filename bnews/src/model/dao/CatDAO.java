package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Category;

public class CatDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String table;
	
	public CatDAO(){
		connectDBLibrary = new ConnectDBLibrary();
		this.table = "category";
	}
	public ArrayList<Category> getItems(){
		ArrayList<Category> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
				listItems.add(objCat);
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
		return listItems;
	}
	public int addItem(String nameCat) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO " + table + " (name) VALUES (?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nameCat);
			pst.executeUpdate();
			result = 1;
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
		return result;
		
	}
	public Category getItem(int idCat) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " WHERE id_cat = ?";
		Category objCat = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			rs = pst.executeQuery();
			while(rs.next()){
				objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
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
		return objCat;
	}
	public int editItem(int idCat, String nameCat) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE " + table + " SET name = ? WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nameCat);
			pst.setInt(2, idCat);
			pst.executeUpdate();
			result = 2;
			
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
		return result;
	}
	public int delItem(int idCat) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM " + table + " WHERE id_cat = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
			pst.executeUpdate();
			result = 3;
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
		return result;
	}
	public int countItems() {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(*) AS countCat FROM category ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("countCat");
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
		ArrayList<Category> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM category LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();
			while(rs.next()){
				Category objCat = new Category(rs.getInt("id_cat"), rs.getString("name"));
				listItems.add(objCat);
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
		return listItems;
	}

}
