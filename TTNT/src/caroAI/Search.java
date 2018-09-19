package caroAI;

import java.util.Scanner;

public class Search {
	private Scanner sc;

	public static void main(String[] args) {
		new Search();
	}

	public Search() {
		sc = new Scanner(System.in);
		int player = 1;
		int turn = 0;
		int d = 5;
		State s = new State();
		while (true) {
			if (turn % 2 + 1 == player) {
				// User
				State child = null;
				while (child == null) {
					System.out.println("type.....");
					int x = sc.nextInt();
					int y = sc.nextInt();
					child = new Operator(x, y).Move(s);
				}
				s = child;
				
				if (Win(s))  {
					System.out.println("Player Won!!");
					break;
				}
				
			} else {
				// AI
				System.out.println("AI turn!");
				s = MiniMax(s, d);
			}
			s.Print();
			turn++;
		}
	}

	private boolean Win(State s) {
		
		return false;
	}

	private State MiniMax(State s, int d) {
		return AlphaBeta(s, d, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private State AlphaBeta(State s, int d, int a, int b) {
		if (isEndNode(s) || d == 0) {
			return Value(s);
		}

		State minState = null;
		for (int i = 0; i < s.N; i++)
			for (int j = 0; j < s.N; j++) {
				State child = new Operator(i, j).Move(s);
				if (child == null)
					continue;
				State tmp = AlphaBeta(child, d - 1, -b, -a);
				if (a < -tmp.value) {
					a = -tmp.value;
					minState = child;
					minState.value = a;
				}

				if (a >= b) {
					i = s.N;
					j = s.N;
				}
			}
		s.value = a;
		return s;
	}

	private State Value(State s) {
		s.value = 1;
		return s;
	}

	private boolean isEndNode(State s) {
		for (int i = 0; i < s.N; i++) {
			if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][0] && s.a[i][0] == s.a[i][2])
				return true;
			if (s.a[0][i] != 0 && s.a[0][i] == s.a[1][i] && s.a[2][0] == s.a[0][i])
				return true;
		}
		return false;
	}

}
