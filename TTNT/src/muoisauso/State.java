package muoisauso;

public class State {
	
	int g=0,h=0;
	State fatherState;
	Operator operator;

	int a[][] = new int[4][4];
	
	public State clone() {
		State state = new State();
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 4; x++)
				state.a[y][x] = this.a[y][x];
		return state;
	}
	
	public void print() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if((""+a[y][x]).length() < 2) {
					System.out.print(a[y][x] + "    ");
				} else {
					System.out.print(a[y][x] + "   ");
				}
			}
			System.out.println();
		}
		System.out.println("---------------");
	}
	
	public String key() {
		String status = "";
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				status = status + a[y][x];
			}
		}
		return status;
	}
}
