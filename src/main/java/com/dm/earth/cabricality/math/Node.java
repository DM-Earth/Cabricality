package com.dm.earth.cabricality.math;

public record Node(double x, double y) {
	public Node() {
		this(0, 0);
	}

	double getCross(Node p1, Node p2) {
		return (p2.x - p1.x) * (this.y - p1.y) - (this.x - p1.x) * (p2.y - p1.y);
	}

	Node rotate(Node origin, double clockwiseDegree) {
		return new Node(
				(this.x - origin.x) * Math.cos(Math.toRadians(clockwiseDegree)) - (this.y - origin.y) * Math.sin(Math.toRadians(clockwiseDegree)) + origin.x,
				(this.x - origin.x) * Math.sin(Math.toRadians(clockwiseDegree)) + (this.y - origin.y) * Math.cos(Math.toRadians(clockwiseDegree)) + origin.y
		);
	}

	Node append(Node node) {
		return new Node(this.x + node.x, this.y + node.y);
	}

	public Node scale(Node origin, double scale) {
		return new Node((this.x - origin.x) * scale + origin.x, (this.y - origin.y) * scale + origin.y);
	}
}
