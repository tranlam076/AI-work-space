package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Course;
import model.Image;

public class ImageService extends BaseService {

	public ImageService() {
		super();
	}
	
	public Image get(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, course_id, url, name, ext, status FROM image WHERE id = ? AND status <> 'deleted'"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String courseId = rs.getString("course_id");
				String name = rs.getString("name");
				String url = rs.getString("url");
				String ext = rs.getString("ext");
				String status = rs.getString("status");
				return new Image(id, courseId, url, name, ext, status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public boolean add(Image image) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO image(id, course_id, url, name, ext, status, created) VALUES (?, ?, ?, ?, ?, ?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, image.getId());
			ps.setString(2, image.getCourseId());
			ps.setString(3, image.getUrl());
			ps.setString(4, image.getName());
			ps.setString(5, image.getExt());
			ps.setString(6, image.getStatus());
			ps.setLong(7, image.getCreated());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public boolean delete(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"UPDATE image SET status = 'deleted' WHERE id = ?"
					);
			ps.setString(1, id);
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return false;
	}
}
