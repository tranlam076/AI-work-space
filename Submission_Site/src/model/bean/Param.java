package model.bean;

public class Param {
	String name = "";
	String value = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Param(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public Param() {
		super();
		// TODO Auto-generated constructor stub
	}
}
