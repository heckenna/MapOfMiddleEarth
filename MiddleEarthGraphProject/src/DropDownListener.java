import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class DropDownListener implements ActionListener{
	Graph middleEarth;
	JComboBox<String> t;
	ArrayList<String> n;
	private int thatCity;
	
	public DropDownListener(Graph graph, JComboBox<String> t, int thatCity) {
		n = new ArrayList<>();
		this.middleEarth = graph;
		this.t= t;
		this.thatCity = thatCity;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*for(String u : n) {
			middleEarth.deactivateButton(u, thatCity);
		}
		n.clear();
		n.add((String) t.getSelectedItem());*/
		middleEarth.activateButton((String) t.getSelectedItem(), thatCity);
		
	}

}
