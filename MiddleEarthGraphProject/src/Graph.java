import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JComponent;

public class Graph extends JComponent{
	
	private HashMap<String, Node> searchNode;
	
	public Graph(){
		
		this.searchNode = new HashMap<>();
	}
	
	public boolean insert(String name, int x, int y){
		
		if (this.searchNode.containsKey(name)){
			
			return (false);
		}
		
		this.searchNode.put(name, new Node(x, y, name));
		
		return (true);
	}
	
	public void connect(String name1, String name2, int terrainDifficulty){
		
		Node node1 = this.searchNode.get(name1);
		Node node2 = this.searchNode.get(name2);
		
		Edge newEdge = new Edge(node1, node2, terrainDifficulty);
		
		node1.addEdge(newEdge);
		node2.addEdge(newEdge);
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
		}
		
		System.out.println("Total Length Traveled:" + currentNode.getLengthTraveled());
		
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
	
	@Override
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2 = (Graphics2D) graphics;
		
		int xStretch = 10;
		int yStretch = 10;
		
		for (Node n : this.searchNode.values()){
			
			n.draw(graphics2, xStretch, yStretch);
		}
		
		boolean gridOn = false;
		
		if (gridOn){
			
			graphics2.setColor(Color.BLACK);
		
			for (int i = 0; i < (int) (1900 / xStretch); i++){
					
				graphics2.drawLine(i*xStretch, 0, i*xStretch, 1000);
			}
			
			for (int j = 0; j < (int) (1000 / yStretch); j++){
				
				graphics2.drawLine(0, j*yStretch, 1900, j*yStretch);
			}
		}
	}

}
