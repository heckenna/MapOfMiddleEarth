import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * Creates a graph which stores all of the nodes. This class also contains major operations that can be 
 * 	performed on the graph, such as the shortest path search, node insertion, node connection, positioning of 
 * 	the graph's coordinates, the ability to zoom in and out of the graph, etc. 
 * 
 */
public class Graph extends JComponent{
	
	private HashMap<String, Node> searchNode;
	private HashMap<String, String> descriptions;
	
	private static int FRAME_WIDTH;
	private static int FRAME_HEIGHT;
	
	private double xZoom;
	private double yZoom;
	private static final double X_ZOOM_MAX = 0.9;
	private static final double Y_ZOOM_MAX = 0.75;
	
	private ArrayList<Node> lastPath;

	private Node[] twoCities;
	
	private int lastLength;
	private int lastOppositeLength;

	private SidePanel sidePanel;

	private JFrame frame;
	private boolean toggleDistance;
	private boolean toggleEdges;
	private boolean toggleNames;
	
	private AudioPlayer a;
	
	private BufferedImage background;
	private int backgroundX;
	private int backgroundY;
	private static final int ORIGINAL_WIDTH = 3200;
	private static final int ORIGINAL_HEIGHT = 2400;
	private static final int SCREEN_WIDTH = 1900;
	private static final int SCREEN_HEIGHT = 1429;
	private static final int RIGHT_PADDING = 260;
	
	private int tripPlanRadius;
	
	public Graph(JFrame frame){
		this.a = new AudioPlayer();
		this.a.play("Music.wav");
		
		this.searchNode = new HashMap<>();
		this.twoCities = new Node[2];
		this.descriptions = new HashMap<>();
		
		Graph.FRAME_HEIGHT = frame.getHeight();
		Graph.FRAME_WIDTH = frame.getWidth();
		
		this.xZoom = Graph.X_ZOOM_MAX;
		this.yZoom = Graph.Y_ZOOM_MAX;
		
		this.lastPath = new ArrayList<>();
		
		this.lastLength = 0;
		this.lastOppositeLength = 0;
		
		this.frame = frame;
		
		this.frame.getContentPane().setBackground(new Color(222,184,135));
		
		this.toggleDistance = false;
		this.toggleEdges = false;
		this.toggleNames = false;
		
		this.backgroundX = 0;
		this.backgroundY = 0;
		
		this.tripPlanRadius = 0;
		
		try {
			
			this.background = ImageIO.read(new File("src/Middle Earth Map.jpg"));
		}
		
		catch (Exception e){
			
			System.out.println("ERROR: Could Not Find Background Image");
		}
	}
	
	public JFrame getFrame() {
		
		return this.frame;
	}
	
	public void addSidePanel(SidePanel panel) {
		
		this.sidePanel = panel;
	}
	
	/**
	 * 
	 * Inserts a node into the graph
	 *
	 * @param name
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean insert(String name, int x, int y){
		
		if (this.searchNode.containsKey(name)){
			
			return (false);
		}
		Node newNode = new Node(x, y, name);		
		this.searchNode.put(name, new Node(x, y, name));
		
		return (true);
	}
	
	/**
	 * 
	 * Gets an array containing all of the node names in this graph. This is used for the drop down box purposes. 
	 *
	 * @return
	 */
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
	
	/**
	 * 
	 * Creates a connection, via an edge, between two of the nodes.
	 *
	 * @param name1
	 * @param name2
	 * @param terrainDifficulty
	 * @return
	 */
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
	
	/**
	 * 
	 * An A* algorithm which finds the shortest path between two citites using the criteria of either
	 * 	distance or time. 
	 *
	 * @param beginString
	 * @param destinationString
	 * @param criteria
	 */
	public void findShortestPath(String beginString, String destinationString, String criteria){
		
		String oppositeCriteria = "";
		if(this.twoCities[0]!= null) this.twoCities[0].button.setSelected(false);
		if(this.twoCities[1]!= null) this.twoCities[1].button.setSelected(false);

		
		if (criteria.equals("distance")){
			
			oppositeCriteria = "time";
		}
		
		else {
			
			oppositeCriteria = "distance";
		}
		
		setPathColor(Color.BLACK, Color.BLACK, false);
		
		Node begin = this.searchNode.get(beginString);
		Node destination = this.searchNode.get(destinationString);
		
		this.twoCities[0] = begin;
		this.twoCities[1] = destination;
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
		
		setPathColor(Color.WHITE, Color.WHITE, true);
		
	}
	
