package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectDBLibrary;
import model.bean.News;

public class NewsDAO {
	private ConnectDBLibrary connectDBLibrary;
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public NewsDAO(){
		connectDBLibrary = new ConnectDBLibrary();
	}
	public ArrayList<News> getItems(){
		ArrayList<News> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * ,  category.name AS nameCat, news.name AS nameNews FROM news INNER JOIN category ON category.id_cat = news.id_cat ORDER BY id_news DESC ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				News objNews = new News(rs.getInt("id_news"), rs.getString("nameNews"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"),rs.getString("nameCat"));
				listItems.add(objNews);
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
	public int addItem(News objNews) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO news (name,preview_text,detail_text,id_cat,picture) VALUES (?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview_text());
			pst.setString(3, objNews.getDetail_text());
			pst.setInt(4, objNews.getId_cat());
			pst.setString(5, objNews.getPicture());
			pst.executeUpdate();
			result = 1;
			
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
	public News getItem(int idNews) {
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * ,  category.name AS nameCat, news.name AS nameNews FROM news INNER JOIN category ON category.id_cat = news.id_cat WHERE id_news = ?";
		News objNews = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			while(rs.next()){
				objNews = new News(rs.getInt("id_news"), rs.getString("nameNews"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"),rs.getString("nameCat"));
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
		return objNews;
	}
	public int editItem(News objNews) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "UPDATE news SET name = ?, preview_text = ?, detail_text = ?, id_cat = ?, picture = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getName());
			pst.setString(2, objNews.getPreview_text());
			pst.setString(3, objNews.getDetail_text());
			pst.setInt(4, objNews.getId_cat());
			pst.setString(5, objNews.getPicture());
			pst.setInt(6, objNews.getId_news());
			result = pst.executeUpdate();
			
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
	public int delItem(int idNews) {
		int result = 0;
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM news WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			result = pst.executeUpdate();
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
		String sql = "SELECT COUNT(*) AS countNews FROM news INNER JOIN category ON category.id_cat = news.id_cat";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				result = rs.getInt("countNews");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public ArrayList<News> getItemsPagition(int offset, int row_count) {
		ArrayList<News> listItems = new ArrayList<>();
		conn = connectDBLibrary.getConnectMySQL();
		String sql = "SELECT * ,  category.name AS nameCat, news.name AS nameNews FROM news INNER JOIN category ON category.id_cat = news.id_cat ORDER BY id_news DESC  LIMIT ?,?";
		System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNews = new News(rs.getInt("id_news"), rs.getString("nameNews"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getInt("id_cat"), rs.getString("picture"),rs.getString("nameCat"));
				listItems.add(objNews);
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
		return listItems;
	}

}
