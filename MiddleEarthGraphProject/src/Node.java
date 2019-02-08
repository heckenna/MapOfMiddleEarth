import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

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
	
	public void addEdge(Edge e){
		
		this.edges.add(e);
	}
	
	public ArrayList<Edge> getEdges(){
		
		return (this.edges);
	}
	
	public void draw(Graphics2D graphics2, double xZoom, double yZoom, JFrame frame, boolean drawDistances, boolean drawEdges){
		graphics2.setFont(new Font("Aniron", Font.PLAIN, 12));
		graphics2.setColor(this.color);
		
		if(!this.button.isVisible()) this.button.setVisible(true);
	    this.button.setBounds((int) (this.x*xZoom - this.radius*1.2), (int) (this.y * yZoom - this.radius), 15, 12);
	    if((this.x*xZoom - this.radius*1.2) > frame.getWidth()-340) {
	    	this.button.setVisible(false);
	    }
		
		/*graphics2.fillOval((int)(this.x * xZoom - this.radius), 
				(int)(this.y * yZoom - this.radius), 
				2*this.radius, 
				2*this.radius);*/
	    	
	    graphics2.drawString(this.name, (int)((this.x - 10) * xZoom), (int)((this.y - 8) * yZoom));
	    	
		for (Edge e : this.edges){
			
			e.draw(graphics2, xZoom, yZoom, drawDistances, drawEdges);
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
	
	public int estimateLength(Node destination, String criteria){
		
		int distance = (int)( Math.sqrt( Math.pow( (this.x - destination.getX()), 2) + 
				Math.pow( (this.y - destination.getY()), 2) ) );
		
		if (criteria.equals("distance")){
			
			return (distance);
		}
		
		return (int)(distance / 5);
	}
}