	/**
	 * 
	 * Sets the color of the last path that the shortest path algorithm returned. This is used to highlight
	 * 	the shortest path. 
	 *
	 * @param nodeColor
	 * @param edgeColor
	 * @param toggleEdge
	 */
	public void setPathColor(Color nodeColor, Color edgeColor, boolean toggleEdge){
		
		Node last = null;
		
		for (Node n : this.lastPath){
			
			n.setColor(nodeColor);
			
			for (Edge e : n.getEdges()){
				
				if (last != null && last.getEdges().contains(e)){
					
					e.setColor(edgeColor);
					e.setToggleDraw(toggleEdge);
				}
			}
			
			last = n;
		}
	}
	
	/**
	 * 
	 * Checks if this graph contains a certain node. 
	 *
	 * @param place
	 * @return
	 */
	public boolean hasPlace(String place) {
		
		return this.searchNode.containsKey(place);
	}
	
	/**
	 * 
	 * Repositions the graph. The graph can move left, right, up, or down. 
	 *
	 * @param x
	 * @param y
	 */
	public void moveCenter(int x, int y){
		
		if (this.backgroundX + x > 0){
			
			x = 0;
		}
		
		else if ((this.backgroundX + Graph.SCREEN_WIDTH + x) * this.xZoom < (Graph.FRAME_WIDTH - Graph.RIGHT_PADDING)){
			
			x = (int)(((Graph.FRAME_WIDTH - Graph.RIGHT_PADDING) / this.xZoom) - Graph.SCREEN_WIDTH - this.backgroundX);
		}
		
		if (this.backgroundY + y > 0){
			
			y = 0;
		}
		
		else if ((this.backgroundY + Graph.SCREEN_HEIGHT + y) * this.yZoom < (this.frame.getHeight())){
			
			y = (int)((this.frame.getHeight() / this.yZoom) - Graph.SCREEN_HEIGHT - this.backgroundY);
		}
		
		for (Node n : this.searchNode.values()){
			
			n.updateCoordinate(x, y);
		}
		
		this.backgroundX += x;
		this.backgroundY += y;
	}
	
	/**
	 * 
	 * Changes the zoom on the map. 
	 *
	 * @param amount
	 */
	public void zoom(double amount){
		
		this.xZoom += amount;
		this.yZoom += amount;
		
		if (this.xZoom < Graph.X_ZOOM_MAX){
			
			this.xZoom = Graph.X_ZOOM_MAX;
		}
		
		if (this.yZoom < Graph.Y_ZOOM_MAX){
			
			this.yZoom = Graph.Y_ZOOM_MAX;
		}
		
		moveCenter(0,0);

	}
	
	@Override
	/**
	 * 
	 * Draws the background image, tells each of the nodes to draw themselves (The nodes tell the edges to draw
	 * 	themselves). 
	 * 
	 */
	protected void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2 = (Graphics2D) graphics;
		
		Font newR = new Font("Times New Roman", 12, 12);
		
		try {
			
			Font aniron = newR.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont((float)12);
			graphics2.setFont(font);
		} 
		
		catch (FontFormatException | IOException exception) {
			
			exception.printStackTrace();
		}
		
		graphics2.drawImage(this.background, 
				(int)(this.backgroundX * this.xZoom), 
				(int)(this.backgroundY * this.yZoom), 
				(int)((this.backgroundX + Graph.SCREEN_WIDTH) * this.xZoom), 
				(int)((this.backgroundY + Graph.SCREEN_HEIGHT) * this.yZoom), 
				0, 0, Graph.ORIGINAL_WIDTH, Graph.ORIGINAL_HEIGHT, null); 
		
		for (Node n : this.searchNode.values()){
			
			n.draw(graphics2, this.xZoom, this.yZoom, this.frame, this.toggleDistance, this.toggleEdges, this.toggleNames);
		}
		
