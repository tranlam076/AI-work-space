package model.bean;

public class News {
	private int id_news;
	private String name;
	public News(int id_news, String name, String preview_text,
			String detail_text, int id_cat, String picture, String nameCat) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.id_cat = id_cat;
		this.picture = picture;
		this.nameCat = nameCat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	private String preview_text;
	private String detail_text;
	private int id_cat;
	private String picture;
	private String nameCat;
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(int id_news, String name, String preview_text,
			String detail_text, int id_cat, String picture) {
		super();
		this.id_news = id_news;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.id_cat = id_cat;
		this.picture = picture;
	}


}
