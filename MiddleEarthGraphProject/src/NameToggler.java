import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameToggler implements ActionListener {

	Graph middleEarth;
	
	public NameToggler(Graph graph) {
		this.middleEarth = graph;
	}
	//Toggles the names being visible on the graph
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.toggleNames();
 		this.middleEarth.repaint();
	}

}
