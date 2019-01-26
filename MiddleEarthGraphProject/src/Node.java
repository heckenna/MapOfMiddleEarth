import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Node {

	private int x;
	private int y;
	private String name;
	
	private ArrayList<Edge> edges;
	
	public Node(int x, int y, String name){
		
		this.x = x;
		this.y = y;
		
		this.name = name;
		
		this.edges = new ArrayList<>();
		
	}
	
	public void addEdge(Edge e){
		
		this.edges.add(e);
	}
	
	public void draw(Graphics2D graphics2, int xStretch, int yStretch){
		
		graphics2.setColor(Color.BLACK);
		
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
}
