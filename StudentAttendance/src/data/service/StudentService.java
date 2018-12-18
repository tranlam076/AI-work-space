package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Student;

public class StudentService extends BaseService {

	public StudentService() {
		super();
	}
	
	public Student get(String id) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, user_id, name FROM student WHERE id = ?;"
					);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("user_id");
				String name = rs.getString("name");
				return new Student(id, userId, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
	
	public boolean add(Student student) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO student(id, user_id, name) VALUES (?, ?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, student.getId());
			ps.setString(2, student.getUserId());
			ps.setString(3, student.getName());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public List<Course> getCourses(String studentId) {
		List<Course> courseList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, name, teacher_id FROM course WHERE id IN (SELECT course_id FROM student_course WHERE student_id = ?);"
					);
			ps.setString(1, studentId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String teacherId = rs.getString("teacher_id");
				courseList.add(new Course(id, teacherId, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return courseList;
	}
	
	public Student getStudentByUserId(String userId) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id, user_id, name FROM student WHERE user_id = ?;"
					);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				return new Student(id, userId, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return null;
	}
}
