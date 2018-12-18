package model;

public class StudentImage {
	private String id;
	private String studentId;
	private String student_name;
	private String image;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	
	public String getImageId() {
		return image;
	}
	public void setImageId(String imageId) {
		this.image = imageId;
	}
	public StudentImage(String id, String studentId, String student_name, String image) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.image = image;
		this.student_name = student_name;
	}
	
	
	
	public StudentImage(String id, String studentId, String image) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.image = image;
	}
	public StudentImage() {
		super();
	}	
}
