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
		//Makes sure to change the values of TwoCities and whether or not the needed JRadioButtons are selected
		this.middleEarth.activateButton((String) t.getSelectedItem(), this.thatCity);
	}

}
