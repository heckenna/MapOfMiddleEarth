import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class DropDownListener implements ActionListener{
	Graph middleEarth;
	JComboBox<String> t;

	ArrayList<String> citiesActive;

	private int thatCity;
	
	public DropDownListener(Graph graph, JComboBox<String> t, int thatCity) {
		citiesActive = new ArrayList<>();

		this.middleEarth = graph;
		this.t= t;
		this.thatCity = thatCity;
	}
	
	@Override
	//Allows buttons on the graph to be controlled by changes in the DropDown boxes.
	public void actionPerformed(ActionEvent arg0) {
		
		//Clear out array so the ArrayList stays reasonably sized.
		citiesActive.clear();
		citiesActive.add((String) t.getSelectedItem());
		this.middleEarth.activateButton((String) t.getSelectedItem(), this.thatCity);
	}

}
