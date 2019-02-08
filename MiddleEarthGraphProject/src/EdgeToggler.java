import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdgeToggler implements ActionListener {

	Graph middleEarth;
	
	public EdgeToggler(Graph graph) {
		this.middleEarth = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.toggleEdges();
 		this.middleEarth.repaint();
	}

}
