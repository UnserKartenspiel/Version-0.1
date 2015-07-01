import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameIntelligence implements GameListener, ActionListener {

	GameClient client;
	JFrame frame;
	ArrayList<Card> stack = new ArrayList();

	ArrayList<Card> hand = new ArrayList();

	@Override
	public void messageReceived(String message) {
		if (message.substring(message.length() - 1).equals("i")) {
			System.out.println("Echo");
		} else {
			System.out.println("handleCommand");
		}
	}

	public GameIntelligence(GameClient client) {
		this.client = client;
		stack.add(new Card(1));
		stack.add(new Card(2));
		stack.add(new Card(1));
		stack.add(new Card(2));
		stack.add(new Card(2));
		stack.add(new Card(3));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Stack")) {
			stackClicked();
		}
	}

	private void stackClicked() {
		if (stack.size() > 0) {
			hand.add(stack.get(stack.size() - 1));
			stack.remove(stack.size() - 1);
			send("Karte aufgenommen");
		}else{
			send("Stack leer");
		}
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void send(String message){
		client.send(message);
	}
	
}
