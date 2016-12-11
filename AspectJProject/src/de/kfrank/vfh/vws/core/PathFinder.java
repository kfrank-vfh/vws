package de.kfrank.vfh.vws.core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {

	private List<Node> nodesToExamine;
	
	private List<Node> examinedNodes;
	
	public void find(NodeGrid grid) {
		initiateLists();
		nodesToExamine.add(grid.getStartNode());
		while (!nodesToExamine.isEmpty()) {
			Node currentNode = markAndGetFirstNode();
			if (currentNode.equals(grid.getTargetNode())) {
				return;
			}
			for (Node neighbourNode : grid.getValidNeighbourNodes(currentNode)) {
				if (examinedNodes.contains(neighbourNode)) {
					continue;
				}
				boolean examineNeighbourNode = !nodesToExamine.contains(neighbourNode); 
				if (examineNeighbourNode || currentNode.isParentWithShorterPath(neighbourNode)) {
					neighbourNode.setParent(currentNode);
					if (examineNeighbourNode) {
						nodesToExamine.add(neighbourNode);
					}
				}
			}
		}
		Collections.sort(nodesToExamine);
	}
	
	private void initiateLists() {
		nodesToExamine = new LinkedList<>();
		examinedNodes = new LinkedList<>();
	}
	
	private Node markAndGetFirstNode() {
		Node firstNode = nodesToExamine.get(0);
		nodesToExamine.remove(firstNode);
		examinedNodes.add(firstNode);
		return firstNode;
	}
}