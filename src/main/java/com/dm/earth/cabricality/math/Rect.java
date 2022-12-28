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

	public boolean contains(Node node) {
		return node.getCross(ld, lu) * node.getCross(ru, rd) >= 0 && node.getCross(lu, ru) * node.getCross(rd, ld) >= 0;
	}

	public Node center() {
		return new Node(((lu.x() + rd.x()) / 2 + (ru.x() + rd.x()) / 2), ((lu.y() + rd.y()) / 2 + (ru.y() + rd.y()) / 2));
	}

	public Rect rotate(Node origin, double clockwiseDegree) {
		return new Rect(lu.rotate(origin, clockwiseDegree), ld.rotate(origin, clockwiseDegree), rd.rotate(origin, clockwiseDegree), ru.rotate(origin, clockwiseDegree));
	}

	public Rect scale(double scale) {
		return new Rect(lu.scale(center(), scale), ld.scale(center(), scale), rd.scale(center(), scale), ru.scale(center(), scale));
	}

	public Rect scale(Node origin, double scale) {
		return new Rect(lu.scale(origin, scale), ld.scale(origin, scale), rd.scale(origin, scale), ru.scale(origin, scale));
	}

	public Rect interpolate(Rect rect, double ratio) {
		return new Rect(lu.interpolate(rect.lu, ratio), ld.interpolate(rect.ld, ratio), rd.interpolate(rect.rd, ratio), ru.interpolate(rect.ru, ratio));
	}
}
