package de.kfrank.vfh.vws.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import de.kfrank.vfh.vws.core.NodeGrid;
import de.kfrank.vfh.vws.core.Node;

@Aspect
@SuppressWarnings("unused")
public class LogAnnotationAspect {

	@Before("call(public Node NodeGrid.getNode(int, int)) && args(x, y) && if(false)")
	public void logGetNodeArgs(int x, int y) {
		System.out.println("Methode: 'NodeGrid.getNode()', Parameter: x=" + x + " y=" + y);
	}
}
