package model.bean;

public class Category {
	private String id_cat;
	private String name;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String id_cat, String name) {
		super();
		this.id_cat = id_cat;
		this.name = name;
	}

	public String getId_cat() {
		return id_cat;
	}
	public void setId_cat(String id_cat) {
		this.id_cat = id_cat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
