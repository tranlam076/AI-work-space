package data.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Image;
import model.Role;
import model.Student;
import model.StudentCourse;
import model.StudentImage;
import model.Teacher;
import model.User;

public class StudentImageService extends BaseService  {
	private ResultSet rs;

	public StudentImageService() {
		super();
	}
	
	public boolean add(String studentId, String imageUrl) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO student_image (id, student_id, image) VALUES (?, ?, ?);";
			ps = connection.prepareStatement(query);
			ps.setString(1, System.currentTimeMillis() + "");
			ps.setString(2, studentId);
			ps.setString(3, imageUrl);
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
