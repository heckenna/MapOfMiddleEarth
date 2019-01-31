import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main{
	
	private static final String TITLE = "MiddleEarth";
	
	private static final int FRAME_WIDTH = 1900;
	private static final int FRAME_HEIGHT = 1000;
	
	public static void main(String args[]){
		
		JFrame frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		frame.setTitle(TITLE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Graph middleEarth = new Graph(FRAME_WIDTH, FRAME_HEIGHT);
		
		GetUserInput input = new GetUserInput(middleEarth, frame);
		
		DomainLoader loader = new DomainLoader();
		loader.loadDomain(middleEarth);

		
		
//		middleEarth.insert("Point A", 75, 82);
//		middleEarth.insert("Point B", 30, 27);
//		middleEarth.insert("Point C", 69, 49);
//		middleEarth.insert("Point D", 58, 51);
//		middleEarth.insert("Point E", 24, 13);
//		middleEarth.insert("Point F", 141, 90);
//		middleEarth.insert("Point G", 9, 70);
//		middleEarth.insert("Point H", 141, 43);
//		middleEarth.insert("Point I", 64, 14);
//		middleEarth.insert("Point J", 153, 79);
//		middleEarth.insert("Point K", 122, 65);
//		middleEarth.insert("Point L", 57, 74);
//		middleEarth.insert("Point M", 122, 34);
//		middleEarth.insert("Point N", 34, 84);
//		middleEarth.insert("Point O", 108, 35);
//		middleEarth.insert("Point P", 17, 73);
//		middleEarth.insert("Point Q", 120, 53);
//		middleEarth.insert("Point R", 103, 9);
//		middleEarth.insert("Point S", 59, 10);
//		middleEarth.insert("Point T", 13, 79);
//		middleEarth.insert("Point U", 109, 66);
//		middleEarth.insert("Point V", 68, 69);
//		middleEarth.insert("Point W", 159, 69);
//		middleEarth.insert("Point X", 9, 25);
//		middleEarth.insert("Point Y", 60, 40);
//		middleEarth.insert("Point Z", 121, 46);
//		
//		middleEarth.connect("Point B", "Point E", 49);
//		middleEarth.connect("Point B", "Point X", 23);
//		middleEarth.connect("Point X", "Point E", 6);
//		middleEarth.connect("Point E", "Point S", 36);
//		middleEarth.connect("Point B", "Point I", 22);
//		middleEarth.connect("Point I", "Point S", 1);
//		middleEarth.connect("Point S", "Point R", 13);
//		middleEarth.connect("Point I", "Point R", 16);
//		middleEarth.connect("Point B", "Point Y", 31);
//		middleEarth.connect("Point I", "Point Y", 6);
//		middleEarth.connect("Point Y", "Point D", 38);
//		middleEarth.connect("Point Y", "Point C", 39);
//		middleEarth.connect("Point D", "Point C", 16);
//		middleEarth.connect("Point X", "Point G", 45);
//		middleEarth.connect("Point B", "Point P", 40);
//		middleEarth.connect("Point G", "Point P", 19);
//		middleEarth.connect("Point G", "Point T", 20);
//		middleEarth.connect("Point P", "Point T", 19);
//		middleEarth.connect("Point P", "Point N", 21);
//		middleEarth.connect("Point T", "Point N", 25);
//		middleEarth.connect("Point D", "Point V", 26);
//		middleEarth.connect("Point C", "Point V", 29);
//		middleEarth.connect("Point N", "Point L", 20);
//		middleEarth.connect("Point L", "Point V", 15);
//		middleEarth.connect("Point V", "Point A", 19);
//		middleEarth.connect("Point L", "Point A", 47);
//		middleEarth.connect("Point R", "Point O", 41);
//		middleEarth.connect("Point C", "Point O", 1);
//		middleEarth.connect("Point A", "Point U", 16);
//		middleEarth.connect("Point O", "Point M", 6);
//		middleEarth.connect("Point O", "Point Z", 26);
//		middleEarth.connect("Point U", "Point K", 43);
//		middleEarth.connect("Point M", "Point Z", 30);
//		middleEarth.connect("Point Z", "Point Q", 37);
//		middleEarth.connect("Point Q", "Point K", 27);
//		middleEarth.connect("Point U", "Point Q", 0);
//		middleEarth.connect("Point M", "Point H", 5);
//		middleEarth.connect("Point Z", "Point H", 30);
//		middleEarth.connect("Point K", "Point F", 19);
//		middleEarth.connect("Point F", "Point J", 18);
//		middleEarth.connect("Point J", "Point W", 15);
//		middleEarth.connect("Point H", "Point W", 11);
		
//		middleEarth.findShortestPath("Point X", "Point F", "distance");
//		middleEarth.findShortestPath("Point X", "Point A", "time");
		
		middleEarth.findShortestPath("hinling", "andrast", "distance");
		

		frame.add(middleEarth, BorderLayout.CENTER);
		frame.add(input, BorderLayout.EAST);
		input.addButton();
		
		
		frame.setVisible(true);
	}

}
