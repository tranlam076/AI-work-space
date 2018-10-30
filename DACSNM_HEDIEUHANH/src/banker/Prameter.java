package banker;

public class Prameter {
	String name = "";
	Position p = new Position();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getP() {
		return p;
	}
	public void setP(Position p) {
		this.p = p;
	}
	public Prameter(String name, Position p) {
		super();
		this.name = name;
		this.p = p;
	}
}
