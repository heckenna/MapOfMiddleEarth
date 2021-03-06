import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameToggler implements ActionListener {

/** 
 * Toggles the names being visible on the graph
 */
	Graph middleEarth;
	
	public NameToggler(Graph graph) {
		this.middleEarth = graph;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Turns on and off the displaying of names on the map.
		this.middleEarth.toggleNames();
 		this.middleEarth.repaint();
	}

}
