import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioPlayer {
	
	Clip clip;

	public AudioPlayer() {

		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException exception) {
			exception.printStackTrace();
		}
		
	}

	public void play(String fileName) {
		try {
			clip.open(AudioSystem.getAudioInputStream(new File("src/" + fileName)));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {
			System.out.println(e.toString());
			
		}

	}

	public void changeState(String value) {
		if(value == "Pause Music"){
			clip.stop();
		}
		else{
			clip.start();
		}
		
	}

}
