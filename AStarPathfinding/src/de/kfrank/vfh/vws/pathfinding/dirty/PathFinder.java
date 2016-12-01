package de.kfrank.vfh.vws.pathfinding.dirty;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {

	public static void find(NodeGrid grid) {
		// initiate lists for nodes to examine and already examined nodes
		List<Node> openNodes = new LinkedList<>();
		List<Node> closedNodes = new LinkedList<>();

		// first node to examine is the start node
		openNodes.add(grid.getStartNode());

		// examine until end node found or no nodes to examine remaining
		while (!openNodes.isEmpty()) {
			// mark the first node in the list as already examined
			Node current = openNodes.get(0);
			openNodes.remove(current);
			closedNodes.add(current);

			// return if the end node was found
			if (current.equals(grid.getEndNode())) {
				return;
			}

			// loop through all neighbour nodes
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					Node node = grid.getNode(current.getPosX() + x, current.getPosY() + y);
					// continue if neighbour node is invalid
					if ((x == 0 && y == 0) || node == null || node.isBlocked() || closedNodes.contains(node)) {
						continue;
					}

					// calculate path length to neighbour
					int pathLength = Math.abs(x) + Math.abs(y) == 2 ? 14 : 10;
					pathLength += current.getGCost();
					boolean neighbourNotInOpen = !openNodes.contains(node);
					
					// check if neighbour was not examined or new path length is shorter than old one 
					if (neighbourNotInOpen || node.getGCost() > pathLength) {
						// calculate new g and f costs and set new parent node
						node.setGCost(pathLength);
						node.setfCost(node.getGCost() + node.getHCost());
						node.setParent(current);
						
						// add neighbour to the list with to be examined nodes
						if (neighbourNotInOpen) {
							openNodes.add(node);
						}
					}
				}
			}
			
			// sort nodes to be examined so that most promising node is at the beginning of the list
			Collections.sort(openNodes);
		}
	}
}