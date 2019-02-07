import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	
	
	private static final String TITLE = "MiddleEarth";
	
	private static final int FRAME_WIDTH = 1900;
	private static final int FRAME_HEIGHT = 1000;
	
	public static void main(String args[]){
		
		JFrame frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		frame.setTitle(TITLE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Graph middleEarth = new Graph(frame);
		
		DomainLoader loader = new DomainLoader();
		loader.loadDomain(middleEarth);
		
		
		JPanel inputPanel = new JPanel();
		SidePanel input = new SidePanel(middleEarth, inputPanel);
		middleEarth.addSidePanel(input);

		
		middleEarth.addButtons();
		inputPanel.add(input);
		
		frame.add(middleEarth, BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.EAST);

		input.addSearchBars();
		//input.addMapNav();
		//input.addPanelOutput();
		
		//input.addTextBox();
		
		frame.setVisible(true);
	}

}
