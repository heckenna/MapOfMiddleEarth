import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Graph extends JComponent{
	
	private HashMap<String, Node> searchNode;
	
	private double xZoom;
	private double yZoom;
	
	private ArrayList<Node> lastPath;

	private ArrayList<Node> twoCities;
	
	private int lastLength;
	
	public Graph(){
		
		this.searchNode = new HashMap<>();
		this.twoCities = new ArrayList<Node>();
		
		this.xZoom = 1;
		this.yZoom = 0.7;
		
		this.lastPath = new ArrayList<>();
		
		this.lastLength = 0;
	}
	
	public boolean insert(String name, int x, int y){
		
		if (this.searchNode.containsKey(name)){
			
			return (false);
		}
		Node newNode = new Node(x, y, name);		
		this.searchNode.put(name, new Node(x, y, name));
		
		return (true);
	}
	
	
	public String[] getNameArray() {
		
		int k = 0;
		
		String[] h = new String[this.searchNode.size()];
		
		for(Node n : this.searchNode.values()) {
			
			h[k] = n.getName();
			
			k+=1;		
		}
		
		Arrays.sort(h);
		
		return (h);
	}
	
	public boolean connect(String name1, String name2, double terrainDifficulty){
		
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
		
		setPathColor(Color.BLACK, Color.BLUE);
		
		Node begin = this.searchNode.get(beginString);
		Node destination = this.searchNode.get(destinationString);
		
		PriorityQueue<Path> queue = new PriorityQueue<>();
		
		Path currentPath = new Path(new ArrayList<Node>(), 0, 0, begin);
		
		Node currentNode = begin;
		
		while (currentNode != destination){
			
			for (Edge e : currentNode.getEdges()){
				
				Node neighbor = e.getOtherEnd(currentNode);
				
				if (! currentPath.getVisited().contains(neighbor)){
					
					queue.add(new Path(currentPath.getVisited(), 
							currentPath.getLengthTraveled() + e.getLength(criteria), 
							neighbor.estimateLength(destination, criteria), 
							neighbor));
				}
			}

			currentPath = queue.poll();
			
			currentNode = currentPath.getLastNode();
		}
		
		this.lastPath = currentPath.getVisited();
		
		this.lastLength = currentPath.getLengthTraveled();
		
		setPathColor(Color.RED, Color.RED);
		
	}
	
	public void setPathColor(Color nodeColor, Color edgeColor){
		
		Node last = null;
		
		for (Node n : this.lastPath){
			
			n.setColor(nodeColor);
			
			for (Edge e : n.getEdges()){
				
				if (last != null && last.getEdges().contains(e)){
					
					e.setColor(edgeColor);
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
	
	public void zoom(double amount){
		
		this.xZoom += amount;
		this.yZoom += amount;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2 = (Graphics2D) graphics;
		
		for (Node n : this.searchNode.values()){
			
			n.draw(graphics2, this.xZoom, this.yZoom);
		}
	}
	
	public int getLastLength(){
		
		return (this.lastLength);
	}

	public void findBetween(Node city) {
		
		if (this.twoCities.isEmpty()) {
			
			this.twoCities.add(city);
			
		} 
		
		else {
			
			this.findShortestPath(twoCities.get(0).getName(), city.getName(), "distance");
			twoCities.get(0).button.setSelected(false);
			city.button.setSelected(false);
			this.twoCities = new ArrayList<Node>();
		}
	}

	public void addButtons(JFrame frame) {
		
		int k = 0;
				
		for(Node n : this.searchNode.values()) {
			
			n.button.addActionListener(new CityListener(n, this));
			frame.add(n.button);
			
			k+=1;		
		}
		
	}

}
