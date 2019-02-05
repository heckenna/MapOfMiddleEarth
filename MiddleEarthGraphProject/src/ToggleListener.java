import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class ToggleListener implements ActionListener{
	
	private JRadioButton otherButton;

	public ToggleListener(JRadioButton linkedButton){
		this.otherButton= linkedButton;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		otherButton.setSelected(!otherButton.isSelected());		
		//System.out.println("fdafds");
	}

}
