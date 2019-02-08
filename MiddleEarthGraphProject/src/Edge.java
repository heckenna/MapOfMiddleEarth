import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	
	private int distance;
	private int time;
	
	private Color color;
	
	private double terrainDifficulty;
	
	private Node start;
	private Node end;
	
	private int speed;
	
	private boolean toggleDraw;
	
	public Edge(Node start, Node end, double terrainDifficulty){
		
		this.end = end;
		this.start = start;
		
		this.color = Color.BLUE;
		
		this.speed = 5;
		
		this.terrainDifficulty = terrainDifficulty;
		
		this.distance = calculateDistance();
		this.time = calculateTime();
		
		this.toggleDraw = false;
	}
	
	public void draw(Graphics2D graphics2, double xZoom, double yZoom, boolean drawDistances, boolean drawEdges){
		
		if (this.toggleDraw || drawEdges){
			
			graphics2.setColor(this.color);
			
			graphics2.drawLine((int)(this.start.getX() * xZoom), 
					(int)(this.start.getY() * yZoom), 
					(int)(this.end.getX() * xZoom), 
					(int)(this.end.getY() * yZoom));
		}
			
		if(drawDistances) {
			graphics2.setColor(Color.BLUE);
			graphics2.drawString("D: " + Integer.toString(this.distance), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY()) / 2 * yZoom));
		
			graphics2.setColor(Color.BLACK);
			graphics2.drawString("T: " + Integer.toString(this.time), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY() + 40) / 2 * yZoom));
		}
	}
	
	public int calculateDistance(){
		
		return (int)( Math.sqrt( Math.pow( (this.start.getX() - this.end.getX()), 2) + 
				Math.pow( (this.start.getY() - this.end.getY()), 2) ) );
	}
	
	public int calculateTime(){
		
		return (int)(this.distance / (this.speed * this.terrainDifficulty));
	}
	
	public Node getOtherEnd(Node n){
		
		if (n == this.start){
			
			return (this.end);
		}
		
		return (this.start);
	}
	
	public void setColor(Color c){
		
		this.color = c;
	}
	
	public void setToggleDraw(boolean toggle){
		
		this.toggleDraw = toggle;
	}
	
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
