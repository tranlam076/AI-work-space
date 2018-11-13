package model.bean;

public class User {
	String id;
	String password;
	String info;
	
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
	public User(String id, String password, String info) {
		super();
		this.info = info;
		this.id = id;
		this.password = password;
	}
	public User() {
		super();
	}
	
}
