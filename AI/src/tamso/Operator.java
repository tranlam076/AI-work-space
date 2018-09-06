package tamso;

public class Operator {
	/*
	 * 0: up
	 * 1: down
	 * 2: left
	 * 3: right
	 */
	int i;
	
	public Operator(int i) {
		this.i = i;
	}
	
	public State move(State s) {
		switch(i) {
		case 0: return up(s);
		case 1: return down(s);
		case 2: return left(s);
		case 3: return right(s);
		}
		return null;
	}

	private State right(State s) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (s.a[y][x] == 0) {
					x0 = x;
					y0 = 0;
				}
		
		if (x0 == 0) return null;
		State sn = s.clone();
		sn.a[y0][x0] = s.a[y0][x0-1];
		sn.a[y0][x0-1] = 0;
		return sn;
	}

	private State left(State s) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (s.a[y][x] == 0) {
					x0 = x;
					y0 = 0;
				}
		
		if (x0 == 2) return null;
		State sn = s.clone();
		sn.a[y0][x0] = s.a[y0][x0+1];
		sn.a[y0][x0+1] = 0;
		return sn;
	}

	private State down(State s) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (s.a[y][x] == 0) {
					x0 = x;
					y0 = 0;
				}
		
		if (y0 == 0) return null;
		State sn = s.clone();
		sn.a[y0][x0] = s.a[y0-1][x0];
		sn.a[y0-1][x0] = 0;
		return sn;
	}

	private State up(State s) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (s.a[y][x] == 0) {
					x0 = x;
					y0 = 0;
				}
		
		if (y0 == 2) return null;
		State sn = s.clone();
		sn.a[y0][x0] = s.a[y0+1][x0];
		sn.a[y0+1][x0] = 0;
		return sn;
	}
}
