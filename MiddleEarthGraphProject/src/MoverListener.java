import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoverListener implements ActionListener {

	private Graph graph;
	private int y;
	private int x;

	public MoverListener(int x, int y, Graph graph) {
		this.x = x;
		this.y = y;
		this.graph = graph;
	}

	//Moves the center Focus of the graph. Allows for North, South, East, and West movement.
	//Also allows the zoom functionality.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.graph.moveCenter(x, y);
		graph.repaint();
	}
}
