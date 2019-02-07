import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SidePanel extends JComponent{
	

	private Graph middleEarth;
	private JPanel panel;
	private JLabel outputLength;
	private JLabel outputOppositeLength;
	private JComboBox<String> s;
	private JComboBox<String> e;
	protected JRadioButton dist;
	protected JButton enter;
	//private JButton button;
	

	public SidePanel(Graph graph, JPanel frame) {
		this.s = new JComboBox<>();
		this.e = new JComboBox<>();
		this.panel = frame;
		
		this.middleEarth = graph; 
		
		Dimension preferredSize = new Dimension(300,400);
		this.panel.setPreferredSize(preferredSize );
		
		this.outputLength = new JLabel("Total Distance Traveled:" + this.middleEarth.getLastLength());
		this.outputOppositeLength = new JLabel("Total Time Traveled:" + this.middleEarth.getLastOppositeLength());
		
		this.addSearchBars();
		this.addMapNav();
		this.addTogglers();
		this.addPanelOutput();
	}

	private void addTogglers() {
		JRadioButton toggleDistance = new JRadioButton("Display Distance and Time");
		
		toggleDistance.addActionListener(new DistanceToggler(this.middleEarth));
		
		this.panel.add(toggleDistance);
	}

	public void addMapNav(){
		
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		
		north.addActionListener(new MoverListener(0, 50, this.middleEarth));
		south.addActionListener(new MoverListener(0, -50, this.middleEarth));
		east.addActionListener(new MoverListener(-50, 0, this.middleEarth));
		west.addActionListener(new MoverListener(50, 0, this.middleEarth));
		
		zoomIn.addActionListener(new ZoomListener(0.1, this.middleEarth));
		zoomOut.addActionListener(new ZoomListener(-0.1, this.middleEarth));
		
		JPanel compass = new JPanel();
		
		compass.add(north, BorderLayout.NORTH);
		compass.add(west, BorderLayout.WEST);
		compass.add(east, BorderLayout.EAST);
		compass.add(south, BorderLayout.SOUTH);
		
		JPanel zooms = new JPanel();
		
		zooms.add(zoomIn);
		zooms.add(zoomOut);
		
		Dimension size = new Dimension(137,100);
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
		
		JLabel startLabel = new JLabel("Start: ");
		JLabel endLabel = new JLabel("End: ");
		
	    String[] h = this.middleEarth.getNameArray();
		JComboBox<String> end = new JComboBox<>(h);
		JComboBox<String> start = new JComboBox<>(h);
		this.s = start;
		this.e = end;
		
		
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
		
		display.setPreferredSize(new Dimension(200,300));
		
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
	
}
