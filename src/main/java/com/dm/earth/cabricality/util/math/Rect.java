package com.dm.earth.cabricality.util.math;

public record Rect(Node lu, Node ld, Node rd, Node ru) {
	public Rect() {
		this(new Node(), new Node(), new Node(), new Node());
	}

	public Rect(Node lu, Node rd) {
		this(lu, new Node(lu.x(), rd.y()), rd, new Node(rd.x(), lu.y()));
	}

	public boolean contains(Node node) {
		return node.getCross(ld, lu) * node.getCross(ru, rd) >= 0 && node.getCross(lu, ru) * node.getCross(rd, ld) >= 0;
	}

	public Rect rotate(Node origin, double clockwiseDegree) {
		return new Rect(lu.rotate(origin, clockwiseDegree), ld.rotate(origin, clockwiseDegree), rd.rotate(origin, clockwiseDegree), ru.rotate(origin, clockwiseDegree));
	}
}
