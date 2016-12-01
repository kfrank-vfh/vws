package de.kfrank.vfh.vws.pathfinding.dirty;

public class NodeGrid {

	private Node[][] grid;

	private Node endNode;

	private Node startNode;

	public NodeGrid(int width, int height, int startX, int startY, int endX, int endY) {
		// initiate grid with nodes
		grid = new Node[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Node(x, y, endX, endY);
			}
		}
		// set start and end nodes
		startNode = grid[startX][startY];
		endNode = grid[endX][endY];
	}

	public Node getNode(int x, int y) {
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
			// return null if position out of bounds
			return null;
		}
		return grid[x][y];
	}

	public Node getEndNode() {
		return endNode;
	}

	public Node getStartNode() {
		return startNode;
	}
}
