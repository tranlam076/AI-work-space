package banker;

public class Prameter {
	String name = "";
	Position psotion = new Position();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getPsotion() {
		return psotion;
	}
	public void setPsotion(Position psotion) {
		this.psotion = psotion;
	}
	public Prameter(String name, Position psotion) {
		super();
		this.name = name;
		this.psotion = psotion;
	}
	public Prameter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
