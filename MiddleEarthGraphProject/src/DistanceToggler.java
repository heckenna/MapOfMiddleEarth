import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceToggler implements ActionListener {

	Graph middleEarth;
	
	public DistanceToggler(Graph graph) {
		this.middleEarth = graph;
	}
	//Toggles the visibility of the distance markers on the graph
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.toggleDistance();
 		this.middleEarth.repaint();
	}

}
