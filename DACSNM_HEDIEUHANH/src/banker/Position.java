package banker;

import java.awt.Point;

public class Position {
	Point start;
	Point end;
	
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Position(Point start, Point end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
}
