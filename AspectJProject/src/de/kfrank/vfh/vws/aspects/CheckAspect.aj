package de.kfrank.vfh.vws.aspects;

import org.junit.Assert;

import de.kfrank.vfh.vws.core.Node;
import de.kfrank.vfh.vws.core.NodeGrid;
import de.kfrank.vfh.vws.core.PathFinderTest;

public aspect CheckAspect {

	Node around(NodeGrid grid, int x, int y) : call(public Node NodeGrid.getNode(int, int)) && args(x, y) &&  target(grid) {
		Node[][] g = grid.getGrid();
		if (x < 0 || y < 0 || x >= g.length || y >= g[0].length) {
			return null;
		}
		return proceed(grid, x, y);
	}

	before(NodeGrid grid, int[] pathCords) : call(private void PathFinderTest.evaluatePath(NodeGrid, int...)) && args(grid, pathCords) {
		if (pathCords != null && pathCords.length % 2 == 1) {
			Assert.fail("uneven count of path cords not valid");
		}
	}
}
