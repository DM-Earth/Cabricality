package com.dm.earth.cabricality.math;

import net.minecraft.client.MinecraftClient;

public record Rect(Node lu, Node ld, Node rd, Node ru) {
	public Rect() {
		this(new Node(), new Node(MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledHeight()));
	}

	public Rect(Node lu, Node rd) {
		this(lu, new Node(lu.x(), rd.y()), rd, new Node(rd.x(), lu.y()));
	}

	public Rect(double x, double y, double width, double height) {
		this(new Node(x, y), new Node(x + width, y + height));
	}

	public Rect min(Rect other) {
		return new Rect(
				new Node(Math.min(lu.x(), other.lu.x()), Math.min(lu.y(), other.lu.y())),
				new Node(Math.max(ld.x(), other.ld.x()), Math.max(ld.y(), other.ld.y())),
				new Node(Math.max(rd.x(), other.rd.x()), Math.max(rd.y(), other.rd.y())),
				new Node(Math.min(ru.x(), other.ru.x()), Math.min(ru.y(), other.ru.y()))
		);
	}

	public Rect max(Rect other) {
		return new Rect(
				new Node(Math.max(lu.x(), other.lu.x()), Math.max(lu.y(), other.lu.y())),
				new Node(Math.min(ld.x(), other.ld.x()), Math.min(ld.y(), other.ld.y())),
				new Node(Math.min(rd.x(), other.rd.x()), Math.min(rd.y(), other.rd.y())),
				new Node(Math.max(ru.x(), other.ru.x()), Math.max(ru.y(), other.ru.y()))
		);
	}

	public boolean contains(Node node) {
		return node.getCross(ld, lu) * node.getCross(ru, rd) >= 0 && node.getCross(lu, ru) * node.getCross(rd, ld) >= 0;
	}

	public Node center() {
		return new Node(((lu.x() + ld.x()) / 2 + (ru.x() + rd.x()) / 2) / 2, ((lu.y() + ld.y()) / 2 + (ru.y() + rd.y()) / 2) / 2);
	}

	public Rect rotate(Node origin, double clockwiseDegree) {
		return new Rect(lu.rotate(origin, clockwiseDegree), ld.rotate(origin, clockwiseDegree), rd.rotate(origin, clockwiseDegree), ru.rotate(origin, clockwiseDegree));
	}

	public Rect scale(double scaling) {
		return new Rect(lu.scale(center(), scaling), ld.scale(center(), scaling), rd.scale(center(), scaling), ru.scale(center(), scaling));
	}

	public Rect scale(Node origin, double scaling) {
		return new Rect(lu.scale(origin, scaling), ld.scale(origin, scaling), rd.scale(origin, scaling), ru.scale(origin, scaling));
	}

	public Rect expand(double expand) {
		return new Rect(new Node(lu.x() - expand, lu.y() - expand), new Node(ld.x() - expand, ld.y() + expand), new Node(rd.x() + expand, rd.y() + expand), new Node(ru.x() + expand, ru.y() - expand));
	}

	public Rect interpolate(Rect rect, double ratio) {
		return new Rect(lu.scale(rect.lu, ratio), ld.scale(rect.ld, ratio), rd.scale(rect.rd, ratio), ru.scale(rect.ru, ratio));
	}
}
