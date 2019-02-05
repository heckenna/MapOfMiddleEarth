import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class DropDownListener implements ActionListener{
	Graph middleEarth;
	JComboBox<String> t;
	ArrayList<String> n;
	
	public DropDownListener(Graph graph, JComboBox<String> t) {
		n = new ArrayList<>();
		this.middleEarth = graph;
		this.t= t;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(t.getSelectedItem());
		for(String u : n) {
			//want to keep as only last city maybe?
			middleEarth.deactivateButton(u);
		}
		n.clear();
		n.add((String) t.getSelectedItem());
		middleEarth.activateButton((String) t.getSelectedItem());
		
	}

}
