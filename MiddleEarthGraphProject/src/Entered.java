import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Entered implements ActionListener {

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
			
			//graph.clearButtons();
			//graph.addCity(startPt);
			graph.setPathColor(Color.BLACK, Color.BLACK, false);
			
			while(true) {
				
				try {

					String name = JOptionPane.showInputDialog(this.graph.getFrame(),
		                    "How Far Do You Want To Travel? ", null);
					int radius = Integer.parseInt(name);
					this.graph.planTrip(radius);
					this.graph.repaint();
					break;
				}
				
				catch(Exception e){
					
					//
				}
			}	
		}
		
		else if(this.graph.hasPlace(startPt) && this.graph.hasPlace(endPt)) {
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
			
			System.out.print("Invalid Location\n");
		}
		
		this.graph.repaint();
	}

}
