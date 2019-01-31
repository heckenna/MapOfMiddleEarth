import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Entered implements ActionListener {

	private JTextField text;
	//private JTextField start;
	private JComboBox start;
	private JTextField end;
	private String metric;
	private Graph graph;
	
	public Entered(JTextField start, JTextField end, String metric, Graph graph) {
		//this.start = start;
		this.end = end;
		this.metric = metric;
		this.graph = graph;
	}
	public Entered(JComboBox start, JTextField end, String metric, Graph graph) {
		this.start = start;
		this.end = end;
		this.metric = metric;
		this.graph = graph;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//String startPt = start.getText();
		String startPt = (String) start.getSelectedItem();
		String endPt = end.getText();
		if(graph.hasPlace(startPt) && graph.hasPlace(endPt)) {
			graph.findShortestPath(startPt, endPt, metric);
		} else {
			System.out.print("Invalid Location\n");
		}
		graph.repaint();
	}

}
