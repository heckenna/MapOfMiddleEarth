import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 * 
 * A node is a single city or place in the graph. Each node contains information about itself such as its
 * 	coordiantes, which connections it has, etc. 
 *
 */
public class Node {

	private int xCoor;
	private int yCoor;
	
	private int x;
	private int y;
	
	private String name;
	
	private Color color;
	private int radius;
	
	private ArrayList<Edge> edges;
	protected JRadioButton button;
	
	public Node(int xCoor, int yCoor, String name){
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.x = xCoor;
		this.y = yCoor;
		
		this.name = name;
		
		this.button = new JRadioButton(name);
		button.setFocusable(false);
		

		
		this.button.setOpaque(false);
		
		this.color = Color.BLACK;
		
		this.radius = 5;
		
		this.edges = new ArrayList<>();
		
	}
	
	/**
	 * 
	 * Adds a connection to this node. 
	 *
	 * @param e
	 */
	public void addEdge(Edge e){
		
		this.edges.add(e);
	}
	
	/**
	 * 
	 * returns the arraylist of edges, or connections, that this node contains. 
	 *
	 * @return
	 */
	public ArrayList<Edge> getEdges(){
		
		return (this.edges);
	}
	
	/**
	 * 
	 * Draws this node. This node also tells each of its edges to draw themselves. The names of the nodes can be 
	 * 	toggled in this method; choosing whether or not to display the names. 
	 *
	 * @param graphics2
	 * @param xZoom
	 * @param yZoom
	 * @param frame
	 * @param drawDistances
	 * @param drawEdges
	 * @param drawNames
	 */
	public void draw(Graphics2D graphics2, double xZoom, double yZoom, JFrame frame, boolean drawDistances, boolean drawEdges, boolean drawNames){
		
		if(!this.button.isVisible()) this.button.setVisible(true);
		
	    this.button.setBounds((int) (this.x*xZoom - this.radius*1.2), (int) (this.y * yZoom - this.radius), 15, 12);
	    
	    if((this.x*xZoom - this.radius*1.2) > frame.getWidth()-340) {
	    	
	    	this.button.setVisible(false);
	    }
	    	
	    if (drawNames){
	    	
	    	graphics2.setColor(this.color);
	    	graphics2.drawString(this.name, (int)((this.x - 10) * xZoom), (int)((this.y - 8) * yZoom));
	    }
	    	
		for (Edge e : this.edges){
			
			e.draw(graphics2, xZoom, yZoom, drawDistances, drawEdges);
		}
		
	}
	
	/**
	 * 
	 * Updates the position of the node. Used in positioning of the graph. 
	 *
	 * @param x
	 * @param y
	 */
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
	
	/**
	 * 
	 * Sets the color of the node's name. 
	 *
	 * @param c
	 */
	public void setColor(Color c){
		
		this.color  = c;
	}
	
	/**
	 * 
	 * Estimates the length (either distance or time) between this node and a specified node. 
	 *
	 * @param destination
	 * @param criteria
	 * @return
	 */
	public int estimateLength(Node destination, String criteria){
		
		int distance = (int)( Math.sqrt( Math.pow( (this.x - destination.getX()), 2) + 
				Math.pow( (this.y - destination.getY()), 2) ) );
		
		if (criteria.equals("distance")){
			
			return (distance);
		}
		
		return (int)(distance / 12);
	}
}
