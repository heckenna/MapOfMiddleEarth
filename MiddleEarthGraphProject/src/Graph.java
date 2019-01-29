import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JComponent;

public class Graph extends JComponent{
	
	private HashMap<String, Node> searchNode;
	
	private Color color;
	
	private boolean gridOn;
	
	private double xZoom;
	private double yZoom;
	
	private static int FRAME_HEIGHT;
	private static int FRAME_WIDTH;
	
	public Graph( int FRAME_WIDTH, int FRAME_HEIGHT){
		
		this.searchNode = new HashMap<>();
		
		this.color = Color.BLACK;
		
		this.gridOn = false;
		
		this.xZoom = 10;
		this.yZoom = 10;
		
		Graph.FRAME_HEIGHT = FRAME_HEIGHT;
		Graph.FRAME_WIDTH = FRAME_WIDTH;
	}
	
	public boolean insert(String name, int x, int y){
		
		if (this.searchNode.containsKey(name)){
			
			return (false);
		}
		
		this.searchNode.put(name, new Node(x, y, name));
		
		return (true);
	}
	
	public boolean connect(String name1, String name2, int terrainDifficulty){
		
		Node node1 = this.searchNode.get(name1);
		Node node2 = this.searchNode.get(name2);
		
		if (node1 == null || node2 == null){
			
			return (false);
		}
		
		Edge newEdge = new Edge(node1, node2, terrainDifficulty);
		
		node1.addEdge(newEdge);
		node2.addEdge(newEdge);
		
		return (true);
	}
	
	public void findShortestPath(String beginString, String destinationString, String criteria){
		
		Node begin = this.searchNode.get(beginString);
		Node destination = this.searchNode.get(destinationString);
		
		ArrayList<Node> visited = new ArrayList<>();
		visited.add(begin);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(begin);
		
		Node currentNode = begin;
		currentNode.setLengthTraveled(0);
		currentNode.estimateTotalLength(destination);
		currentNode.setVisited(new ArrayList<Node>());
		
		while (currentNode != destination){
			
			for (Edge e : currentNode.getEdges()){
				
				Node neighbor = e.getOtherEnd(currentNode);
				
				if (! visited.contains(neighbor)){
				
					neighbor.setLengthTraveled(currentNode.getLengthTraveled() + e.getLength(criteria));
					
					neighbor.estimateTotalLength(destination);
					
					neighbor.setVisited(currentNode.getVisited());
					
					queue.add(neighbor);
					
					visited.add(neighbor);
				}
			}
			
			queue.remove(currentNode);
			
			currentNode = queue.peek();
			
			if (currentNode == null){
				
				return;
			}
		}
		
		System.out.println("Total Length Traveled (Distance or Time):" + currentNode.getLengthTraveled());
		
		Node last = null;
		
		for (Node n : currentNode.getVisited()){
			
			n.setColor(Color.RED);
			
			for (Edge e : n.getEdges()){
				
				if (last != null && last.getEdges().contains(e)){
					
					e.setColor(Color.RED);
				}
			}
			
			last = n;
		}
		
	}
	
	public boolean hasPlace(String place) {
		
		return this.searchNode.containsKey(place);
	}
	
	public void moveCenter(int x, int y){
		
		for (Node n : this.searchNode.values()){
			
			n.updateCoordinate(x, y);
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2 = (Graphics2D) graphics;
		
		for (Node n : this.searchNode.values()){
			
			n.draw(graphics2, this.xZoom, this.yZoom);
		}
		
		if (this.gridOn){
			
			graphics2.setColor(this.color);
		
			for (int i = 0; i < (int)(Graph.FRAME_WIDTH / this.xZoom); i++){
					
				graphics2.drawLine((int)(i * this.xZoom), 0, (int)(i * this.xZoom), Graph.FRAME_HEIGHT);
			}
			
			for (int j = 0; j < (int)(Graph.FRAME_HEIGHT / this.yZoom); j++){
				
				graphics2.drawLine(0, (int)(j * this.yZoom), Graph.FRAME_WIDTH, (int)(j * this.yZoom));
			}
		}
	}

}
