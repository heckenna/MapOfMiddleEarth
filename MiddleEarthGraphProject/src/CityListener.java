import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CityListener implements ActionListener{
	
	Node city;
	Graph graph;
	
	public CityListener(Node city, Graph graph){
		this.city = city;
		this.graph = graph;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Print City");
		graph.findBetween(city);
		graph.repaint();
		
		
		
	}

	
}
