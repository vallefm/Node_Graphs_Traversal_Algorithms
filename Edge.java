// Edge between two nodes
public class Edge {
	
	private int distance;
	private Node tail;
	private Node head;
	
	public Edge(Node tailNode, Node headNode, int dist) {
		distance = dist;
		tail = tailNode;
		head = headNode;
	}
	
	public Node getTail() {
		return tail;
	}
	
	public Node getHead() {
		return head;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setTail(Node newTail) {
		tail = newTail;
	}
	
	public void setHead(Node newHead) {
		head = newHead;
	}
	
	public void setDistance(int dist) {
		distance = dist;
	}
	
	void setEdgeType() {
		
	}
	public String getEdgeType() {
		if(tail.getStartTime() < head.getStartTime() && tail.getFinishTime() > head.getFinishTime()) {	
			if(head.getStartTime() - tail.getStartTime() == 1 || head.getFinishTime() - tail.getFinishTime() == -1) {
				return "Tree";
			}
			
			else {
				return "Forward";
			}
			
		}
		
		else if(tail.getStartTime() > head.getStartTime() && tail.getFinishTime() < head.getFinishTime()) {
			return "Back";
		}
		
		else if( head.getStartTime() < head.getFinishTime() && head.getFinishTime() < tail.getStartTime() && tail.getStartTime() < tail.getFinishTime()) {
			return "Cross";
		}
		
		else {
			return "Error";
		}
		
	}
}
