import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceToggler implements ActionListener {

/**
 * 	Toggles the visibility of the distance markers on the graph
 */
	Graph middleEarth;
	
	public DistanceToggler(Graph graph) {
		this.middleEarth = graph;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.toggleDistance();
 		this.middleEarth.repaint();
	}

}
