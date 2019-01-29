import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GetUserInput extends JComponent{

	private Graph middleEarth;
	//private JFrame frame;
	//private JButton button;
	

	public GetUserInput(Graph graph, JFrame frame) {
		//JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		middleEarth = graph; 
		JButton button = new JButton("ASDDSF");
		
		panel.add(button);
		frame.add(panel);
		
		
		frame.setVisible(true);
		
	}

	public ArrayList<String> scan() {
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
