import java.awt.Color;
import java.awt.Graphics2D;

public class Edge {
	
	private int distance;
	private int time;
	
	private int terrainDifficulty;
	
	private Node start;
	private Node end;
	
	public Edge(Node start, Node end, int terrainDifficulty){
		
		this.end = end;
		this.start = start;
		
		this.distance = calculateDistance();
		
		this.terrainDifficulty = terrainDifficulty;
	}
	
	public void draw(Graphics2D graphics2, int xStretch, int yStretch){
		
		graphics2.setColor(Color.BLUE);
		
		graphics2.drawLine(this.start.getX() * xStretch, 
				this.start.getY() * yStretch, 
				this.end.getX() * xStretch, 
				this.end.getY() * yStretch);
	}
	
	public int calculateDistance(){
		
		return (int)( Math.sqrt( Math.pow( (this.start.getX() - this.end.getX()), 2) + 
				Math.pow( (this.start.getY() - this.end.getY()), 2) ) );
	}
	
//	public int calculateTime(){
//		
//		return ( this.distance * (SPEED) + this.terrainDifficulty * (SOMETHING) );
//	}

}
