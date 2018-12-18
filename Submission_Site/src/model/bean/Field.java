package model.bean;

public class Field {
	private String idField;
	private String name;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	public Field() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Field(String idField, String name) {
		super();
		this.idField = idField;
		this.name = name;
	}
	public Field(String idField, String name, String createdAt, String updatedAt, String deletedAt) {
		super();
		this.idField = idField;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	
}
