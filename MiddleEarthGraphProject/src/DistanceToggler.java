import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceToggler implements ActionListener {

	Graph middleEarth;
	
	public DistanceToggler(Graph graph) {
		this.middleEarth = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.toggleDisplay();
;		this.middleEarth.repaint();
	}

}
