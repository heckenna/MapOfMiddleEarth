import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SidePanel extends JComponent{
	

	private Graph middleEarth;
	private JPanel panel;
	private JPanel descriptionPanel;
	private JPanel audioPanel;
	private JButton pausePlay;
	private JLabel descriptionLabel;
	private JLabel outputLength;
	private JLabel outputOppositeLength;
	private JComboBox<String> s;
	private JComboBox<String> e;
	protected JRadioButton dist;
	protected JButton enter;
	
	//This creates a side panel for user input and some display
	public SidePanel(Graph graph, JPanel frame) {
		this.s = new JComboBox<>();
		this.e = new JComboBox<>();
		this.panel = frame;
		panel.setBackground(new Color(222,184,135));
		
		this.middleEarth = graph; 
		
		Dimension preferredSize = new Dimension(310,400);
		this.panel.setPreferredSize(preferredSize );
		
		this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.outputLength = new JLabel("Distance: " + this.middleEarth.getLastLength() + " Miles");
		this.outputOppositeLength = new JLabel("Time: " + this.middleEarth.getLastOppositeLength() + " Days");
		new SetStyle(outputLength, 13);
		new SetStyle(outputOppositeLength, 13);

		//outputLength.setText("<html> <strong style=font-size:90%;>"+outputLength.getText() +"</strong></html>");
		//outputOppositeLength.setText("<html> <div style=font-size:90%;>"+outputOppositeLength.getText()+"</div></html>");

		//Adds the elements to the side panel.
		this.addSearchBars();
		this.addMapNav();
		this.addTogglers();
		this.addPanelOutput();
		this.addDescriptionPanel();
		this.addAudioPanel();
		
	}

	//Makes a button to control Audio and adds audio to the program.
	private void addAudioPanel() {
		this.pausePlay = new JButton();
		this.audioPanel = new JPanel();
		this.audioPanel.setPreferredSize(new Dimension(300,100));
		this.panel.add(this.audioPanel);
		new SetStyle(this.pausePlay, 10);
		this.audioPanel.add(this.pausePlay);
		this.pausePlay.setText("Pause Music");
		this.pausePlay.setBackground(Color.WHITE);
		this.audioPanel.setOpaque(false);
		this.pausePlay.addActionListener(new AudioListener(this.pausePlay,this.middleEarth.getAudioPlayer()));
		
	}

	//Adds descriptions of the destinations to be displayed on the side panel
	private void addDescriptionPanel() {
		this.descriptionLabel = new JLabel();
		this.descriptionPanel = new JPanel();
		this.descriptionPanel.setPreferredSize(new Dimension(300, 510));
		this.descriptionPanel.setOpaque(false);
		new SetStyle(this.descriptionLabel, 10);
		this.panel.add(this.descriptionPanel);
		this.descriptionPanel.add(this.descriptionLabel);
		
	}

	//Adds choices of displaying distance/time, edges, and names.
	private void addTogglers() {
		JRadioButton toggleDistance = new JRadioButton("Display Distance and Time");
		
		//Makes the distance/time toggleable 
		toggleDistance.addActionListener(new DistanceToggler(this.middleEarth));
		new SetStyle(toggleDistance, 10);

		this.panel.add(toggleDistance);
		
		//Makes the edges toggleable 
		JRadioButton toggleEdges = new JRadioButton("Display Edges");
		
		toggleEdges.addActionListener(new EdgeToggler(this.middleEarth));
		new SetStyle(toggleEdges, 10);
		
		this.panel.add(toggleEdges);
		
		//Makes the names toggleable 
		JRadioButton toggleNames = new JRadioButton("Display Names");
		
		toggleNames.addActionListener(new NameToggler(this.middleEarth));
		new SetStyle(toggleNames, 10);
		
		this.panel.add(toggleNames);
	}

	//Makes it possible to move around the map.
	public void addMapNav(){
		
		
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		//SetStyle MUST be called for everything in order for keyListeners to work.
		//Sets font, also makes it so element CANNOT be focused.
		new SetStyle(north, 10);
		new SetStyle(south, 10);
		new SetStyle(east, 10);
		new SetStyle(west, 10);

		new SetStyle(zoomIn, 10);
		new SetStyle(zoomOut, 10);

		
		//Listeners to move around the map.		
		north.addActionListener(new MoverListener(0, 50, this.middleEarth));
		south.addActionListener(new MoverListener(0, -50, this.middleEarth));
		east.addActionListener(new MoverListener(-50, 0, this.middleEarth));
		west.addActionListener(new MoverListener(50, 0, this.middleEarth));
		
		north.setText("<html> <div style=font-size:100%;> North </div></html>");
		
		zoomIn.addActionListener(new ZoomListener(0.1, this.middleEarth));
		zoomOut.addActionListener(new ZoomListener(-0.1, this.middleEarth));
		
		//panel must be the ONLY focusable thing in order for the keyListeners to work.
		panel.setFocusable(true);
		panel.addKeyListener(new KeysToClick(north, south, east, west, enter, zoomIn, zoomOut));
		
		JPanel compass = new JPanel();
		//Set to transparent for stylistic reasons.
		compass.setOpaque(false);
		
		compass.add(north, BorderLayout.NORTH);
		compass.add(west, BorderLayout.WEST);
		compass.add(east, BorderLayout.EAST);
		compass.add(south, BorderLayout.SOUTH);
		
		JPanel zooms = new JPanel();
		//Set to transparent for stylistic reasons.
		zooms.setOpaque(false);

		
		zooms.add(zoomIn);
		zooms.add(zoomOut);
		
		Dimension size = new Dimension(160,110);
		compass.setPreferredSize(size);
		
		this.panel.add(compass, BorderLayout.WEST);
		this.panel.add(zooms, BorderLayout.SOUTH);

		
	}
	
	//Lets the user search for places. 
	public void addSearchBars() {
		//Start and end panels created to put relevant elements with each other.
		Dimension searchSize = new Dimension(250,30);
		JPanel startPanel = new JPanel();
		JPanel endPanel = new JPanel();
		startPanel.setOpaque(false);
		endPanel.setOpaque(false);

		
		JLabel startLabel = new JLabel("Start:");
		JLabel endLabel = new JLabel("End:");
		
		new SetStyle(startLabel, 9);
		new SetStyle(endLabel, 9);

		//Builds a dropdown menue for the places.
	    String[] h = this.middleEarth.getNameArray();
		JComboBox<String> end = new JComboBox<>(h);
		end.addActionListener(new DropDownListener(middleEarth, end));
		JComboBox<String> start = new JComboBox<>(h);
		start.addActionListener(new DropDownListener(middleEarth, start));
		this.s = start;
		this.e = end;
		//Mouseovers
		start.setToolTipText("Select a location to begin your journey at.");
		end.setToolTipText("Select a location to end your journey at.");
		
		new SetStyle(start, 9);
		new SetStyle(end, 9);
		
		end.setPreferredSize(searchSize);
		start.setPreferredSize(searchSize);

		//dist and time buttons created to toggle between criteria.
		JRadioButton dist = new JRadioButton("Distance");
		JRadioButton time = new JRadioButton("Time");
		JButton clear = new JButton("Clear");
		JButton enter = new JButton("Enter");
		//dist is selected by default
		dist.setSelected(true);
		this.dist = dist;
		this.enter = enter;
		
		//Font and focusability set
		new SetStyle(dist, 9);
		new SetStyle(time, 9);
		new SetStyle(clear, 9);
		new SetStyle(enter, 9);
		
		//enter and clear now do as name implies
		clear.addActionListener(new ClearListener(this.middleEarth));
		enter.addActionListener(new Entered(start, end, dist, time, this.middleEarth, this.outputLength, this.outputOppositeLength));
		//Each button must change the state of the other when pressed.
		dist.addActionListener(new ToggleListener(time));
		time.addActionListener(new ToggleListener(dist));
		
		//attaching objects to start and endpanels.
		startPanel.add(startLabel);
		startPanel.add(start);
		endPanel.add(endLabel);
		endPanel.add(end);
		
		//panel gets these objects added.
		this.panel.add(startPanel);
		this.panel.add(endPanel);
		this.panel.add(dist);
		this.panel.add(time);
		this.panel.add(enter);
		this.panel.add(clear, BorderLayout.CENTER);		
	}

	//Adds the calculated distance and times to the SidePanel
	public void addPanelOutput() {
		JPanel display = new JPanel();
		display.setOpaque(false);
		
		display.setPreferredSize(new Dimension(200,60));
		
		display.add(this.outputLength);
		display.add(this.outputOppositeLength);
		this.panel.add(display);
		
	}
	
	//Used to populate the JComboBoxes with cities sected by clicking the nodes.
	public void populateStart(String city) {
		this.s.setSelectedItem(city);
	}
	public void populateEnd(String city) {
		this.e.setSelectedItem(city);
	}

	//Puts description of selected location on the SidePanel
	public void addDescription(HashMap<String, String> descriptions, String city) {
		if(city == "clear"){
			this.descriptionLabel.setText("");
		}
		else{
			this.descriptionLabel.setText("<html><div style=text-align:center;font-size:100%>"+"____________________________________<br>"+descriptions.get(city)+"<br>___________________________________"+"</div></html>");
		}
		this.descriptionPanel.add(this.descriptionLabel);
	}
	
}
