import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SetStyle {

	public SetStyle(JComboBox<String> thing, int fontSize) {
		thing.setFocusable(false);
		thing.setFont(new Font("Aniron", Font.PLAIN,  fontSize));
	}

	public SetStyle(JLabel thing, int fontSize) {
		thing.setFocusable(false);
		thing.setFont(new Font("Aniron", Font.PLAIN,  fontSize));
	}

	public SetStyle(JButton thing, int fontSize) {
		thing.setFocusable(false);
		thing.setFont(new Font("Aniron", Font.PLAIN,  fontSize));	
	}

	public SetStyle(JRadioButton thing, int fontSize) {
		thing.setFocusable(false);
		thing.setFont(new Font("Aniron", Font.PLAIN,  fontSize));	
	}

}
