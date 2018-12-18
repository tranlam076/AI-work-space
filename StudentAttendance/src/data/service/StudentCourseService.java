package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.StudentCourse;

public class StudentCourseService extends BaseService {

	public StudentCourseService() {
		super();
	}
	
	public boolean add(StudentCourse studentCourse) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO student_course(student_id, course_id) VALUES (?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, studentCourse.getStudentId());
			ps.setString(2, studentCourse.getCourseId());
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
}
