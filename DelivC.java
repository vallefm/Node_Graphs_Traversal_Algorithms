import java.io.*;

public class DelivC{

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;
	//private Graph MSTgraph;
	Graph MSTgraph = new Graph();

	//Constructor - DO NOT MODIFY
	public DelivC(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable C
		runDelivC();

		output.flush();
	}

		
	private void runDelivC() {
		MST(graph);
	}
	
	
	void MST(Graph graph) {
		Node startNode = null;
		boolean nodesRemain = true;
		
		if(MSTgraph.getNodeList().isEmpty()) {	 			
			for(Node someNode:graph.getNodeList()) {
				if(someNode.getValue().equals("S")) {
					startNode = someNode;
					MSTgraph.addNode(startNode);
				}
			}
		}
		
		while(nodesRemain) {
			int minDistance = Integer.MAX_VALUE;
			Edge MSTedge = null;
			for(Node someNode: MSTgraph.getNodeList()) {
				System.out.print("On node" + someNode.getAbbrev() + " this node has edges going to ");
				for(Edge someEdge:someNode.getOutgoingEdges()){
					System.out.print(someEdge.getHead().getAbbrev() + "(" + someEdge.getDistance() + ")"+ ", ");
					if(someEdge.getDistance() < minDistance && !MSTgraph.getNodeList().contains(someEdge.getHead())) {
						minDistance = someEdge.getDistance();
						MSTedge = someEdge;
					}
					
					else if(someEdge.getDistance() == minDistance && !MSTgraph.getNodeList().contains(someEdge.getHead())) {
							if(someEdge.getHead().getAbbrev().compareTo(MSTedge.getHead().getAbbrev()) > -1){
								minDistance = someEdge.getDistance();
								MSTedge = someEdge;
							}
					}
				}
			}
			
			if(minDistance < Integer.MAX_VALUE) {
				MSTgraph.addNode(MSTedge.getHead());
				MSTgraph.addEdge(MSTedge);
				for(Node n: MSTgraph.getNodeList()) {
					System.out.print("\n" + n.getAbbrev() +", ");
				}
				System.out.println(MSTedge.getTail().getAbbrev() +" ->" + MSTedge.getHead().getAbbrev());
			}
			else {
				nodesRemain = false;
			}
		}
		
		
		
		
		
		
	}
	


}

