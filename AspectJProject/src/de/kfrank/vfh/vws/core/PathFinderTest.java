package de.kfrank.vfh.vws.core;

import org.junit.Assert;
import org.junit.Test;

public class PathFinderTest {

	@Test
	public void testValidPath1() {
		// generate grid
		NodeGrid grid = new NodeGrid(7, 5, 5, 4, 2, 1);
		blockNode(grid, 1, 1);
		blockHorizontalLine(grid, 2, 1, 5);

		// solve grid with pathfinder
		new PathFinder().find(grid);

		// evaluate correct path
		evaluatePath(grid, new int[] { 5, 4, 5, 3, 6, 2, 5, 1, 4, 1, 3, 1, 2, 1 });
	}

	@Test
	public void testValidPath2() {
		// generate grid
		NodeGrid grid = new NodeGrid(7, 5, 5, 4, 0, 4);
		blockVerticalLine(grid, 1, 1, 4);
		blockHorizontalLine(grid, 2, 1, 5);

		// solve grid with pathfinder
		new PathFinder().find(grid);

		// evaluate correct path
		evaluatePath(grid, new int[] { 5, 4, 5, 3, 6, 2, 5, 1, 4, 0, 3, 0, 2, 0, 1, 0, 0, 1, 0, 2, 0, 3, 0, 4 });
	}

	@Test
	public void testNoPathAvailable() {
		// generate grid
		NodeGrid grid = new NodeGrid(7, 5, 5, 4, 0, 4);
		blockVerticalLine(grid, 1, 0, 4);

		// solve grid with pathfinder
		new PathFinder().find(grid);

		// evaluate correct path
		evaluatePath(grid, null);
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

	private void evaluatePath(NodeGrid grid, int... pathCords) {
		Node target = grid.getTargetNode();
		if (pathCords == null) {
			Assert.assertNull(target.getParent());
		} else {
			Node current = target;
			for (int i = pathCords.length - 2; i >= 0; i -= 2) {
				int x = pathCords[i];
				int y = pathCords[i + 1];
				Assert.assertNotNull(current);
				Assert.assertEquals(x, current.getPosX());
				Assert.assertEquals(y, current.getPosY());
				current = current.getParent();
			}
			Assert.assertNull(current);
		}
	}
}
