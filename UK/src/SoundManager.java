import jaco.mp3.player.MP3Player;
import java.io.File;

public class SoundManager {

	public SoundManager() {
		new MP3Player(new File("src/audioresources/lobby.mp3")).play();
	}
}
