import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomListener implements ActionListener {
	
	private Graph middleEarth;
	private float zoom;

	public ZoomListener(float zoom, Graph graph) {
		this.zoom = zoom;
		this.middleEarth = graph;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.middleEarth.zoom(this.zoom);
		
	}

}
