package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.Submission;

public class SubmissionDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private String table;

	public SubmissionDAO() {
		connectDBLibrary = new ConnectDBLibrary();
		this.table = "submission";
	}

	public ArrayList<Submission> getItems() {
		ArrayList<Submission> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " ORDER BY createdAt DESC";
		Submission objSubmission = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"), rs.getString("idField"), rs.getString("title"),
						rs.getString("description"), rs.getString("keywords"), rs.getString("fileNameUpload"));
				listItems.add(objSubmission);
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

	public String addItem(Submission submission) throws SQLException {
		String result = "default";
		conn = connectDBLibrary.getConnectMySQL();
		st = conn.createStatement();
		String sql = "INSERT INTO " + table
				+ " (idField,title,description,keywords,fileNameUpload) VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql, new String[]{"idSubmission"});
			pst.setString(1, submission.getIdField());
			pst.setString(2, submission.getTitle());
			pst.setString(3, submission.getDescription());
			pst.setString(4, submission.getKeywords());
			pst.setString(5, submission.getFileNameUpload());
			result = "" + pst.executeUpdate();
//			rs = pst.getGeneratedKeys();
//			while (rs.next()) {
//				result = rs.getString(0);
//			}
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

	public Submission getItem(String idSubmission) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM " + table + " WHERE idSubmission = ?";
		Submission objSubmission = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idSubmission);
			rs = pst.executeQuery();
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"), rs.getString("idField"), rs.getString("title"),
						rs.getString("description"), rs.getString("keywords"), rs.getString("fileNameUpload"));
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
		return objSubmission;
	}

	public int editItem(Submission submission) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE news SET IdField = ?, name = ?, email = ?, country = ?, organization = ?, webPage = ?, isCorresponding = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, submission.getIdField());
			pst.setString(2, submission.getTitle());
			pst.setString(3, submission.getDescription());
			pst.setString(4, submission.getKeywords());
			pst.setString(5, submission.getFileNameUpload());
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

	public int delItem(String idSubmission) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM " + table + " WHERE idSubmission = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idSubmission);
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
		String sql = "SELECT COUNT(*) AS countSubmission FROM submission ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("countSubmission");
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
		ArrayList<Submission> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM table LIMIT ?,?";
		Submission objSubmission = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"),rs.getString("idField"), rs.getString("title"),
						rs.getString("description"), rs.getString("keywords"), rs.getString("fileNameUpload"));
				listItems.add(objSubmission);
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
