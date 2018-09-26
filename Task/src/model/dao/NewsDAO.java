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
		String sql = "SELECT * FROM news ORDER BY id_news DESC ";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				News objNews = new News(rs.getString("id_news"), rs.getString("title"),rs.getString("content"),rs.getString("id_cat"),rs.getString("nameCat"));
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
		String sql = "INSERT INTO news (title,content,id_cat,nameCat) VALUES (?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getTitle());
			pst.setString(2, objNews.getContent());
			pst.setString(3, objNews.getId_cat());
			pst.setString(4, objNews.getNameCat());
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
		String sql = "SELECT * ,  cat.name AS nameCat, news.title AS titleNews FROM news INNER JOIN cat ON cat.id_cat = news.id_cat WHERE id_news = ?";
		News objNews = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idNews);
			rs = pst.executeQuery();
			while(rs.next()){
				objNews = new News(rs.getString("id_news"), rs.getString("title"),rs.getString("content"),rs.getString("id_cat"),rs.getString("nameCat"));
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
		String sql = "UPDATE news SET title = ?, content = ?, id_cat = ? WHERE id_news = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNews.getTitle());
			pst.setString(2, objNews.getContent());
			pst.setString(3, objNews.getId_cat());
			pst.setString(4, objNews.getId_news());
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
		String sql = "SELECT COUNT(*) AS countNews FROM news INNER JOIN cat ON cat.id_cat = news.id_cat";
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
		String sql = "SELECT * ,  cat.name AS nameCat, news.name AS nameNews FROM news INNER JOIN cat ON cat.id_cat = news.id_cat ORDER BY id_news DESC  LIMIT ?,?";
		System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();
			while(rs.next()){
				News objNews = new News(rs.getString("id_news"), rs.getString("title"),rs.getString("content"),rs.getString("id_cat"),rs.getString("nameCat"));
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
