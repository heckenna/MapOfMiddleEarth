import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	
	private int distance;
	private int time;
	
	private Color color;
	
	private int terrainDifficulty;
	
	private Node start;
	private Node end;
	
	public Edge(Node start, Node end, int terrainDifficulty){
		
		this.end = end;
		this.start = start;
		
		this.color = Color.BLUE;
		
		this.terrainDifficulty = terrainDifficulty;
		
		this.distance = calculateDistance();
		this.time = calculateTime();
	}
	
	public void draw(Graphics2D graphics2, int xStretch, int yStretch){
		
		graphics2.setColor(this.color);
		
		graphics2.drawLine(this.start.getX() * xStretch, 
				this.start.getY() * yStretch, 
				this.end.getX() * xStretch, 
				this.end.getY() * yStretch);
		
		graphics2.drawString(Integer.toString(this.distance), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xStretch), 
				(int)((this.start.getY()+this.end.getY()) / 2 * yStretch));
		
		graphics2.setColor(Color.BLACK);
		
		graphics2.drawString(Integer.toString(this.time), 
				(int)((this.start.getX()+this.end.getX()) / 2 * xStretch), 
				(int)((this.start.getY()+this.end.getY() + 2) / 2 * yStretch));
		
	}
	
	public int calculateDistance(){
		
		return (int)( Math.sqrt( Math.pow( (this.start.getX() - this.end.getX()), 2) + 
				Math.pow( (this.start.getY() - this.end.getY()), 2) ) );
	}
	
	public int calculateTime(){
		
		int speed = 5;
		
		return ( (this.distance + this.terrainDifficulty) * speed);
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
