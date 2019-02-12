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
		
		this.addSearchBars();
		this.addMapNav();
		this.addTogglers();
		this.addPanelOutput();
		this.addDescriptionPanel();
		this.addAudioPanel();
		
	}

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

	private void addDescriptionPanel() {
		this.descriptionLabel = new JLabel();
		this.descriptionPanel = new JPanel();
		this.descriptionPanel.setPreferredSize(new Dimension(300, 525));
		this.descriptionPanel.setOpaque(false);
		new SetStyle(this.descriptionLabel, 10);
		this.panel.add(this.descriptionPanel);
		this.descriptionPanel.add(this.descriptionLabel);
		
	}

	private void addTogglers() {
		JRadioButton toggleDistance = new JRadioButton("Display Distance and Time");
		
		toggleDistance.addActionListener(new DistanceToggler(this.middleEarth));
		new SetStyle(toggleDistance, 10);

		this.panel.add(toggleDistance);
		
		JRadioButton toggleEdges = new JRadioButton("Display Edges");
		
		toggleEdges.addActionListener(new EdgeToggler(this.middleEarth));
		new SetStyle(toggleEdges, 10);
		
		this.panel.add(toggleEdges);
		
		JRadioButton toggleNames = new JRadioButton("Display Names");
		
		toggleNames.addActionListener(new NameToggler(this.middleEarth));
		new SetStyle(toggleNames, 10);
		
		this.panel.add(toggleNames);
	}

	public void addMapNav(){
		
		
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		
		new SetStyle(north, 10);
		new SetStyle(south, 10);
		new SetStyle(east, 10);
		new SetStyle(west, 10);

		new SetStyle(zoomIn, 10);
		new SetStyle(zoomOut, 10);

		
		
		MoverListener moveNorth = new MoverListener(0, 50, this.middleEarth);
		
		north.addActionListener(moveNorth);
		south.addActionListener(new MoverListener(0, -50, this.middleEarth));
		east.addActionListener(new MoverListener(-50, 0, this.middleEarth));
		west.addActionListener(new MoverListener(50, 0, this.middleEarth));
		
		zoomIn.addActionListener(new ZoomListener(0.1, this.middleEarth));
		zoomOut.addActionListener(new ZoomListener(-0.1, this.middleEarth));
		
		panel.setFocusable(true);
		
		panel.addKeyListener(new KeysToClick(north, south, east, west, enter, zoomIn, zoomOut));
		/*north.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
			put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP,0), "Up_pressed");
        north.getActionMap().put("Up_pressed", new ActionClick(moveNorth));*/

		
		JPanel compass = new JPanel();
		compass.setOpaque(false);
		
		compass.add(north, BorderLayout.NORTH);
		compass.add(west, BorderLayout.WEST);
		compass.add(east, BorderLayout.EAST);
		compass.add(south, BorderLayout.SOUTH);
		
		JPanel zooms = new JPanel();
		zooms.setOpaque(false);

		
		zooms.add(zoomIn);
		zooms.add(zoomOut);
		
		Dimension size = new Dimension(160,120);
		compass.setPreferredSize(size);
		//size.setSize(50, 100);
		//zooms.setSize(size);
		
		this.panel.add(compass, BorderLayout.WEST);
		this.panel.add(zooms, BorderLayout.SOUTH);

		
	}
	
	public void addSearchBars() {
		Dimension searchSize = new Dimension(250,30);
		JPanel startPanel = new JPanel();
		JPanel endPanel = new JPanel();
		startPanel.setOpaque(false);
		endPanel.setOpaque(false);

		
		JLabel startLabel = new JLabel("Start:");
		JLabel endLabel = new JLabel("End:");
		
		new SetStyle(startLabel, 9);
		new SetStyle(endLabel, 9);

		
	    String[] h = this.middleEarth.getNameArray();
		JComboBox<String> end = new JComboBox<>(h);
		end.addActionListener(new DropDownListener(middleEarth, end));
		JComboBox<String> start = new JComboBox<>(h);
		start.addActionListener(new DropDownListener(middleEarth, start));
		this.s = start;
		this.e = end;
		start.setToolTipText("Select a location to begin your journey at.");
		end.setToolTipText("Select a location to end your journey at.");
		
		new SetStyle(start, 9);
		new SetStyle(end, 9);
		
		end.setPreferredSize(searchSize);
		start.setPreferredSize(searchSize);

		JRadioButton dist = new JRadioButton("Distance");
		JRadioButton time = new JRadioButton("Time");
		JButton clear = new JButton("Clear");
		JButton enter = new JButton("Enter");
		//dist is selected by default
		dist.setSelected(true);
		this.dist = dist;
		this.enter = enter;
		
		new SetStyle(dist, 9);
		new SetStyle(time, 9);
		new SetStyle(clear, 9);
		new SetStyle(enter, 9);
		
		clear.addActionListener(new ClearListener(this.middleEarth));
		enter.addActionListener(new Entered(start, end, dist, time, this.middleEarth, this.outputLength, this.outputOppositeLength));
		dist.addActionListener(new ToggleListener(time));
		time.addActionListener(new ToggleListener(dist));
		
		startPanel.add(startLabel);
		startPanel.add(start);
		endPanel.add(endLabel);
		endPanel.add(end);
		
		this.panel.add(startPanel);
		this.panel.add(endPanel);
		this.panel.add(dist);
		this.panel.add(time);
		this.panel.add(enter);
		this.panel.add(clear, BorderLayout.CENTER);		
	}

	public void addPanelOutput() {
		JPanel display = new JPanel();
		display.setOpaque(false);
		
		display.setPreferredSize(new Dimension(200,60));
		
		display.add(this.outputLength);
		display.add(this.outputOppositeLength);
		this.panel.add(display);
		
	}
	
	public void populateStart(String city) {
		this.s.setSelectedItem(city);
	}
	public void populateEnd(String city) {
		this.e.setSelectedItem(city);
	}

	public void addDescription(HashMap<String, String> descriptions, String city) {
		if(city == "clear"){
			this.descriptionLabel.setText("");
		}
		else{
			this.descriptionLabel.setText("<html><div style=text-align:center>"+"____________________________________<br>"+descriptions.get(city)+"<br>___________________________________"+"</html>");
		}
		this.descriptionPanel.add(this.descriptionLabel);
	}
	
}
