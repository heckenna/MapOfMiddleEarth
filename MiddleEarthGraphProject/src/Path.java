import java.util.ArrayList;

public class Path implements Comparable<Path>{
	
	private int lengthTraveled;
	private int estimatedTotalLength;
	
	private ArrayList<Node> visited;
	
	private Node lastNode;
	
	public Path(ArrayList<Node> haveVisited, int lengthTraveled, int estimate, Node lastNode){
		
		this.visited = new ArrayList<>();
		
		for (Node n : haveVisited){
			
			this.visited.add(n);
		}
		
		this.visited.add(lastNode);
		
		
		this.lengthTraveled = lengthTraveled;
		
		this.estimatedTotalLength = estimate + lengthTraveled;
		
		
		this.lastNode = lastNode;
	}
	
	public Node getLastNode(){
		
		return (this.lastNode);
	}
	
	public int getLengthTraveled(){
		
		return (this.lengthTraveled);
	}
	
	public ArrayList<Node> getVisited(){
		
		return (this.visited);
	}

	@Override
	public int compareTo(Path p) {
		
		if (this.estimatedTotalLength <= p.estimatedTotalLength){
			
			return (-1);
		}
		
		return (1);
	}

}