		if (this.twoCities[0] != null && this.twoCities[1] == null && this.tripPlanRadius > 0){
			
			Node n = this.twoCities[0];
			
			int width = (int)(this.tripPlanRadius / 0.9);
			int height = (int)(this.tripPlanRadius / 0.75);
			int radiusPadding = 4;
			
			graphics2.setColor(new Color(0,255,0,75));
			
			graphics2.fillOval((int)((n.getX() - width)*this.xZoom + radiusPadding), 
					(int)((n.getY() - height)*this.yZoom + radiusPadding), 
					(int)(2*width*this.xZoom), 
					(int)(2*height*this.yZoom));
		}
	}
	
	/**
	 * 
	 * Sets the desired radius of the trip plan mode
	 *
	 * @param radius
	 */
	public void planTrip(int radius){
		
		this.tripPlanRadius = radius;
	}
	
	/**
	 * 
	 * Gets the length of the last search path, whether distance or time were the units. 
	 *
	 * @return
	 */
	public int getLastLength(){
		
		return (this.lastLength);
	}
	
	/**
	 * 
	 * Gets the opposite units of the last length of the search path. For example, if the last searhc path was
	 * 	conducted on the basis of distance, then the time of the last path will be returned. 
	 *
	 * @return
	 */
	public int getLastOppositeLength(){
		
		return (this.lastOppositeLength);
	}

	/**
	 * 
	 * Adds a city to be either a start or an end node. If it is a start node, then nothing occurs, the program has
	 * 	to wait for an end city to be selected. IF it is an end city, then a shortest path is automatically
	 * 	calculated. 
	 *
	 * @param city
	 */
	public void findBetween(Node city) {		
		
		if(this.twoCities[0] != null && this.twoCities[1] != null) {
			
			this.twoCities[0].button.setSelected(false);
			this.twoCities[1].button.setSelected(false);
			this.twoCities[0] = null;
			this.twoCities[1] = null;
		} 
		
		city.button.setSelected(true);
		
		if (this.twoCities[0] == null) {
			
			this.sidePanel.addDescription(this.descriptions,city.getName());
			this.setPathColor(Color.BLACK, Color.BLACK, false);
			this.twoCities[0] = city;
			this.sidePanel.populateStart(city.getName());
			this.twoCities[0].button.setSelected(true);			
		} 
		
		else if (this.twoCities[1] == null){
			
			this.twoCities[1] = city;
			this.sidePanel.addDescription(this.descriptions,city.getName());
			this.setPathColor(Color.BLACK, Color.BLACK, false);
			this.sidePanel.populateEnd(city.getName());
			this.twoCities[1].button.setSelected(true);			
		}
		
		if (this.twoCities[0] != null && this.twoCities[1] != null) {

			this.sidePanel.enter.doClick();
		}
	}
	
	/**
	 * 
	 * Overrides the start city with the passed in city. 
	 *
	 * @param name
	 */
	public void addCity(String name){
		
		Node n = this.searchNode.get(name);
		
		if(n != null){
			
			if(this.twoCities[0]!= null) this.twoCities[0].button.setSelected(false);
			this.twoCities[0] = n;
		}
	}

	/**
	 * 
	 * Adds a CityListener to all of the nodes in the graph. 
	 *
	 */
	public void addButtons() {
				
		for(Node n : this.searchNode.values()) {
			
			n.button.addActionListener(new CityListener(n, this));
			this.frame.add(n.button);	
		}
		
	}
	
	/**
	 * 
	 * Sets a node's radio button to activate. Makes this node either the start or end city. Displays the 
	 * 	description of this city int the side panel. 
	 *
	 * @param city
	 * @param thatCity
	 */
	public void activateButton(String city, int thatCity) {
		
		this.planTrip(0);
		this.repaint();
		
		Node n = this.searchNode.get(city);
		
		if(this.twoCities[thatCity]!=null) this.twoCities[thatCity].button.setSelected(false);
		
		this.twoCities[thatCity] = n;
		
		if(n!=null) {
			
			this.twoCities[thatCity].button.setSelected(true);
			this.sidePanel.addDescription(this.descriptions,city);
		}
		
		
	}
	
	/**
	 * 
	 * Clears the start and end cities, so that no node has been selected yet. 
	 *
	 */
	public void clearButtons() {
		
		if(this.twoCities[0]!= null) this.twoCities[0].button.setSelected(false);
		this.twoCities[0] = null;
		if(this.twoCities[1]!= null) this.twoCities[1].button.setSelected(false);
		this.twoCities[1] = null;
		this.sidePanel.addDescription(this.descriptions,"clear");
		
	}
	
	/**
	 * 
	 * Toggles whether or not to display the edge metrics of distance and time. 
	 *
	 */
	public void toggleDistance() {
		
		this.toggleDistance = ! this.toggleDistance;
	}
	
	/**
	 * 
	 * Toggles whether or not to display the edges. 
	 *
	 */
	public void toggleEdges(){
		
		this.toggleEdges = ! this.toggleEdges;
	}
	
	/**
	 * 
	 * Toggles whether or not to display the city names.
	 *
	 */
	public void toggleNames(){
		
		this.toggleNames = ! this.toggleNames;
	}

	/**
	 * Inserts a new desciption of a city into the map.
	 *
	 * @param name
	 * @param description
	 */
	public void insertDescription(String name, String description) {
		
		this.descriptions.put(name, description);
		
	}

	/**
	 * 
	 * returns this graph's audio player
	 *
	 * @return
	 */
	public AudioPlayer getAudioPlayer() {
		
		return this.a;
	}
}
