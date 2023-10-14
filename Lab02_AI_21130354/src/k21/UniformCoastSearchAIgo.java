package k21;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCoastSearchAIgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				int rs = Double.compare(o1.getPathCost(), o2.getPathCost());
				if (rs == 0)
					return o1.getLabel().compareToIgnoreCase(o2.getLabel());
				return rs;
			}
		});
		HashSet<Node> visited = new HashSet<Node>();
		frontier.add(root);
		visited.add(root);

		ArrayList<String> path = null;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {

				path = (ArrayList<String>) NodeUtils.printPath(currentNode);
				
				for (String pat : path) {
					System.out.print(pat + "--");
				}
				return currentNode;
			}

			List<Edge> edge = currentNode.getChildren();
			for (Edge ed : edge) {
				Node nod = ed.getEnd();
				if (!visited.contains(nod)) {
					visited.add(nod);
					nod.setPathCost(ed.getWeight() + currentNode.getPathCost());
					frontier.add(nod);
					nod.setParent(currentNode);

				} else if (nod.getPathCost() > ed.getWeight() + currentNode.getPathCost()) {
					nod.setPathCost(ed.getWeight() + currentNode.getPathCost());
					nod.setParent(currentNode);

				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				int rs = Double.compare(o1.getPathCost(), o2.getPathCost());
				if (rs == 0)
					return o1.getLabel().compareToIgnoreCase(o2.getLabel());
				return rs;
			}
		});
		HashSet<Node> visited = new HashSet<Node>();
		Node startNode = findNode(root, start);
		startNode.setPathCost(0);
		startNode.setParent(null);
		if (startNode == null) {
			return null;
		}
		visited.add(startNode);
		frontier.add(startNode);
		ArrayList<String> path = null;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {

				path = (ArrayList<String>) NodeUtils.printPath(currentNode);
				System.out.println(path.size());
				for (String pat : path) {

					System.out.print(pat + "--");
				}
				return currentNode;
			}
			System.out.println(currentNode.getPathCost());
			List<Edge> edge = currentNode.getChildren();
			for (Edge ed : edge) {
				Node nod = ed.getEnd();
				if (!visited.contains(nod)) {
					visited.add(nod);
					nod.setPathCost(ed.getWeight() + currentNode.getPathCost());
					frontier.add(nod);
					nod.setParent(currentNode);

				} else if (nod.getPathCost() > ed.getWeight() + currentNode.getPathCost()) {
					nod.setPathCost(ed.getWeight() + currentNode.getPathCost());
					nod.setParent(currentNode);

				}
			}
		}
		System.out.println("Khong co goal");

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

		System.out.println(nodeD.getPathCost());

		ISearchAlgo us = new UniformCoastSearchAIgo();
		Node result = us.execute(nodeS, "G");
		Node result2 = us.execute(nodeS, "A", "H");

		Node nodeStart = new Node("Start");
		Node g2_nodeA = new Node("A");
		Node g2_nodeB = new Node("B");
		Node g2_nodeC = new Node("C");
		Node g2_nodeD = new Node("D");
		Node g2_nodeE = new Node("E");
		Node g2_nodeF = new Node("F");
		Node g2_nodeG = new Node("G");
		Node g2_nodeH = new Node("H");
		Node g2_nodeP = new Node("P");
		Node g2_nodeQ = new Node("Q");
		Node g2_nodeR = new Node("R");

		nodeStart.addEdge(g2_nodeD, 3);

	}
}
