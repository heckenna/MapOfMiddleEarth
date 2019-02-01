import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Entered implements ActionListener {

	private JLabel text;
	private JComboBox<String> start;
	private JComboBox<String> end;
	private Graph graph;
	private JRadioButton distance;
	private JRadioButton time;
	
	public Entered(JComboBox<String> start, JComboBox<String> end, JRadioButton dist, JRadioButton time,
			Graph graph, JLabel outputText) {
		this.start = start;
		this.end = end;
		this.distance = dist;
		this.time = time;
		this.graph = graph;
		this.text = outputText;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		//String startPt = start.getText();
		String startPt = (String) start.getSelectedItem();
		String endPt = (String) end.getSelectedItem();
		if(graph.hasPlace(startPt) && graph.hasPlace(endPt)) {
			if(this.distance.isSelected()) {
				graph.findShortestPath(startPt, endPt, "distance");
				this.text.setText("Total Length Traveled:" + this.graph.lastLength);
			}
			else {
				graph.findShortestPath(startPt, endPt, "time");
				this.text.setText("Total Length Traveled:" + this.graph.lastLength);

			}
		} else {
			System.out.print("Invalid Location\n");
		}
		graph.repaint();
	}

}
