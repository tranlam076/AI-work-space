package hailamso;

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
	
	public State move(State state) {
		switch(i) {
		case 0: return up(state);
		case 1: return down(state);
		case 2: return left(state);
		case 3: return right(state);
		}
		return null;
	}

	private State right(State state) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				if (state.a[y][x] == 0) {
					x0 = x;
					y0 = y;
				}
		
		if (x0 == 0) return null;
		State newState = state.clone();
		newState.a[y0][x0] = state.a[y0][x0-1];
		newState.a[y0][x0-1] = 0;
		return newState;
	}

	private State left(State state) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				if (state.a[y][x] == 0) {
					x0 = x;
					y0 = y;
				}
		
		if (x0 == 4) return null;
		State newState = state.clone();
		newState.a[y0][x0] = state.a[y0][x0+1];
		newState.a[y0][x0+1] = 0;
		return newState;
	}

	private State down(State state) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				if (state.a[y][x] == 0) {
					x0 = x;
					y0 = y;
				}
		
		if (y0 == 0) return null;
		State newState = state.clone();
		newState.a[y0][x0] = state.a[y0-1][x0];
		newState.a[y0-1][x0] = 0;
		return newState;
	}

	private State up(State state) {
		int x0 = 0, y0 = 0;
		for (int y = 0; y < 5; y++)
			for (int x = 0; x < 5; x++)
				if (state.a[y][x] == 0) {
					x0 = x;
					y0 = y;
				}
		
		if (y0 == 4) return null;
		State newState = state.clone();
		newState.a[y0][x0] = state.a[y0+1][x0];
		newState.a[y0+1][x0] = 0;
		return newState;
	}
}
