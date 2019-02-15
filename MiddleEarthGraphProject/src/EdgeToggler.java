import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Allows the user to toggle the edge drawing. This way, the user can choose not to display the 
 * 	edges, which can de-clutter the screen. 
 * 
 */
public class EdgeToggler implements ActionListener {

	Graph middleEarth;
	
	public EdgeToggler(Graph graph) {
		
		this.middleEarth = graph;
	}

	@Override
	/**
	 * 
	 * Toggles the edges and repaints the screen. 
	 * 
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		this.middleEarth.toggleEdges();
 		this.middleEarth.repaint();
	}

}
