import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node implements Comparable<Node>{

	private int xCoor;
	private int yCoor;
	
	private int x;
	private int y;
	
	private String name;
	
	private Color color;
	private int radius;
	
	private ArrayList<Edge> edges;
	
	private int lengthTraveled;
	private int estimatedTotalLength;
	
	private ArrayList<Node> visited;
	
	public Node(int xCoor, int yCoor, String name){
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.x = xCoor;
		this.y = yCoor;
		
		this.name = name;
		
		this.color = Color.BLACK;
		
		this.radius = 5;
		
		this.edges = new ArrayList<>();
		
		this.lengthTraveled = 0;
		this.estimatedTotalLength = 0;
		
		this.visited = new ArrayList<>();
		
	}
	
	public void addEdge(Edge e){
		
		this.edges.add(e);
	}
	
	public ArrayList<Edge> getEdges(){
		
		return (this.edges);
	}
	
	public void draw(Graphics2D graphics2, double xZoom, double yZoom){
		
		graphics2.setColor(this.color);
		
		graphics2.fillOval((int)(this.x * xZoom - this.radius), 
				(int)(this.y * yZoom - this.radius), 
				2*this.radius, 
				2*this.radius);
		
		graphics2.drawString(this.name, (int)((this.x - 10) * xZoom), (int)((this.y - 8) * yZoom));
		
		for (Edge e : this.edges){
			
			e.draw(graphics2, xZoom, yZoom);
		}
		
	}
	
	public void updateCoordinate(int x, int y){
		
		this.x += x;
		this.y += y;
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
	
	public int getLengthTraveled(){
		
		return (this.lengthTraveled);
	}
	
	public void setLengthTraveled(int lengthTraveled){
		
		this.lengthTraveled = lengthTraveled;
	}
	
	public void estimateTotalLength(Node destination){
		
		this.estimatedTotalLength = (int)( Math.sqrt( Math.pow( (this.x - destination.getX()), 2) + 
				Math.pow( (this.y - destination.getY()), 2) ) ) + this.lengthTraveled;
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

	@Override
	public int compareTo(Node n) {
		
		if (this.estimatedTotalLength <= n.estimatedTotalLength){
			
			return (-1);
		}
		
		return (1);
	}
}
