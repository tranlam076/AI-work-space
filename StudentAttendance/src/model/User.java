package model;

public class User {

	private String id;
	private String username;
	private String password;
	private String roleId;

	public User(String username, String password, String roleId) {
		super();
		this.username = username;
		this.password = password;
		this.roleId = roleId;
	}

	public User(String id, String username, String password, String roleId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public User() {
		super();
	}
	
	
}
