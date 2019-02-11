import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SetStyle {

	public SetStyle(JComboBox<String> thing, float fontSize) {
		//Makes a font object in order to create the Aniron font. It is wierd, but it works.
		Font newR = new Font("Times New Roman", 12, 12);
		try {
			Font aniron = newR.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setFocusable(false);
		thing.setBackground(Color.BLACK);
		thing.setForeground(Color.RED);
		thing.setAutoscrolls(true);
	}

	public SetStyle(JLabel thing, float fontSize) {
		thing.setFocusable(false);
		Font newR = new Font("Times New Roman", 12, 12);
		try {
			Font aniron = newR.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setOpaque(false);
		thing.setForeground(Color.RED);
	}

	public SetStyle(JButton thing, float fontSize) {
		thing.setFocusable(false);
		Font newR = new Font("Times New Roman", 12, 12);
		try {
			Font aniron = newR.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setBackground(Color.BLACK);
		thing.setForeground(Color.RED);
		thing.setBorderPainted(false);
	}

	public SetStyle(JRadioButton thing, float fontSize) {
		thing.setFocusable(false);
		Font newR = new Font("Times New Roman", 12, 12);
		try {
			Font aniron = newR.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setOpaque(false);
		thing.setForeground(Color.RED);
	}

	
}
