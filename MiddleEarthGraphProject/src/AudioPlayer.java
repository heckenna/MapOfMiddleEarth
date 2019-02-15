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
	
	//Open a file and play. User must specify what type of file it is (.wav, .mp4, etc...). Loops continuously by default. 
	public void play(String fileName) {
		try {
			clip.open(AudioSystem.getAudioInputStream(new File("src/" + fileName)));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	//Allows the audio clip to be paused and resumed.
	public void changeState(String value) {
		if(value == "Pause Music"){
			clip.stop();
		}
		else{
			clip.start();
		}
		
	}

}
