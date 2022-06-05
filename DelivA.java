import java.io.*;
import java.util.Collections;
import java.util.Comparator;

public class DelivA {

	private File inputFile;
	private File outputFile;
	private PrintWriter output;
	private Graph graph;

	//Constructor - DO NOT MODIFY
	public DelivA(File in, Graph gr) {
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
		
		// Calls the method that will do the work of deliverable A
		runDelivA();

		output.flush();
	}

	//*********************************************************************************
	//               This is where your work starts
	//comparator class private, written below runDelivA method
	private void runDelivA() {
		
		System.out.println("Indegree: ");
		output.println("Indegree: ");
		Collections.sort(graph.getNodeList(), new NodeComparerIndegree());
		for(Node someNode:graph.getNodeList()) {
			System.out.print("Node " + someNode.getAbbrev() + " has indegree ");
			System.out.println(someNode.getIncomingEdges().size());
			
			
			output.print("Node " + someNode.getAbbrev() + " has indegree ");
			output.println(someNode.getIncomingEdges().size());
		}
	

		
		
		
		
		System.out.println("\nOutdegree: ");
		output.println("\nOutdegree: ");
		Collections.sort(graph.getNodeList(), new NodeComparerOutdegree());
		for(Node someNode:graph.getNodeList()) {
			System.out.print("Node " + someNode.getAbbrev() + " has outdegree ");
			System.out.println(someNode.getOutgoingEdges().size());
			
			
			output.print("Node " + someNode.getAbbrev() + " has outdegree ");
			output.println(someNode.getOutgoingEdges().size());
		}
	}
		
		private class NodeComparerOutdegree implements Comparator<Node>{
			public int compare(Node node1, Node node2) {
				if(node1.getOutgoingEdges().size() > node2.getOutgoingEdges().size()) {
					return -1;
				}
				else if(node1.getOutgoingEdges().size() < node2.getOutgoingEdges().size()) {
					return 1;
				}
				else {
					if(node1.getIncomingEdges().size() < node2.getIncomingEdges().size()){
						return 1;
					}
					else if(node1.getIncomingEdges().size() < node2.getIncomingEdges().size()){
						return -1;
					}
					else {
						return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
					}
				
			}
						
		}
		}
		
		private class NodeComparerIndegree implements Comparator<Node>{
			public int compare(Node node1, Node node2) {
				if(node1.getIncomingEdges().size() > node2.getIncomingEdges().size()) {
					return -1;
				}
				else if(node1.getIncomingEdges().size() < node2.getIncomingEdges().size()) {
					return 1;
				}
				else {
					if(node1.getOutgoingEdges().size() < node2.getOutgoingEdges().size()){
						return 1;
					}
					else if(node1.getOutgoingEdges().size() < node2.getOutgoingEdges().size()){
						return -1;
					}
					else {
						return node1.getAbbrev().compareToIgnoreCase(node2.getAbbrev());
					}
				}
				
			}
		}
}
