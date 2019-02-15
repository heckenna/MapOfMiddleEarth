import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * An Edge is a connection or path between two nodes. Each Edge has two nodes, a start and an end node. 
 * 	Each Edge also contains its own data about how long it is in miles and how long it takes in days to 
 * 	travel the edge, depending on the user's walking speed. 
 *
 */
public class Edge {
	
	private int distance;
	private int time;
	
	private Color color;
	
	private double terrainDifficulty;
	
	private Node start;
	private Node end;
	
	private int speed;
	
	private boolean toggleDraw;
	
	private static final BasicStroke STROKE = new BasicStroke(2);
	
	public Edge(Node start, Node end, double terrainDifficulty){
		
		this.end = end;
		this.start = start;
		
		this.color = Color.BLACK;
		
		this.speed = 12;
		
		this.terrainDifficulty = terrainDifficulty;
		
		this.distance = calculateDistance();
		this.time = calculateTime();
		
		this.toggleDraw = false; 
	}
	
	/**
	 * 
	 * Draws the edge on the screen. If drawDistances is true, then the edges data will be drawn on the 
	 * 	frame (time and distance). If drawEdges if true, then the straight line edges will be drawn. Both of
	 * 	these options can be toggled by the user.
	 *
	 * @param graphics2
	 * @param xZoom
	 * @param yZoom
	 * @param drawDistances
	 * @param drawEdges
	 */
	public void draw(Graphics2D graphics2, double xZoom, double yZoom, boolean drawDistances, boolean drawEdges){
		
		if (this.toggleDraw || drawEdges){
			
			graphics2.setColor(this.color);
			
			graphics2.setStroke(Edge.STROKE);
			
			graphics2.drawLine((int)(this.start.getX() * xZoom), 
					(int)(this.start.getY() * yZoom), 
					(int)(this.end.getX() * xZoom), 
					(int)(this.end.getY() * yZoom));
		}
			
		if(drawDistances) {
			
			graphics2.setColor(Color.BLACK);
			graphics2.drawString("D: " + Integer.toString(this.distance), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY()) / 2 * yZoom));
		
			graphics2.setColor(Color.BLACK);
			graphics2.drawString("T: " + Integer.toString(this.time), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY() + 40) / 2 * yZoom));
		}
	}
	
	/**
	 * 
	 * calculates the distance of this edge depending upon the start and end node coordinates.
	 *
	 */
	public int calculateDistance(){
		
		return (int)( Math.sqrt( Math.pow( (this.start.getX() - this.end.getX()), 2) + 
				Math.pow( (this.start.getY() - this.end.getY()), 2) ) );
	}
	
	/**
	 * 
	 * Calculates the time it takes to traverse this edge. This depends on the distance, the user's traveling speed, 
	 * 	and the difficulty of the terrain associated with this edge. 
	 */
	public int calculateTime(){
		
		return (int)(this.distance / (this.speed * this.terrainDifficulty));
	}
	
	/**
	 * 
	 * Gets the other end of the current node. For example, if the current node is the start node, the 
	 * 	end node is returned. 
	 *
	 */
	public Node getOtherEnd(Node n){
		
		if (n == this.start){
			
			return (this.end);
		}
		
		return (this.start);
	}
	
	/**
	 * 
	 * Sets the color of the edge.
	 *
	 * @param c
	 */
	public void setColor(Color c){
		
		this.color = c;
	}
	
	/**
	 * 
	 * Allows the edge to be drawn even if the user has chosen not to display edges. We override the user's 
	 * 	choice when a search path is displayed. This way, whenever a shortest path search is conducted, 
	 * 	the path is always highlighted. 
	 *
	 * @param toggle
	 */
	public void setToggleDraw(boolean toggle){
		
		this.toggleDraw = toggle;
	}
	
	/**
	 * 
	 * Returns the correct metric of this edge, either time or distance, depending upon the input criteria.
	 *
	 * @param criteria
	 * @return
	 */
	public int getLength(String criteria){
		
		if (criteria.equals("distance")){
		
			return (this.distance);
		}
		
		else if (criteria.equals("time")){
		
			return (this.time);
		}
		
		return (-1);
	}

}
