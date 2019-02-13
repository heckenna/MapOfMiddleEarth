import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class ToggleListener implements ActionListener{
	
	private JRadioButton otherButton;

	//links two buttons so one button's state affects the state of the other.
	public ToggleListener(JRadioButton linkedButton){
		this.otherButton= linkedButton;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		otherButton.setSelected(!otherButton.isSelected());		
	}

}
