package de.kfrank.vfh.vws.pathfinding.clean;

public class Node implements Comparable<Node> {

	private static final int ORTHOGONAL_PATH_COSTS = 10;

	private static final int DIAGONAL_PATH_COSTS = 14;

	private int previousCosts;

	private int futureCosts;

	private int estimateCosts;

	private boolean blocked;

	private Node parent;

	private int posX;

	private int posY;

	public Node(int posX, int posY, int targetPosX, int targetPosY) {
		setPosX(posX);
		setPosY(posY);
		setFutureCosts(calculateDistanceTo(targetPosX, targetPosY));
	}

	private void calculatePreviousCosts() {
		int distanceToParent = calculateDistanceTo(parent.getPosX(), parent.getPosY());
		setPreviousCosts(parent.getPreviousCosts() + distanceToParent);
	}
	
	private int calculateDistanceTo(int x, int y) {
		int horizontalDistance = Math.abs(getPosX() - x);
		int verticalDistance = Math.abs(getPosY() - y);
		int diagonalCosts = Math.min(horizontalDistance, verticalDistance) * DIAGONAL_PATH_COSTS;
		int orthogonalCosts = Math.abs(horizontalDistance - verticalDistance) * ORTHOGONAL_PATH_COSTS;
		return diagonalCosts + orthogonalCosts;
	}

	private void calculateEstimateCosts() {
		setEstimateCosts(previousCosts + futureCosts);
	}

	private int getPreviousCosts() {
		return previousCosts;
	}

	private void setPreviousCosts(int previousCosts) {
		this.previousCosts = previousCosts;
	}

	private void setFutureCosts(int futureCosts) {
		this.futureCosts = futureCosts;
	}

	private void setEstimateCosts(int estimateCosts) {
		this.estimateCosts = estimateCosts;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	/**
	 * Methode nur zum Test
	 */
	Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
		calculatePreviousCosts();
		calculateEstimateCosts();
	}
	
	public boolean isParentWithShorterPath(Node childNode) {
		int distanceToChild = calculateDistanceTo(childNode.getPosX(), childNode.getPosY());
		return distanceToChild + getPreviousCosts() < childNode.getPreviousCosts();
	}

	public int getPosX() {
		return posX;
	}

	private void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	private void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public int compareTo(Node o) {
		if (estimateCosts == o.estimateCosts) {
			return futureCosts - o.futureCosts;
		}
		return estimateCosts - o.estimateCosts;
	}
}
