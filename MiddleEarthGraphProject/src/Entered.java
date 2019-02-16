import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Entered implements ActionListener {
	
/**
 * Does the graph searching algorithm.
 * Displays the path on the map, also will update the time and distances on the SidePanel
 * Also handles the "travel mode"
 */

	private JLabel outputLength;
	private JLabel outputOppositeLength;
	private JComboBox<String> start;
	private JComboBox<String> end;
	private Graph graph;
	private JRadioButton distance;
	private JRadioButton time;
	
	public Entered(JComboBox<String> start, JComboBox<String> end, JRadioButton dist, JRadioButton time,
			Graph graph, JLabel outputLength, JLabel outputOppositeLength) {
		this.start = start;
		this.end = end;
		this.distance = dist;
		this.time = time;
		this.graph = graph;
		this.outputLength = outputLength;
		this.outputOppositeLength = outputOppositeLength;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String startPt = (String) this.start.getSelectedItem();
		String endPt = (String) this.end.getSelectedItem();
		
		if(endPt.equals("Desired Distance")) {
			//Travel mode. Creates a circle around the city of a specified distance in order to show the user
			//possible locations to travel without exceeding the distance desired.
			graph.setPathColor(Color.BLACK, Color.BLACK, false);
			
			while(true) {
				//Wrapped in a loop to keep prompting the user until an Int is entered.
				try {
					//Creates a popup in order to get a desired distance from the user
					String name = JOptionPane.showInputDialog(this.graph.getFrame(),
		                    "How Far Do You Want To Travel? ", null);
					int radius = Integer.parseInt(name);
					this.graph.planTrip(radius);
					this.graph.repaint();
					break;
				}
				
				catch(Exception e){
					
					/////////
				}
			}	
		}
		
		else if(this.graph.hasPlace(startPt) && this.graph.hasPlace(endPt)) {
			//Calls the A* algorithm from Graph
			if(this.distance.isSelected()) {
				this.graph.findShortestPath(startPt, endPt, "distance");
				this.outputLength.setText("Distance: " + this.graph.getLastLength() + " Miles");
				this.outputOppositeLength.setText("Time: " + this.graph.getLastOppositeLength() + " Days");
			}
			else {
				this.graph.findShortestPath(startPt, endPt, "time");
				this.outputLength.setText("Time: " + this.graph.getLastLength() + " Days");
				this.outputOppositeLength.setText("Distance: " + this.graph.getLastOppositeLength() + " Miles");

			}
		} 
		
		else {
			//If no city is selected
			System.out.print("Invalid Location\n");
		}
		
		this.graph.repaint();
	}

}
