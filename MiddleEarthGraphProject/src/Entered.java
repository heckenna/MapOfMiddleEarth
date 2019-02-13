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
		String startPt = (String) start.getSelectedItem();
		String endPt = (String) end.getSelectedItem();
		
		if(endPt.equals("Desired Distance")) {
			while(true) {
				String name = JOptionPane.showInputDialog(graph.getFrame(),
	                    "How far do you want to travel?", null);
				try {
					int distance = Integer.parseInt(name);
					graph.planTrip(distance);
					graph.repaint();
					break;
				}
				catch(Exception e){
					
				}
			}
			
			
			
		}
		else if(graph.hasPlace(startPt) && graph.hasPlace(endPt)) {
			if(this.distance.isSelected()) {
				graph.findShortestPath(startPt, endPt, "distance");
				this.outputLength.setText("Distance: " + this.graph.getLastLength() + " Miles");
				this.outputOppositeLength.setText("Time: " + this.graph.getLastOppositeLength() + " Days");
			}
			else {
				graph.findShortestPath(startPt, endPt, "time");
				this.outputLength.setText("Time: " + this.graph.getLastLength() + " Days");
				this.outputOppositeLength.setText("Distance: " + this.graph.getLastOppositeLength() + " Miles");

			}
		} else {
			System.out.print("Invalid Location\n");
		}
		graph.repaint();
	}

}
