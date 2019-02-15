import java.util.ArrayList;

/**
 * 
 * A Path is used in the shortest path algorithm. These objects are used to keep track of whichi nodes have been visited
 * 	and in what order, the total distance and time traveled, and the estimated total ditance of the path. A separate
 * 	Path object is created for each path the A* algorithm explores. 
 * 
 * 
 */
public class Path implements Comparable<Path>{
	
	private int lengthTraveled;
	private int oppositeLengthTraveled;
	private int estimatedTotalLength;
	
	private ArrayList<Node> visited;
	
	private Node lastNode;
	
	public Path(ArrayList<Node> haveVisited, int lengthTraveled, int oppositeLengthTraveled, int estimate, Node lastNode){
		
		this.visited = new ArrayList<>();
		
		for (Node n : haveVisited){
			
			this.visited.add(n);
		}
		
		this.visited.add(lastNode);
		
		
		this.lengthTraveled = lengthTraveled;
		
		this.oppositeLengthTraveled = oppositeLengthTraveled;
		
		this.estimatedTotalLength = estimate + lengthTraveled;
		
		
		this.lastNode = lastNode;
	}
	
	/**
	 * 
	 * Gets the last node this path has been to thus far. 
	 *
	 * @return
	 */
	public Node getLastNode(){
		
		return (this.lastNode);
	}
	
	/**
	 * 
	 * Gets the length of the path, either in distance or time. 
	 *
	 * @return
	 */
	public int getLengthTraveled(){
		
		return (this.lengthTraveled);
	}
	
	/**
	 * 
	 * Gets the opposite metric associated with this path. For example, if the main search criteria is for a path of
	 * 	shorest distance, then the time of this path is returned. 
	 *
	 * @return
	 */
	public int getOppositeLengthTraveled(){
		
		return (this.oppositeLengthTraveled);
	}
	
	/**
	 * 
	 *  returns a list of the nodes in this path, in order of visited.
	 *
	 * @return
	 */
	public ArrayList<Node> getVisited(){
		
		return (this.visited);
	}

	@Override
	/**
	 * 
	 * Used to compare two paths to see which is the shortest one, based upon either distance or time. 
	 * 
	 */
	public int compareTo(Path p) {
		
		if (this.estimatedTotalLength <= p.estimatedTotalLength){
			
			return (-1);
		}
		
		return (1);
	}

}
