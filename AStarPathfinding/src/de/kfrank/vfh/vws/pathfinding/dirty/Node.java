package de.kfrank.vfh.vws.pathfinding.dirty;

public class Node implements Comparable<Node> {

	private int gCost;

	private int hCost;

	private int fCost;

	private boolean blocked;

	private Node parent;

	private int posX;

	private int posY;

	public Node(int x, int y, int endX, int endY) {
		// set own position
		this.posX = x;
		this.posY = y;

		// calculate hCost
		int dx = Math.abs(x - endX);
		int dy = Math.abs(y - endY);
		hCost = Math.min(dx, dy) * 14;
		hCost += Math.abs(dx - dy) * 10;
	}

	public int getGCost() {
		return gCost;
	}

	public void setGCost(int gCost) {
		this.gCost = gCost;
	}

	public void setHCost(int hCost) {
		this.hCost = hCost;
	}

	public int getHCost() {
		return hCost;
	}

	public void setfCost(int fCost) {
		this.fCost = fCost;
	}

	public int getFCost() {
		return fCost;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	@Override
	public int compareTo(Node o) {
		if (fCost == o.fCost) {
			return hCost - o.hCost;
		}
		return fCost - o.fCost;
	}
}
