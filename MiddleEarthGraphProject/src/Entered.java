import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
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
		//String startPt = start.getText();
		String startPt = (String) start.getSelectedItem();
		String endPt = (String) end.getSelectedItem();
		if(graph.hasPlace(startPt) && graph.hasPlace(endPt)) {
			if(this.distance.isSelected()) {
				graph.findShortestPath(startPt, endPt, "distance");
				this.outputLength.setText("Total Distance Traveled:" + this.graph.getLastLength());
				this.outputOppositeLength.setText("Total Time Traveled:" + this.graph.getLastOppositeLength());
			}
			else {
				graph.findShortestPath(startPt, endPt, "time");
				this.outputLength.setText("Total Time Traveled:" + this.graph.getLastLength());
				this.outputOppositeLength.setText("Total Distance Traveled:" + this.graph.getLastOppositeLength());

			}
		} else {
			System.out.print("Invalid Location\n");
		}
		graph.repaint();
	}

}
