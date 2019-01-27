import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	
	private int distance;
	private int time;
	
	private Color color;
	
	private int terrainDifficulty;
	
	private Node start;
	private Node end;
	
	private int speed;
	
	public Edge(Node start, Node end, int terrainDifficulty){
		
		this.end = end;
		this.start = start;
		
		this.color = Color.BLUE;
		
		this.speed = 5;
		
		this.terrainDifficulty = terrainDifficulty;
		
		this.distance = calculateDistance();
		this.time = calculateTime();
	}
	
	public void draw(Graphics2D graphics2, double xZoom, double yZoom){
		
		graphics2.setColor(this.color);
		
		graphics2.drawLine((int)(this.start.getX() * xZoom), 
				(int)(this.start.getY() * yZoom), 
				(int)(this.end.getX() * xZoom), 
				(int)(this.end.getY() * yZoom));
		
		graphics2.setColor(Color.BLUE);
		graphics2.drawString("D: " + Integer.toString(this.distance), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY()) / 2 * yZoom));
		
		graphics2.setColor(Color.BLACK);
		graphics2.drawString("T: " + Integer.toString(this.time), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xZoom), 
				(int)((this.start.getY()+this.end.getY() + 2) / 2 * yZoom));
		
	}
	
	public int calculateDistance(){
		
		return (int)( Math.sqrt( Math.pow( (this.start.getX() - this.end.getX()), 2) + 
				Math.pow( (this.start.getY() - this.end.getY()), 2) ) );
	}
	
	public int calculateTime(){
		
		return ( (this.distance + this.terrainDifficulty) * this.speed);
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
