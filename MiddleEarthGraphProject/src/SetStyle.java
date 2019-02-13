import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class SetStyle {

	//README:
	//All of these constructors basically do the same thing but for different components.
	//A font is created and added to the Object
	//the Object is made unfocusable for the purpose of making the keyListener work.
	//Also will make background transparent and add color where wanted for the desired "look"
	public SetStyle(JComboBox<String> thing, float fontSize) {
		//Makes a font object in order to create the Aniron font. It is wierd, but it works.
		try {
			Font aniron = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setFocusable(false);
		thing.setBackground(Color.WHITE);
		thing.setAutoscrolls(true);
	}

	public SetStyle(JLabel thing, float fontSize) {
		thing.setFocusable(false);
		try {
			Font aniron = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setOpaque(false);
	}

	public SetStyle(JButton thing, float fontSize) {
		thing.setFocusable(false);
		
		try {
			Font aniron = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setBackground(Color.WHITE);
		thing.setBorderPainted(false);
	}

	public SetStyle(JRadioButton thing, float fontSize) {
		thing.setFocusable(false);
		try {
			Font aniron = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
		thing.setOpaque(false);
	}

	public SetStyle(JFrame thing, int fontSize) {
		try {
			Font aniron = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/ANIRB___0.TTF"));
			Font font = aniron.deriveFont(fontSize);
			thing.setFont(font);
		} catch (FontFormatException | IOException exception) {
			exception.printStackTrace();
		}
	}

	
}
