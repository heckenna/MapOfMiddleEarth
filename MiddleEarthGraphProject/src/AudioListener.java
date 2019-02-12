import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AudioListener implements ActionListener{

	private AudioPlayer aP;
	private JButton button;
	
	public AudioListener(JButton b, AudioPlayer a){
		this.aP = a;
		this.button = b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String value = this.button.getText();
		if(value == "Play Music"){
			button.setText("Pause Music");
		}
		else{
			button.setText("Play Music");
		}
		this.aP.changeState(value);		
	}

}
