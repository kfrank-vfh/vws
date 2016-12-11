package de.kfrank.vfh.vws.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import de.kfrank.vfh.vws.core.Node;
import de.kfrank.vfh.vws.core.NodeGrid;
import de.kfrank.vfh.vws.core.PathFinderTest;
import org.junit.Assert;

@Aspect
@SuppressWarnings("unused")
public class CheckAnnotationAspect {

	@Around("call(public Node NodeGrid.getNode(int, int)) && args(x, y) &&  target(grid) && if(false)")
	public Node checkOutOfBounds(ProceedingJoinPoint thisJoinPoint, NodeGrid grid, int x, int y) {
		Node[][] g = grid.getGrid();
		if (x < 0 || y < 0 || x >= g.length || y >= g[0].length) {
			return null;
		}
		try {
			return (Node) thisJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	@Before("call(private void PathFinderTest.evaluatePath(NodeGrid, int...)) && args(grid, pathCords)  && if(false)")
	public void checkPathCords(NodeGrid grid, int... pathCords) {
		if (pathCords != null && pathCords.length % 2 == 1) {
			Assert.fail("uneven count of path cords not valid");
		}
	}
}
