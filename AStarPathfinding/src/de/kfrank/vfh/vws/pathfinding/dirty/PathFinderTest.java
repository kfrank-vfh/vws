package de.kfrank.vfh.vws.pathfinding.dirty;

import de.kfrank.vfh.vws.pathfinding.dirty.Node;

public class PathFinderTest {

	public static void main(String[] args) {
		NodeGrid grid = generateGrid2();
		PathFinder.find(grid);
		sysoutSolution(grid);
	}
	
	private static NodeGrid generateGrid1() {
		NodeGrid grid = new NodeGrid(7, 5, 5, 4, 2, 1);
		blockNode(grid, 1, 1);
		blockHorizontalLine(grid, 2, 1, 5);
		return grid;
	}
	
	private static NodeGrid generateGrid2() {
		NodeGrid grid = new NodeGrid(7, 5, 5, 4, 0, 4);
		blockVerticalLine(grid, 1, 1, 4);
		blockHorizontalLine(grid, 2, 1, 5);
		return grid;
	}
	
	private static void blockNode(NodeGrid grid, int x, int y) {
		Node node = grid.getNode(x, y);
		if (node != null) {
			node.setBlocked(true);
		}
	}

	private static void blockVerticalLine(NodeGrid grid, int x, int fromY, int toY) {
		for (int y = fromY; y <= toY; y++) {
			blockNode(grid, x, y);
		}
	}

	private static void blockHorizontalLine(NodeGrid grid, int y, int fromX, int toX) {
		for (int x = fromX; x <= toX; x++) {
			blockNode(grid, x, y);
		}
	}
	
	private static void sysoutSolution(NodeGrid grid) {
		Node endNode = grid.getEndNode();
		if (endNode.getParent() == null) {
			System.out.println("no solution found");
		} else {
			StringBuilder builder = new StringBuilder();
			Node node = endNode;
			while (node != null) {
				if (builder.length() > 0) {
					builder.insert(0, "; ");
				}
				builder.insert(0, node.getPosY());
				builder.insert(0, ",");
				builder.insert(0, node.getPosX());
				node = node.getParent();
			}
			System.out.println(builder.toString());
		}
	}
}
