import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Entered implements ActionListener {

	private JTextField text;
	private JComboBox<String> start;
	private JComboBox<String> end;
	private Graph graph;
	private JRadioButton distance;
	private JRadioButton time;
	
	public Entered(JComboBox<String> start, JComboBox<String> end, JRadioButton dist, JRadioButton time,
			Graph graph) {
		this.start = start;
		this.end = end;
		this.distance = dist;
		this.time = time;
		this.graph = graph;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String startPt = (String) start.getSelectedItem();
		String endPt = (String) end.getSelectedItem();
		if(graph.hasPlace(startPt) && graph.hasPlace(endPt)) {
			if(this.distance.isSelected()) {
				graph.findShortestPath(startPt, endPt, "distance");
			}
			else graph.findShortestPath(startPt, endPt, "time");
		} else {
			System.out.print("Invalid Location\n");
		}
		graph.repaint();
	}

}
