import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameIntelligence implements GameListener, ActionListener, MouseListener{

	GameClient client;
	Frame frame;
	ArrayList<Card> stack = new ArrayList();
	ArrayList<Card> hand = new ArrayList();
	Boolean cardSelected = false;
	int selectedHandCardID = 99;   //Keine Handkarte ausgewählt

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
		stack.add(new Card(1,this));
		stack.add(new Card(2,this));
		stack.add(new Card(1,this));
		stack.add(new Card(2,this));
		stack.add(new Card(2,this));
		stack.add(new Card(3,this));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Stack")) {
			stackClicked();
		}
		if (e.getActionCommand().contains("Card on hand")){
			handClicked(e);
		}
	}

	private void handClicked(ActionEvent e) {
		selectedHandCardID = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ","" ));
		cardSelected = true;
	}

	public void stackClicked() {
		if (stack.size() > 0) {
			hand.add(stack.get(stack.size() - 1));
			stack.remove(stack.size() - 1);
			send("Karte aufgenommen");
			hand.get(hand.size()-1).setActionCommand("Card on hand " + (hand.size()-1));
			frame.handPanel.add(hand.get(hand.size()-1));
			frame.revalidate();
			frame.repaint();
		}else{
			send("Stack leer");
		}
	}

	public void send(String message){
		client.send(message);
	}
	
	public void setFrame(Frame frame){
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent().equals(frame.playedPanel) && cardSelected){
			System.out.println("Spiele Karte aus");
			removeCardFromHand(selectedHandCardID);
			cardSelected = false;
			selectedHandCardID = 99;
		}
	}

	private void removeCardFromHand(int selectedHandCardID2) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
