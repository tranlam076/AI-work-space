package tamso;

public class State {
	
	int g=0,h=0;
	State father;
	Operator mom;

	int a[][] = new int[3][3];
	
	public State clone() {
		State s = new State();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				s.a[y][x] = this.a[y][x];
		return s;
	}
	
	public void print() {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				System.out.print(a[y][x] + "   ");
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
	
	public String key() {
		String st = "";
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				st = st + a[y][x];
//				System.out.print(a[y][x] + "   ");
			}
//			System.out.println();
		}
//		System.out.println("---------------");
		return st;
	}
}
