import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomListener implements ActionListener {
	
	private Graph graph;
	private double zoom;

	public ZoomListener(double d, Graph graph) {
		this.zoom = d;
		this.graph = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.graph.zoom(this.zoom);
		
		graph.repaint();
		
	}

}
