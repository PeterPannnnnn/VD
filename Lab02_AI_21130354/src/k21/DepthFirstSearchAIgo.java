package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearchAIgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
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

			for (Node child : children) {
				if (!visited.contains(child)) {
					frontier.push(child);
					visited.add(child);
					child.setParent(currentNode);
				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		Set<Node> visited = new HashSet<>();
		ArrayList<String> path = null;

		Node startNode = findNode(root, start);
		if (startNode == null) {
			return null;
		}

		frontier.push(startNode);
		visited.add(startNode);

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();

			if (currentNode.getLabel().equals(goal)) {

				path = (ArrayList<String>) NodeUtils.printPath(currentNode);
				System.out.println(path.size());
				for (String pat : path) {
					System.out.print(pat + "--");
				}
				System.out.println(frontier.size());

				return currentNode;
			}
			
			List<Node> children = currentNode.getChildrenNodes();
			children.sort(new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o2.getLabel().compareTo(o1.getLabel());
				}
			});
			for (Node child : children) {
				if (!visited.contains(child)) {
					frontier.push(child);
					visited.add(child);
					child.setParent(currentNode);
				}
			}
		}
		return null;
	}

	public Node executeTree(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
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

			for (Node child : children) {
				if (!frontier.contains(child)) {
					frontier.push(child);
					child.setParent(currentNode);
				} else {
					Node n = new Node(child.getLabel());
					frontier.add(n);
					n.setParent(currentNode);
				}
			}
		}

		return null;
	}

	private Node findNode(Node node, String label) {
		if (node.getLabel().equals(label)) {
			return node;
		}
		for (Node nodeChild : node.getChildrenNodes()) {
			Node foundNode = findNode(nodeChild, label);
			if (foundNode != null) {
				return foundNode;
			}
		}

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
		ISearchAlgo algo1 = new DepthFirstSearchAIgo();
		Node result = algo1.execute(nodeS, "G");

		DepthFirstSearchAIgo dep = new DepthFirstSearchAIgo();
		Node node = dep.executeTree(nodeS, "G");

	}

}
