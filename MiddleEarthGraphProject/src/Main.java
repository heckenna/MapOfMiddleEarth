import javax.swing.JFrame;

public class Main {
	
	private static final String TITLE = "MiddleEarth";
	
	private static final int FRAME_WIDTH = 1900;
	private static final int FRAME_HEIGHT = 1000;
	
	public static void main(String args[]){
		
		JFrame frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		frame.setTitle(TITLE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Graph middleEarth = new Graph();
		
		middleEarth.insert("Point A", 75, 82);
		middleEarth.insert("Point B", 30, 27);
		middleEarth.insert("Point C", 69, 49);
		middleEarth.insert("Point D", 58, 51);
		middleEarth.insert("Point E", 24, 13);
		middleEarth.insert("Point F", 141, 90);
		middleEarth.insert("Point G", 9, 70);
		middleEarth.insert("Point H", 141, 43);
		middleEarth.insert("Point I", 64, 14);
		middleEarth.insert("Point J", 153, 79);
		middleEarth.insert("Point K", 122, 65);
		middleEarth.insert("Point L", 57, 74);
		middleEarth.insert("Point M", 122, 34);
		middleEarth.insert("Point N", 34, 84);
		middleEarth.insert("Point O", 108, 35);
		middleEarth.insert("Point P", 17, 73);
		middleEarth.insert("Point Q", 120, 53);
		middleEarth.insert("Point R", 103, 9);
		middleEarth.insert("Point S", 59, 10);
		middleEarth.insert("Point T", 13, 79);
		middleEarth.insert("Point U", 109, 66);
		middleEarth.insert("Point V", 68, 69);
		middleEarth.insert("Point W", 159, 69);
		middleEarth.insert("Point X", 9, 25);
		middleEarth.insert("Point Y", 60, 40);
		middleEarth.insert("Point Z", 121, 46);
		
		middleEarth.connect("Point B", "Point E", 0);
		middleEarth.connect("Point B", "Point X", 0);
		middleEarth.connect("Point X", "Point E", 0);
		middleEarth.connect("Point E", "Point S", 0);
		middleEarth.connect("Point B", "Point I", 0);
		middleEarth.connect("Point I", "Point S", 0);
		middleEarth.connect("Point S", "Point R", 0);
		middleEarth.connect("Point I", "Point R", 0);
		middleEarth.connect("Point B", "Point Y", 0);
		middleEarth.connect("Point I", "Point Y", 0);
		middleEarth.connect("Point Y", "Point D", 0);
		middleEarth.connect("Point Y", "Point C", 0);
		middleEarth.connect("Point D", "Point C", 0);
		middleEarth.connect("Point X", "Point G", 0);
		middleEarth.connect("Point B", "Point P", 0);
		middleEarth.connect("Point G", "Point P", 0);
		middleEarth.connect("Point G", "Point T", 0);
		middleEarth.connect("Point P", "Point T", 0);
		middleEarth.connect("Point P", "Point N", 0);
		middleEarth.connect("Point T", "Point N", 0);
		middleEarth.connect("Point D", "Point V", 0);
		middleEarth.connect("Point C", "Point V", 0);
		middleEarth.connect("Point N", "Point L", 0);
		middleEarth.connect("Point L", "Point V", 0);
		middleEarth.connect("Point V", "Point A", 0);
		middleEarth.connect("Point L", "Point A", 0);
		middleEarth.connect("Point R", "Point O", 0);
		middleEarth.connect("Point C", "Point O", 0);
		middleEarth.connect("Point A", "Point U", 0);
		middleEarth.connect("Point O", "Point M", 0);
		middleEarth.connect("Point O", "Point Z", 0);
		middleEarth.connect("Point U", "Point K", 0);
		middleEarth.connect("Point M", "Point Z", 0);
		middleEarth.connect("Point Z", "Point Q", 0);
		middleEarth.connect("Point Q", "Point K", 0);
		middleEarth.connect("Point U", "Point Q", 0);
		middleEarth.connect("Point M", "Point H", 0);
		middleEarth.connect("Point Z", "Point H", 0);
		middleEarth.connect("Point K", "Point F", 0);
		middleEarth.connect("Point F", "Point J", 0);
		middleEarth.connect("Point J", "Point W", 0);
		middleEarth.connect("Point H", "Point W", 0);
		
		frame.add(middleEarth);
		
		frame.setVisible(true);
	}

}
