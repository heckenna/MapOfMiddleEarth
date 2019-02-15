import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class DropDownListener implements ActionListener{
	Graph middleEarth;
	JComboBox<String> t;
	ArrayList<String> citiesActive;
	
	public DropDownListener(Graph graph, JComboBox<String> t) {
		citiesActive = new ArrayList<>();
		this.middleEarth = graph;
		this.t= t;
	}
	
	@Override
	//Allows buttons on the graph to be controlled by changes in the DropDown boxes.
	public void actionPerformed(ActionEvent arg0) {
		//For every string in u, deactivate the button on the graph. 
		for(String cityName : citiesActive) {
			middleEarth.deactivateButton(cityName);
		}
		//Clear out array so the ArrayList stays reasonably sized.
		citiesActive.clear();
		citiesActive.add((String) t.getSelectedItem());
		middleEarth.activateButton((String) t.getSelectedItem());
		
	}

}
