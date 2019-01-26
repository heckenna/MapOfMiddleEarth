import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node implements Comparable<Node>{

	private int x;
	private int y;
	private String name;
	
	private Color color;
	
	private ArrayList<Edge> edges;
	
	private int distanceTraveled;
	private int estimatedTotalDistance;
	
	private ArrayList<Node> visited;
	
	public Node(int x, int y, String name){
		
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		this.color = Color.BLACK;
		
		this.edges = new ArrayList<>();
		
		this.distanceTraveled = 0;
		this.estimatedTotalDistance = 0;
		
		this.visited = new ArrayList<>();
		
	}
	
	public void addEdge(Edge e){
		
		this.edges.add(e);
	}
	
	public ArrayList<Edge> getEdges(){
		
		return (this.edges);
	}
	
	public void draw(Graphics2D graphics2, int xStretch, int yStretch){
		
		graphics2.setColor(this.color);
		
		int radius = 5;
		
		graphics2.fillOval(this.x * xStretch - radius, this.y * yStretch - radius, 2*radius, 2*radius);
		
		graphics2.drawString(this.name, (int)((this.x - 1.0) * xStretch), (int)((this.y - 0.5) * yStretch));
		
		for (Edge e : this.edges){
			
			e.draw(graphics2, xStretch, yStretch);
		}
		
	}
	
	public String getName(){
		
		return (this.name);
	}
	
	public int getX(){
		
		return (this.x);
	}
	
	public int getY(){
		
		return (this.y);
	}
	
	public void setColor(Color c){
		
		this.color  = c;
	}
	
	public int getDistanceTraveled(){
		
		return (this.distanceTraveled);
	}
	
	public void setDistanceTraveled(int distanceTraveled){
		
		this.distanceTraveled = distanceTraveled;
	}
	
	public void estimateTotalDistance(Node destination){
		
		this.estimatedTotalDistance = (int)( Math.sqrt( Math.pow( (this.x - destination.getX()), 2) + 
				Math.pow( (this.y - destination.getY()), 2) ) ) + this.distanceTraveled;
	}
	
	public void setVisited(ArrayList<Node> a){
		
		this.visited.clear();
		
		for (Node n : a){
			
			this.visited.add(n);
		}
		
		this.visited.add(this);
	}
	
	public ArrayList<Node> getVisited(){
		
		return (this.visited);
	}
	
	public void printVisited(){
		
		for (Node n : this.visited){
			
			System.out.print(n.getName() +" ");
		}
	}

	@Override
	public int compareTo(Node n) {
		
		if (this.estimatedTotalDistance <= n.estimatedTotalDistance){
			
			return (-1);
		}
		
		return (1);
	}
}
