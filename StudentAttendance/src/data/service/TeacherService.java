package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Teacher;

public class TeacherService extends BaseService {

	public TeacherService() {
		super();
	}
	
	public Teacher get(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, user_id, name FROM teacher WHERE id = ?;"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("user_id");
				String name = rs.getString("name");
				return new Teacher(id, userId, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}

	public List<Course> getCourses(String teacherId) {
		List<Course> courseList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, name, teacher_id FROM course WHERE teacher_id = ? AND status <> 'deleted';"
					);
			ps.setString(1, teacherId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				courseList.add(new Course(id, teacherId, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return courseList;
	}
	
	public Teacher getTeacherByUserId(String userId) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, user_id, name FROM teacher WHERE user_id = ?;"
					);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				return new Teacher(id, userId, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
}
