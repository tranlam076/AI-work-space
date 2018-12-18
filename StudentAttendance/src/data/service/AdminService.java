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


public class AdminService extends BaseService {

	private ResultSet rs;
	private StudentService studentService;

	public AdminService() {
		super();
	}
	public List<String> getTables() {
		List<String> tableList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT name FROM sqlite_master WHERE type='table';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				tableList.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return tableList;
	}
	
	public List<String> getTableColumns(String tableName) {
		List<String> columnList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"PRAGMA table_info(" + tableName + ")"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				columnList.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return columnList;
	}
	
	public List<User> getTableUser(String field, String keyWord) {
		List<User> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM user WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("role_id"));
				listItem.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<Role> getTableRole(String field, String keyWord) {
		List<Role> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM role WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Role role = new Role(rs.getString("id"), rs.getString("name"));
				listItem.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<Course> getTableCourse(String field, String keyWord) {
		List<Course> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM course WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Course course = new Course(rs.getString("id"), rs.getString("name"), rs.getString("teacher_id"), rs.getString("status"));
				listItem.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<Image> getTableImage(String field, String keyWord) {
		List<Image> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM image WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Image image = new Image(rs.getString("id"), rs.getString("course_id"), rs.getString("url"), rs.getString("name"), rs.getString("ext"), rs.getString("status"), Long.parseLong(rs.getString("created")));
				listItem.add(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<Student> getTableStudent(String field, String keyWord) {
		List<Student> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM student WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Student student = new Student(rs.getString("id"), rs.getString("user_id"), rs.getString("name"));
				listItem.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<Teacher> getTableTeacher(String field, String keyWord) {
		List<Teacher> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM teacher WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				Teacher teacher = new Teacher(rs.getString("id"), rs.getString("user_id"), rs.getString("name"));
				listItem.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<StudentCourse> getTableStudentCourse(String field, String keyWord) {
		List<StudentCourse> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM student_course WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				StudentCourse studentCourse = new StudentCourse(rs.getString("student_id"), rs.getString("course_id"));
				listItem.add(studentCourse);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
	
	public List<StudentImage> getTableStudentImage(String field, String keyWord) {
		List<StudentImage> listItem = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT * FROM student_image WHERE " + field + " like '%" + keyWord + "%';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				StudentImage studentImage = new StudentImage(rs.getString("id"), rs.getString("student_id"), rs.getString("image"));
				listItem.add(studentImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return listItem;
	}
		
	public boolean deleteRecord(String table, String field[], String value[]) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "DELETE FROM " + table + " WHERE ";
			for (int i = 0; i <  field.length; i++) {
				query += field[i] + " = '" + value[i] + "'";
				if (i < field.length - 1) {
					query += " AND ";
				}
			}
			
			System.out.println(query);
			ps = connection.prepareStatement(query);
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public boolean updateRecord(String table, String newDateField[], String newDataValue[], String conditionField[], String conditionValue[]) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "UPDATE " + table + " SET ";
			for (int i = 0; i <  newDateField.length; i++) {
				query += newDateField[i] + " = '" + newDataValue[i] + "'";
				if (i < newDateField.length - 1) {
					query += ", ";
				}
			}
			query += " WHERE ";
			for (int i = 0; i <  conditionField.length; i++) {
				query += conditionField[i] + " = '" + conditionValue[i] + "'";
				if (i < conditionField.length - 1) {
					query += " AND ";
				}
			}
			
			System.out.println(query);
			ps = connection.prepareStatement(query);
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	
	public boolean addNewRecord(String table, String field[], String value[]) {
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO " + table + " (";
			for (int i = 0; i <  field. length; i++) {
				query += field[i] + ",";
			}
			query = query.substring(0, query.length() - 1) + ") VALUES (";
			for (int i = 0; i <  value. length; i++) {
				query += "'" + value[i] + "',";
			}
			query = query.substring(0, query.length() - 1) + ");";
			System.out.println(query);
			ps = connection.prepareStatement(query);
			int r = ps.executeUpdate();
			return (r == 1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(ps, connection);
		}
		return false;
	}
	public List<String> getTableColumns() {
		List<String> studentIdList = new ArrayList<>();
		Connection connection = sqlite.getConnection();
		PreparedStatement ps = null;
		rs = null;
		try {
			ps = connection.prepareStatement(
					"SELECT id FROM user WHERE role_id='student';"
					);
			rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("id");
				studentIdList.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlite.close(rs, ps, connection);
		}
		return studentIdList;
	}
}
