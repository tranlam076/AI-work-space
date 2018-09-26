package caroAI;

import java.util.Scanner;

public class TicTacToe {
	private Scanner sc;

	public static void main(String[] args) {
		new TicTacToe();
	}

	public TicTacToe() {
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
					System.out.println("Type.....");
					int x = sc.nextInt();
					int y = sc.nextInt();
					child = new Operator(x, y).Move(s);
				}
				System.out.println("out");
				s = child;

				if (Win(s)) {
					System.out.println("Player Won!!");
					break;
				}

			} else {
				// AI
				System.out.println("AI turn!");
				
				int min = Integer.MAX_VALUE;
				State minChild = null;
				for (int i =0; i<s.N; i++) {
					for (int j =0; j<s.N; j++) {
						State child = new Operator(i, j).Move(s;
						if (child == null) {
							continue;
						}
						int tmp = MiniMax(s, d, false)
					}
				}
				s = MiniMax(s, d);
			}
			s.Print();
			turn++;
		}
	}

	private boolean Win(State s) {

		return false;
	}

	private State MiniMax(State s, int d, boolean MP) {
		return AlphaBeta(s, d, Integer.MIN_VALUE, Integer.MAX_VALUE, MP);
	}

	private State AlphaBeta(State s, int d, int a, int b, boolean MP) {
		if (isEndNode(s) || d == 0) {
			return Value(s);
		}

		State minState = null;
lb:		for (int i = 0; i < s.N; i++)
			for (int j = 0; j < s.N; j++) {
				State child = new Operator(i, j).Move(s);
				if (child == null) {
					continue;
				} 
				
				int tmp = AlphaBeta(child, d-1, a, b, MP);
				
				if (MP) {
					a =tmp;
				}
				
				if (a>=b) break lb;
				else {
					if (b>tmp) b =tmp;
				}
				
				
				if (a>b)
					break lb;
				
			}
		return minState;
	}

	private int Value(State s) {
		if (isEndNode(s)) {
			if (Myturn(s)) return 1;
			else return -1;
		}
		return 0;
	}

	private boolean Myturn(State s) {
		int count = 0;
		for (int i =0; i<s.N; i++) {
			for (int j =0; j<s.N; j++) {
				if (s.a[i][j]==0) count ++;
			}
		}
		return false;
	}

	private boolean isEndNode(State s) {
		for (int i = 0; i < s.N; i++) {
			if (s.a[i][0] != 0 && s.a[i][0] == s.a[i][1] && s.a[i][0] == s.a[i][2])
				return true;
			if (s.a[0][i] != 0 && s.a[0][i] == s.a[1][i] && s.a[2][0] == s.a[0][i])
				return true;
		}
		if (s.a[0][0] != 0 && s.a[0][0] == s.a[1][1] && s.a[0][0] == s.a[2][2])
			return true;
		if (s.a[1][1] != 0 && s.a[0][2] == s.a[1][1] && s.a[1][1] == s.a[2][0])
			return true;
		return false;
	}
}
