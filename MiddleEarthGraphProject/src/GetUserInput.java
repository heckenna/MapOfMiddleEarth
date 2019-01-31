import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;

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

		
		
		north.addActionListener(new MoverListener(0, 50, middleEarth));
		south.addActionListener(new MoverListener(0, -50, middleEarth));
		east.addActionListener(new MoverListener(-50, 0, middleEarth));
		west.addActionListener(new MoverListener(50, 0, middleEarth));
		
		JPanel compass = new JPanel();
		
		compass.add(north, BorderLayout.NORTH);
		compass.add(west, BorderLayout.WEST);
		compass.add(east, BorderLayout.EAST);
		compass.add(south, BorderLayout.SOUTH);
		
		Dimension size = new Dimension(137,200);
		compass.setPreferredSize(size);
		
		this.panel.add(compass, BorderLayout.SOUTH);

		
	}
	
	
	
	
	public void addSearchBars() {
		//JTextField start = new JTextField(20);
		String[] h = new String[] {"andrast", "hobbiton" , "rivendell"};
		
		JTextField end = new JTextField(20);
		JComboBox<String> start = new JComboBox<String>(h);
			

		JButton distance = new JButton("Find Distance");
		JButton time = new JButton("Find Shortest Time");
		
		distance.addActionListener(new Entered(start, end, "distance", middleEarth));
		time.addActionListener(new Entered(start, end, "time", middleEarth));

		
		this.panel.add(start, BorderLayout.EAST);
		this.panel.add(end, BorderLayout.EAST);
		this.panel.add(distance);
		this.panel.add(time);
		
	}
	
	public ArrayList<String> scan() {
		//System.out.println("scanning");
		
		
		
		ArrayList<String> places = new ArrayList<String>();
		
		Scanner in = new Scanner(System.in);
		System.out.print("What location do you wish to start at?");
		
		String s = in.nextLine();
		while (!middleEarth.hasPlace(s)) {
			System.out.print("That location does not exist. Please pick a valid location.");
			s = in.nextLine();
		}
		places.add(s);
		
		System.out.print("What location do you wish to end at?");
		
		String t = in.nextLine();
		while (!middleEarth.hasPlace(t)) {
			System.out.print("That location does not exist. Please pick a valid location.");
			t = in.nextLine();
		}
		places.add(t);
		
		System.out.print("Which metric would you like to use? time or distance?");
		
		String metric = in.nextLine();
		while (!metric.equals("time") && !metric.equals("distance")) {
			System.out.print("That metric does not exist. Please pick a valid metric.");
			metric = in.nextLine();
		}
		places.add(metric);
		return places;
	}
	
	
	
}
