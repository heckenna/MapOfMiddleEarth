import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SidePanel extends JComponent{
	

	private Graph middleEarth;
	private JPanel panel;
	//private JButton button;
	

	public SidePanel(Graph graph, JPanel frame) {
		
		this.panel = frame;
		
		middleEarth = graph; 
		
		Dimension preferredSize = new Dimension(300,400);
		panel.setPreferredSize(preferredSize );
		
	}

	public void addMapNav(){
		
		JButton north = new JButton("North");
		JButton south = new JButton("South");
		JButton east = new JButton("East");
		JButton west = new JButton("West");
		
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		
		north.addActionListener(new MoverListener(0, 50, middleEarth));
		south.addActionListener(new MoverListener(0, -50, middleEarth));
		east.addActionListener(new MoverListener(-50, 0, middleEarth));
		west.addActionListener(new MoverListener(50, 0, middleEarth));
		
		zoomIn.addActionListener(new ZoomListener(0.1, middleEarth));
		zoomOut.addActionListener(new ZoomListener(-0.1, middleEarth));
		
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
		
	    String[] h = this.middleEarth.getNameArray();
		JComboBox<String> end = new JComboBox<>(h);
		JComboBox<String> start = new JComboBox<>(h);
		
		end.setPreferredSize(searchSize);
		start.setPreferredSize(searchSize);

		
		JRadioButton dist = new JRadioButton("Distance");
		JRadioButton time = new JRadioButton("Time");
		JButton clear = new JButton("Clear");
		JButton enter = new JButton("Enter");
		//dist is selected by default
		dist.setSelected(true);
		
		clear.addActionListener(new ClearListener(middleEarth));
		enter.addActionListener(new Entered(start, end, dist, time, middleEarth));
		dist.addActionListener(new ToggleListener(time));
		time.addActionListener(new ToggleListener(dist));
		
		
		this.panel.add(start);
		this.panel.add(end);
		this.panel.add(dist);
		this.panel.add(time);
		this.panel.add(enter);
		this.panel.add(clear, BorderLayout.CENTER);
		
	}

	public void addOutput() {
		JPanel outputPanel = new JPanel();
		outputPanel.setPreferredSize(new Dimension(200,300));
		outputPanel.add(new JButton("Hello"));
		
		this.panel.add(outputPanel);
		
	}
	
}
