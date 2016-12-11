package de.kfrank.vfh.vws.core;

import java.util.LinkedList;
import java.util.List;

public class NodeGrid {

	private Node[][] grid;

	private Node targetNode;

	private Node startNode;

	public NodeGrid(int width, int height, int startX, int startY, int targetX, int targetY) {
		initiateGrid(width, height, targetX, targetY);
		setStartNode(getNode(startX, startY));
		setTargetNode(getNode(targetX, targetY));
	}

	private void initiateGrid(int width, int height, int targetX, int targetY) {
		grid = new Node[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				grid[x][y] = new Node(x, y, targetX, targetY);
			}
		}
	}

	public Node[][] getGrid() {
		return grid;
	}

	public Node getNode(int x, int y) {
		return grid[x][y];
	}

	public List<Node> getValidNeighbourNodes(Node node) {
		List<Node> neighbourNodes = new LinkedList<>();
		for (int horizontalTranslation = -1; horizontalTranslation <= 1; horizontalTranslation++) {
			for (int verticalTranslation = -1; verticalTranslation <= 1; verticalTranslation++) {
				Node neighbourNode = getNode(node.getPosX() + horizontalTranslation, node.getPosY() + verticalTranslation);
				if (isValidNeighbourNode(node, neighbourNode)) {
					neighbourNodes.add(neighbourNode);
				}
			}
		}
		return neighbourNodes;
	}

	private boolean isValidNeighbourNode(Node originNode, Node neighbourNode) {
		return !(neighbourNode == null || neighbourNode.isBlocked() || originNode.equals(neighbourNode));
	}

	public Node getTargetNode() {
		return targetNode;
	}

	private void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}

	public Node getStartNode() {
		return startNode;
	}

	private void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
}
