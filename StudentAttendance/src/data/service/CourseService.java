package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Image;
import model.Student;

public class CourseService extends BaseService {

	public CourseService() {
		super();
	}
	
	public Course get(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, teacher_id, name, status FROM course WHERE id = ? AND status <> 'deleted';"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String teacherId = rs.getString("teacher_id");
				String name = rs.getString("name");
				String status = rs.getString("status");
				return new Course(id, name, teacherId, status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public boolean add(Course course) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO course(id, name, teacher_id, status) VALUES (?, ?, ?, 'activate');";
			ps = connection.prepareStatement(query);
			ps.setString(1, course.getId());
			ps.setString(2, course.getName());
			ps.setString(3, course.getTeacherId());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public boolean put(Course oldCourse, Course newCourse) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE course SET name = ? WHERE id = ? AND teacher_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setString(1, newCourse.getName());
			ps.setString(2, oldCourse.getId());
			ps.setString(3, oldCourse.getTeacherId());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public boolean delete(Course course) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE course SET status = 'deleted' WHERE id = ? AND teacher_id = ?;";
			ps = connection.prepareStatement(query);
			ps.setString(1, course.getId());
			ps.setString(2, course.getTeacherId());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public List<Student> getStudents(String courseId) {
		List<Student> studentList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, user_id, name FROM student WHERE id IN (SELECT student_id FROM student_course WHERE course_id = ?);"
					);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String userId = rs.getString("user_id");
				String name = rs.getString("name");
				studentList.add(new Student(id, userId, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return studentList;
	}

	public List<Image> getImages(String courseId) {
		List<Image> imageList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, course_id, url, name, ext, status FROM image WHERE course_id = ? AND status <> 'deleted'"
					);
			ps.setString(1, courseId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String url = rs.getString("url");
				String ext = rs.getString("ext");
				String status = rs.getString("status");
				imageList.add(new Image(id, courseId, url, name, ext, status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return imageList;
	}
}
