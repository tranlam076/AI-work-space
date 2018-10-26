package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Author;

public class AuthorDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String table;

	public AuthorDAO() {
		connectDBLibrary = new ConnectDBLibrary();
		this.table = "author";
	}

	public ArrayList<Author> getItems(String idSubmission ) {
		ArrayList<Author> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " WHERE idSubmission=? ORDER BY createdAt DESC";
		Author objAuthor = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idSubmission);
			rs = pst.executeQuery();
			while (rs.next()) {
				objAuthor = new Author(rs.getString("idAuthor"), rs.getString("idSubmission"), rs.getString("name"),
						rs.getString("email"), rs.getString("country"), rs.getString("organization"),
						rs.getString("webPage"), rs.getBoolean("isCorresponding"));
				listItems.add(objAuthor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listItems;
	}

	public int addItem(Author author) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO " + table
				+ " (idSubmission,name,email,country,organization,webPage,isCorresponding) VALUES (?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, author.getIdSubmission());
			pst.setString(2, author.getName());
			pst.setString(3, author.getEmail());
			pst.setString(4, author.getCountry());
			pst.setString(5, author.getOrganization());
			pst.setString(6, author.getWebPage());
			pst.setBoolean(7, author.isCorresponding());
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

	public Author getItem(String idAuthor) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " WHERE idAuthor = ?";
		Author objAuthor = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idAuthor);
			rs = pst.executeQuery();
			while (rs.next()) {
				objAuthor = new Author(rs.getString("idAuthor"), rs.getString("idSubmission"), rs.getString("name"),
						rs.getString("email"), rs.getString("country"), rs.getString("organization"),
						rs.getString("webPage"), rs.getBoolean("isCorresponding"));
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
		return objAuthor;
	}

	public int editItem(Author author) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE news SET idSubmission = ?, name = ?, email = ?, country = ?, organization = ?, webPage = ?, isCorresponding = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, author.getIdSubmission());
			pst.setString(2, author.getName());
			pst.setString(3, author.getEmail());
			pst.setString(4, author.getCountry());
			pst.setString(5, author.getOrganization());
			pst.setString(6, author.getWebPage());
			pst.setBoolean(7, author.isCorresponding());
			result = pst.executeUpdate();
		} catch (SQLException e) {
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

	public int delItem(String idAuthor) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM " + table + " WHERE idAuthor = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idAuthor);
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
		String sql = "SELECT COUNT(*) AS countAuthor FROM author ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("countAuthor");
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
		ArrayList<Author> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM table LIMIT ?,?";
		Author objAuthor = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objAuthor = new Author(rs.getString("idAuthor"), rs.getString("idSubmission"), rs.getString("name"),
						rs.getString("email"), rs.getString("country"), rs.getString("organization"),
						rs.getString("webPage"), rs.getBoolean("isCorresponding"));
				listItems.add(objAuthor);
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
