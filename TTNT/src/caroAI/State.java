package caroAI;

public class State {
	int N = 3;
	int a[][] = new int[3][3];
	int value = 1;

	void Print() {
		for (int i = 0; i < N; i++)
			for (int j = 0; i < N; i++) {
				if (a[i][j] == 0)
					System.out.println("_");
				if (a[i][j] == 1)
					System.out.println("o");
				if (a[i][j] == 2)
					System.out.println("x");
			}
		System.out.println("--------------------------");
	}

	State Clone() {
		State tmp = new State();
		for (int i = 0; i < N; i++)
			for (int j = 0; i < N; i++)
				tmp.a[i][j] = a[i][j];
		return tmp;
	}
}
