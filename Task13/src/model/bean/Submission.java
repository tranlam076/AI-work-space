package model.bean;

public class Submission {
	String idSubmission;
	String idField;
	String fieldName;
	String title;
	String description;
	String keywords;
	String fileNameUpload;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;

	
	public Submission(String idSubmission, String fieldName, String title, String createdAd) {
		super();
		this.idSubmission = idSubmission;
		this.fieldName = fieldName;
		this.title = title;
		this.createdAt = createdAd;
	}

	public Submission(String idSubmission, String idField, String title, String description, String keywords,
			String fileNameUpload) {
		super();
		this.idSubmission = idSubmission;
		this.idField = idField;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.fileNameUpload = fileNameUpload;
	}

	public Submission(String idSubmission, String idField, String title, String description, String keywords,
			String fileNameUpload, String createdAt, String updatedAt, String deletedAt) {
		super();
		this.idSubmission = idSubmission;
		this.idField = idField;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.fileNameUpload = fileNameUpload;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	public Submission(String idSubmission, String idField, String fieldName, String title, String description, String keywords,
			String fileNameUpload, String createdAt) {
		super();
		this.idSubmission = idSubmission;
		this.idField = idField;
		this.fieldName = fieldName;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.fileNameUpload = fileNameUpload;
		this.createdAt = createdAt;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getIdSubmission() {
		return idSubmission;
	}

	public void setIdSubmission(String idSubmission) {
		this.idSubmission = idSubmission;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getFileNameUpload() {
		return fileNameUpload;
	}

	public void setFileNameUpload(String fileNameUpload) {
		this.fileNameUpload = fileNameUpload;
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

	public Submission() {
		super();
		// TODO Auto-generated constructor stub
	}
}
