package caroAI;

public class Operator {
	int x, y;

	public Operator(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public State Move(State s) {
		if (x < 0 || x >= s.N)
			return null;
		if (y < 0 || y >= s.N)
			return null;
		if (s.a[x][y] != 0)
			return null;
		int count = 0;
		for (int i = 0; i < s.N; i++)
			for (int j = 0; j < s.N; i++)
				if (s.a[i][j] != 0)
					count++;
		State tmp = s.Clone();

		if (count % 2 == 0)
			tmp.a[x][y] = 1;
		else
			tmp.a[x][y] = 2;
		return tmp;
	}
}
