package model.bean;

public class User {
	String id;
	String password;
	String info;
	String role;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String id, String password, String info, String role) {
		super();
		this.id = id;
		this.password = password;
		this.info = info;
		this.role = role;
	}
	public User() {
		super();
	}
	
}
