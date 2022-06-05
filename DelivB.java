import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class DelivB {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	private int time = 1;

	//Constructor - DO NOT MODIFY
	public DelivB(File in, Graph gr) {
		inputFile = in;
		graph = gr;

		// Set up for writing to a file
		try {
			// Use input file name to create output file in the same location
			String inputFileName = inputFile.toString();
			String outputFileName = inputFileName.substring(0, inputFileName.length() - 4).concat("_out.txt");
			outputFile = new File(outputFileName);

			// A Printwriter is an object that can write to a file
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		
		// Calls the method that will do the work of deliverable B
		runDelivB();

		output.flush();
	}

	//*********************************************************************************
	//               This is where your work starts
	private void runDelivB() {
		//Delete these lines when you add functionality
		//to exploring unexplored nodes not connected to the graph.
		
		
		DFS(graph);
		
		output.println("DFS of graph");
		output.println("\nNode   Disc   Finish");
		
		System.out.println("DFS of graph");
		System.out.println("\nNode   Disc   Finish");
		for(Node someNode:graph.getNodeList()) {
			System.out.println(someNode.getAbbrev() + "      " + someNode.getStartTime() + "      " + someNode.getFinishTime());
			output.println(someNode.getAbbrev() + "      " + someNode.getStartTime() + "      " + someNode.getFinishTime());
		}
		
		output.println("\nEdge Classification:\n");
		output.println("Edge   Type");
		
		System.out.println("\nEdge Classification:\n");
		System.out.println("Edge   Type");
		
		for (Node someNode: graph.getNodeList()) {
			for(Edge outEdge: someNode.getOutgoingEdges()) {
				System.out.print(someNode.getAbbrev() + "->"+ outEdge.getHead().getAbbrev() + "   ");
				System.out.println(outEdge.getEdgeType());
				
				output.print(someNode.getAbbrev() + "->"+ outEdge.getHead().getAbbrev() + "   ");
				output.println(outEdge.getEdgeType());
			}
		}
		
		
	}
	
	
	void DFS(Graph graph) {
		boolean untraveledNodesRemain = true;
		Node startNode = null;
		for(Node someNode:graph.getNodeList()) {
			if(someNode.getValue().equals("S")) {
				startNode = someNode;
			}
		}
		
		DFS_Visit(startNode);
		
		while(untraveledNodesRemain){ //move this shit outside DFS maybe
			ArrayList<Node> untraveledNodeList = new ArrayList<Node>();
			for(Node someNode:graph.getNodeList()) {
				if(someNode.getStartTime() == 0) {
					untraveledNodeList.add(someNode);
				}
			}
			Collections.sort(untraveledNodeList, new NodeComparerAlphabetical());
			
			if(untraveledNodeList.size() > 0) {
				DFS_Visit(untraveledNodeList.get(0));
			}
			else {
				untraveledNodesRemain = false;
			}
		}
		
		
		
		
	
	}
	
	private void DFS_Visit(Node headNode) {
		
		int minDistance;
		Node nextNode;
		
		headNode.setStartTime(time);
		time++;
		
		
		
		
		for(int i = 0; i < headNode.getOutgoingEdges().size(); i++) {
			minDistance = Integer.MAX_VALUE;
			nextNode = headNode;
			for(Edge someEdge:headNode.getOutgoingEdges()){
				
				if(someEdge.getDistance() < minDistance && someEdge.getHead().getStartTime() == 0) {
					minDistance = someEdge.getDistance();
					nextNode = someEdge.getHead();
				}
				else if(someEdge.getDistance() == minDistance && someEdge.getHead().getStartTime() == 0) {
						if(someEdge.getHead().getName().compareTo(nextNode.getName()) > -1){
							minDistance = someEdge.getDistance();
							nextNode = someEdge.getHead();
						}
							
				}
			}
			
			if(!nextNode.getAbbrev().equals(headNode.getAbbrev())) {
				DFS_Visit(nextNode);
			}

		}

			headNode.setFinishTime(time);
			time++;
	} 
	
	private class NodeComparerAlphabetical implements Comparator<Node>{
		public int compare(Node node1, Node node2) {
			return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
		}
	}
	
}

