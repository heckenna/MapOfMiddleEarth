import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Entered implements ActionListener {

	private JPanel panel;
	private JTextField text;
	
	public Entered(JTextField text, JPanel panel) {
		this.text = text;
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.print(text.getText());

	}

}
