package model.bean;

public class News {
	private String id_news;
	private String title;
	private String content;
	private String id_cat;
	private String nameCat;
	public String getId_news() {
		return id_news;
	}
	public void setId_news(String id_news) {
		this.id_news = id_news;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId_cat() {
		return id_cat;
	}
	public void setId_cat(String id_cat) {
		this.id_cat = id_cat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public News(String id_news, String title, String content, String id_cat, String nameCat) {
		super();
		this.id_news = id_news;
		this.title = title;
		this.content = content;
		this.id_cat = id_cat;
		this.nameCat = nameCat;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
