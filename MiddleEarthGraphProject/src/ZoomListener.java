import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomListener implements ActionListener {
	
	private Graph graph;
	private double zoom;

	public ZoomListener(double d, Graph graph) {
		this.zoom = d;
		this.graph = graph;
	}

	//Changes the x and y zoom features to increase or decrease, depending on which button was activated.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//This allows us to zoom in or out by a specified amount.
		this.graph.zoom(this.zoom);

		//Repaints to show the changes
		graph.repaint();
	}
}
