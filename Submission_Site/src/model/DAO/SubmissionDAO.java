package model.DAO;

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
		String sql = "SELECT submission.idSubmission,submission.createdAt,field.name,submission.title FROM submission LEFT JOIN field ON field.idField = submission.idField WHERE submission.deletedAt IS NULL ORDER BY submission.createdAt DESC LIMIT 0,10";
		Submission objSubmission = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"), rs.getString("name"),
						rs.getString("title"), rs.getString("createdAt"));
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

	public int addItem(Submission submission) throws SQLException {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO " + table
				+ " (idSubmission,idField,title,description,keywords,fileNameUpload) VALUES (?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, submission.getIdSubmission());
			pst.setString(2, submission.getIdField());
			pst.setString(3, submission.getTitle());
			pst.setString(4, submission.getDescription());
			pst.setString(5, submission.getKeywords());
			pst.setString(6, submission.getFileNameUpload());
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

	public Submission getItem(String idSubmission) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT submission.description,submission.keywords,submission.fileNameUpload,submission.idField,submission.idSubmission,submission.createdAt,field.name,submission.title FROM submission LEFT JOIN field ON field.idField = submission.idField WHERE submission.idSubmission=?";
		Submission objSubmission = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idSubmission);
			rs = pst.executeQuery();
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"), rs.getString("idField"),rs.getString("name"),
						rs.getString("title"), rs.getString("description"), rs.getString("keywords"),
						rs.getString("fileNameUpload"), rs.getString("createdAt"));
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
	
	public int delItems(ArrayList<String> listIdSubmission) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String sql = "UPDATE submission SET deletedAt = '" + date +"' WHERE "; //idSubmission = ";
		for (int i = 0; i < listIdSubmission.size(); i++) {
			sql += "idSubmission = '" + listIdSubmission.get(i) + "'";
			if (i < listIdSubmission.size() - 1) {
				sql += " OR ";
			}
		}
		System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
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

	public ArrayList<Submission> getItemsPagition(int offset, int row_count) {
		ArrayList<Submission> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT submission.idSubmission,submission.createdAt,field.name,submission.title FROM submission LEFT JOIN field ON field.idField = submission.idField ORDER BY submission.createdAt DESC LIMIT ?,?";
		Submission objSubmission = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();
			while (rs.next()) {
				objSubmission = new Submission(rs.getString("idSubmission"), rs.getString("name"),
						rs.getString("title"), rs.getString("createdAt"));
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
