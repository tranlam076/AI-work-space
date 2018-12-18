package model;

public class Course {

	private String id;
	private String name;
	private String teacherId;
	private String status;	
	
	public Course(String id, String teacherId, String name) {
		super();
		this.id = id;
		this.teacherId = teacherId;
		this.name = name;
	}
	
	public Course(String id, String name, String teacherId, String status) {
		super();
		this.id = id;
		this.name = name;
		this.teacherId = teacherId;
		this.status = status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
}
