import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Graph extends JComponent{
	
	private HashMap<String, Node> searchNode;
	
	private double xZoom;
	private double yZoom;
	
	private ArrayList<Node> lastPath;

	private ArrayList<Node> twoCities;
	
	private int lastLength;
	private int lastOppositeLength;

	private SidePanel sidePanel;

	private JFrame frame;
	private boolean toggleDistance;
	
	private BufferedImage background;
	
	private int backgroundX;
	private int backgroundY;
	
	public Graph(JFrame frame){
		
		this.searchNode = new HashMap<>();
		this.twoCities = new ArrayList<Node>();
		
		this.xZoom = 1;
		this.yZoom = 0.7;
		
		this.lastPath = new ArrayList<>();
		
		this.lastLength = 0;
		this.lastOppositeLength = 0;
		
		this.frame = frame;
		
		this.backgroundX = 0;
		this.backgroundY = 0;
		
		try {
			
			this.background = ImageIO.read(new File("src/Middle Earth Map.jpg"));
		}
		
		catch (Exception e){
			
			System.out.println("ERROR: Could Not Find Background Image");
		}
	}
	
	public void addSidePanel(SidePanel panel) {
		this.sidePanel = panel;
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
		
		String oppositeCriteria = "";
		
		if (criteria.equals("distance")){
			
			oppositeCriteria = "time";
		}
		
		else {
			
			oppositeCriteria = "distance";
		}
		
		setPathColor(Color.BLACK, Color.BLUE);
		
		Node begin = this.searchNode.get(beginString);
		Node destination = this.searchNode.get(destinationString);
		
		begin.button.setSelected(true);
		destination.button.setSelected(true);
		
		
		PriorityQueue<Path> queue = new PriorityQueue<>();
		
		Path currentPath = new Path(new ArrayList<Node>(), 0, 0, 0, begin);
		
		Node currentNode = begin;
		
		while (currentNode != destination){
			
			for (Edge e : currentNode.getEdges()){
				
				Node neighbor = e.getOtherEnd(currentNode);
				
				if (! currentPath.getVisited().contains(neighbor)){
					
					queue.add(new Path(currentPath.getVisited(), 
							currentPath.getLengthTraveled() + e.getLength(criteria), 
							currentPath.getOppositeLengthTraveled() + e.getLength(oppositeCriteria),
							neighbor.estimateLength(destination, criteria), 
							neighbor));
				}
			}

			currentPath = queue.poll();
			
			currentNode = currentPath.getLastNode();
		}
		
		this.lastPath = currentPath.getVisited();
		
		this.lastLength = currentPath.getLengthTraveled();
		
		this.lastOppositeLength = currentPath.getOppositeLengthTraveled();
		
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
		
		this.backgroundX += x;
		this.backgroundY += y;
	}
	
	public void zoom(double amount){
		 
		this.xZoom += amount;
		this.yZoom += amount;
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2 = (Graphics2D) graphics;
		
		graphics2.drawImage(this.background, 
				(int)(this.backgroundX * this.xZoom), 
				(int)(this.backgroundY * this.yZoom), 
				(int)((this.backgroundX + 1900) * this.xZoom), 
				(int)((this.backgroundY + 1429) * this.yZoom), 
				0, 0, 3200, 2400, null);
		
		for (Node n : this.searchNode.values()){
			
			n.draw(graphics2, this.xZoom, this.yZoom, this.frame, toggleDistance);
		}
	}
	
	public int getLastLength(){
		
		return (this.lastLength);
	}
	
	public int getLastOppositeLength(){
		
		return (this.lastOppositeLength);
	}

	public void findBetween(Node city) {
		/* Trying to get deselection of city to work nicer. It is a bit wonky.
		if(!city.button.isSelected()) {
			if(city.getName().equals(this.twoCities.get(0).getName())) this.twoCities.remove(0);
			else this.twoCities.remove(1);
			return;
		} */
		
		
		if(this.twoCities.size()==2) {
			twoCities.get(0).button.setSelected(false);
			twoCities.get(1).button.setSelected(false);
			this.twoCities = new ArrayList<Node>();
		}
		
		city.button.setSelected(true);
		
		if (this.twoCities.isEmpty()) {
			this.setPathColor(Color.BLACK, Color.BLUE);
			this.twoCities.add(city);
			this.sidePanel.populateStart(city.getName());
			
		} 
		
		else {
			twoCities.add(city);
			this.findShortestPath(twoCities.get(0).getName(), city.getName(), "distance");
			this.sidePanel.populateEnd(city.getName());

			//this.findShortestPath(twoCities.get(0).getName(), city.getName(), "distance");
			this.sidePanel.enter.doClick();
			
		}
	}

	public void addButtons() {
		
		int k = 0;
				
		for(Node n : this.searchNode.values()) {
			
			n.button.addActionListener(new CityListener(n, this));
			this.frame.add(n.button);
			
			k+=1;		
		}
		
	}
	
	public void activateButton(String city) {
		for(Node n : this.searchNode.values()) {
			if(n.getName()==city) n.button.setSelected(true);
		}
	}
	public void deactivateButton(String city) {
		for(Node n : this.searchNode.values()) {
			if(n.getName() == city) n.button.setSelected(false);
		}
	}

	public void clearButtons() {
		while(!this.twoCities.isEmpty()) {
			this.twoCities.get(0).button.setSelected(false);
			this.twoCities.remove(twoCities.get(0));
		}
	}
	
	public void toggleDisplay() {
		this.toggleDistance = !this.toggleDistance;
	}
}
