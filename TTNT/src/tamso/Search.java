package tamso;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Search {

	public static void main(String[] args) {
		new Search();
	}

	Random rand = new Random();

	public Search() {
		PriorityQueue<State> open = new PriorityQueue<State>(1000000, new Comparator<State>() {
			@Override
			public int compare(State o1, State o2) {
				return (o1.g + o1.h) - (o2.g + o2.h);
			}
		});
		Map<String, State> closed = new HashMap<>();
		State o = null;
		State goal = new State();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				goal.a[y][x] = (x + y * 3 + 1) % 9;
		State start = goal.clone();
		for (int i = 0; i < 1000; i++) {
			State tmp = (new Operator(rand.nextInt(4))).move(start);
			if (tmp != null)
				start = tmp;
		}

		System.out.println("goal");
		goal.print();
		System.out.println("start");
		start.print();

		int count = 0;
		int mem = 0;

		// 1
		start.g = 0;
		start.h = Distance(start, goal);

		open.add(start);
		closed.put(start.key(), start);
		// 2-6
		while (open.size() != 0) {
			count++;
			// 3
			o = open.remove();
			// 4
			if (equal(o, goal)) {
				break;
			}
			// 5
			for (int i = 0; i < 4; i++) {
				Operator op = new Operator(i);
				State child = op.move(o);
				if (child == null)
					continue;
				if (in(child, closed))
					continue;
				child.g = o.g + 1;
				child.h = Distance(child, goal);
				open.add(child);
				if (mem < open.size())
					mem = open.size();
				closed.put(child.key(), child);
				child.father = o;
				child.mom = op;
			}
		}
		print(o);
		System.out.println(count);
		System.out.println(mem);
	}

	private void print(State o) {
		if (o.father != null) {
			print(o.father);
			System.out.println(o.mom.i);
		}
		o.print();
	}

	private boolean in(State child, Map<String, State> list) {
		State s = list.get(child.key());
		if (s == null)
			return false;
		return true;
	}

	private boolean equal(State o, State goal) {
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (o.a[y][x] != goal.a[y][x])
					return false;
		return true;
	}

	private int Distance(State o, State goal) {
		int count = 0;
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 3; i++) {
				int x = (o.a[j][i] - 1) % 3;
				int y = (o.a[j][i] - 1) / 3;
				if (o.a[j][i] != 0)
					count += Math.abs(i - x) + Math.abs(j - y);
			}
		}
		return count;
	}

}
