import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameIntelligence implements GameListener, ActionListener {
	
	GameClient client;

	@Override
	public void messageReceived(String message) {
		System.out.println(message);
	}
	
	public GameIntelligence(GameClient client){
		this.client = client;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("StackClick")){
			client.send("test");
		}
	}

}
