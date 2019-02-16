import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener{
	
/**
 * Clear
 */
	private Graph graph;

	public ClearListener(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.graph.setPathColor(Color.BLACK, Color.BLACK, false);
		//This needs fixed. JButtons may need set false, but twoCities should NOT be cleared at this time
		this.graph.clearButtons();
		graph.repaint();
	}

}
