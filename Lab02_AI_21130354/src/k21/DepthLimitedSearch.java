package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearch {
	public static Node execute(Node root, String goal, int limitedDepth) {

		Stack<Node> frontier = new Stack<Node>();
		HashSet<Node> visited = new HashSet<Node>();
		visited.add(root);
		frontier.push(root);
		ArrayList<String> path = null;

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if (currentNode.getLabel().equals(goal)) {

				path = (ArrayList<String>) NodeUtils.printPath(currentNode);
				for (String pat : path) {
					System.out.print(pat + "--");
				}
				return currentNode;
			}

			List<Node> children = currentNode.getChildrenNodes();
			children.sort(new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o2.getLabel().compareTo(o1.getLabel());
				}
			});
			
			if(currentNode.getDepth() < limitedDepth) {
				
				for (Node child : children) {
					if (!visited.contains(child)) {
						child.setDepth(currentNode.getDepth() + 1);
						frontier.push(child);
						visited.add(child);
						child.setParent(currentNode);
					}
				}
			}else {
				System.out.println("Khong Tim Thay Goal");
				return null;
			}
		}
		System.out.println("Khong co goal");
		return null;

	}
	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);
		
		DepthLimitedSearch dls = new DepthLimitedSearch();
		Node n = dls.execute(nodeS, "G", 4);
	}
}
