package model;

public class StudentCourse {
	
	private String studentId;
	private String courseId;
	
	public StudentCourse(String studentId, String courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}
