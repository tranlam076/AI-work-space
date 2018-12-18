package model;

public class Image {

	private String id;
	private String courseId;
	private String url;
	private String name;
	private String ext;
	private String status;
	private long created;
	
	public Image(String id, String courseId, String url, String name, String ext, String status) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.url = url;
		this.name = name;
		this.ext = ext;
		this.status = status;
	}
	
	public Image(String id, String courseId, String url, String name, String ext, String status, long created) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.url = url;
		this.name = name;
		this.ext = ext;
		this.status = status;
		this.created = created;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

}
