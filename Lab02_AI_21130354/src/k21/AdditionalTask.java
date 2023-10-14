package k21;

public class AdditionalTask {
	public static void main(String[] args) {
		Node nodeStart = new Node("START");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeGOAL = new Node("GOAL");
		Node nodeH = new Node("H");
		Node nodeP = new Node("P");
		Node nodeQ = new Node("Q");
		Node nodeR = new Node("R");
		
		nodeStart.addEdge(nodeD, 3);
		nodeStart.addEdge(nodeE, 9);
		nodeStart.addEdge(nodeP, 1);
		
		nodeD.addEdge(nodeB, 1);
		nodeD.addEdge(nodeC, 8);
		nodeD.addEdge(nodeE, 2);
		
		nodeB.addEdge(nodeA, 2);
		nodeC.addEdge(nodeA, 2);
		
		nodeE.addEdge(nodeH, 1);
		nodeE.addEdge(nodeR, 9);
		
		nodeH.addEdge(nodeP, 4);
		nodeH.addEdge(nodeQ, 4);
		
		nodeP.addEdge(nodeQ, 15);
		nodeQ.addEdge(nodeR, 3);
		nodeR.addEdge(nodeF, 5);
		
		nodeF.addEdge(nodeGOAL, 5);
		nodeF.addEdge(nodeC, 5);
		System.out.println("Test Bread First Search: ");
		BreadthFirstSearchAIgo bsa = new BreadthFirstSearchAIgo();
		Node goal_bsa = bsa.execute(nodeStart, "GOAL");
		Node goal_bsaTree = bsa.executeTree(nodeStart, "GOAL");
		
		System.out.println("Test Depth First Search: ");
		DepthFirstSearchAIgo dfs = new DepthFirstSearchAIgo();
		Node goal_dfs = dfs.execute(nodeStart, "GOAL");
		System.out.println("Tree: ");
		Node goal_dfsTree = dfs.executeTree(nodeStart, "GOAL");
		System.out.println();
		System.out.println("Test Uniform Coast Search: ");
		UniformCoastSearchAIgo ucs = new UniformCoastSearchAIgo();
		Node goal_ucs = ucs.execute(nodeStart, "GOAL");
		
		System.out.println("Test Depth Limited Search: ");
		DepthLimitedSearch dls = new DepthLimitedSearch();
		
		Node goal_dls = dls.execute(nodeStart, "GOAL", 5);
	}
}
