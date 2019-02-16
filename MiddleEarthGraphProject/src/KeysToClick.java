import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class KeysToClick implements KeyListener {

/**
 * When the specified key is pressed, the corresponding buttons will do their thing
 * The Panel MUST be focused for this to work
 */
	
	private JButton north;
	private JButton south;
	private JButton east;
	private JButton west;
	private JButton enter;
	private JButton zoomOut;
	private JButton zoomIn;

	public KeysToClick(JButton north, JButton south, JButton east, JButton west, JButton enter, JButton zoomIn, JButton zoomOut) {
		//Lots of buttons get passed in so we can make them do actions. 
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
		this.enter = enter;
		this.zoomOut = zoomOut;
		this.zoomIn = zoomIn;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_UP) this.north.doClick();
		else if(e.getKeyCode() == e.VK_DOWN) this.south.doClick();
		else if(e.getKeyCode() == e.VK_LEFT) this.west.doClick();
		else if(e.getKeyCode() == e.VK_RIGHT) this.east.doClick();
		else if(e.getKeyCode() == e.VK_ENTER) this.enter.doClick();
		else if(e.getKeyCode() == e.VK_EQUALS || e.getKeyCode() == e.VK_SLASH) this.zoomIn.doClick();
		else if(e.getKeyCode() == e.VK_MINUS || e.getKeyCode() == e.VK_PERIOD) this.zoomOut.doClick();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub.

	}

}
