import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GetUserInput extends JComponent{

	private Graph middleEarth;
	private JPanel panel;
	//private JButton button;
	

	public GetUserInput(Graph graph, JPanel frame) {
		
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
		
		Dimension size = new Dimension(137,200);
		compass.setPreferredSize(size);
		//size.setSize(50, 100);
		//zooms.setSize(size);
		
		this.panel.add(compass, BorderLayout.CENTER);
		this.panel.add(zooms, BorderLayout.SOUTH);

		
	}
	
	
	
	
	public void addSearchBars() {
		//JTextField start = new JTextField(20);
		//String[] h = new String[] {"andrast", "hobbiton" , "rivendell"};
	    String[] h = this.middleEarth.getNameArray();
		//Object[] u = y.toArray();
		JComboBox<String> end = new JComboBox<>(h);
		JComboBox<String> start = new JComboBox<>(h);
		
			

		JButton distance = new JButton("Find Distance");
		JButton time = new JButton("Find Shortest Time");
		
		distance.addActionListener(new Entered(start, end, "distance", middleEarth));
		time.addActionListener(new Entered(start, end, "time", middleEarth));

		
		this.panel.add(start, BorderLayout.EAST);
		this.panel.add(end, BorderLayout.EAST);
		this.panel.add(distance);
		this.panel.add(time);
		
	}
	
}
