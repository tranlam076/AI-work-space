package model.bean;

public class Author {
	private String idAuthor;
	private String idSubmission;
	private String name;
	private String email;
	private String country;
	private String organization;
	private String webPage;
	private boolean isCorresponding;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
	
	public Author(String idAuthor, String idSubmission, String name, String email, String country, String organization,
			String webPage, boolean isCorresponding) {
		super();
		this.idAuthor = idAuthor;
		this.idSubmission = idSubmission;
		this.name = name;
		this.email = email;
		this.country = country;
		this.organization = organization;
		this.webPage = webPage;
		this.isCorresponding = isCorresponding;
	}

	public Author(String idAuthor, String idSubmission, String name, String email, String country, String organization,
			String webPage, boolean isCorresponding, String createdAt, String updatedAt, String deletedAt) {
		super();
		this.idAuthor = idAuthor;
		this.idSubmission = idSubmission;
		this.name = name;
		this.email = email;
		this.country = country;
		this.organization = organization;
		this.webPage = webPage;
		this.isCorresponding = isCorresponding;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public String getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(String idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getIdSubmission() {
		return idSubmission;
	}

	public void setIdSubmission(String idSubmission) {
		this.idSubmission = idSubmission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public boolean isCorresponding() {
		return isCorresponding;
	}

	public void setCorresponding(boolean isCorresponding) {
		this.isCorresponding = isCorresponding;
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

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
