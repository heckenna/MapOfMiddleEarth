import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener{
	
	private Graph graph;

	public ClearListener(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub.
		this.graph.setPathColor(Color.BLACK, Color.BLUE, false);
		this.graph.clearButtons();
		graph.repaint();
	}

}
