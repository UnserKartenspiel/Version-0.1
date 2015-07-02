import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameIntelligence implements GameListener, ActionListener, MouseListener {

	GameClient client;
	Frame frame;
	ArrayList<Card> stack = new ArrayList();
	ArrayList<Card> hand = new ArrayList();
	ArrayList<Card> played = new ArrayList();
	ArrayList<Card> opponentPlayed = new ArrayList();
	Boolean cardSelected = false;
	int selectedHandCardID = 99; // Keine Handkarte ausgewählt
	int selectedPlayedCardID = 99; // Keine gespielte Karte ausgewählt

	@Override
	public void messageReceived(String message) {
		if (message.substring(message.length() - 1).equals("i")) {
		} else {
			handle(message);
		}
	}

	private void handle(String message) {
		if(message.equals("Karte aufgenommen")){
			opponentGetsCard();
		}if(message.contains("Karte ausgespielt")){
			opponentPlaysCard(message);
		}
	}

	private void opponentPlaysCard(String message) {
		frame.oHandPanel.remove(frame.oHandPanel.getComponentCount()-1);
		frame.revalidate();
		frame.repaint();
		int id = Integer.parseInt(message.replaceAll("Karte ausgespielt ",""));
		Card card = new Card(id,this);
		opponentPlayed.add(card);
		opponentPlayed.get(opponentPlayed.size()-1).setActionCommand("Karte auf gegnerischer Hand " + id);
		frame.oPlayedPanel.add(card);
		frame.revalidate();
		frame.repaint();
	}

	private void opponentGetsCard() {
		frame.oHandPanel.add(new CardBackside());
		frame.revalidate();
		frame.repaint();
	}

	public GameIntelligence(GameClient client) {
		this.client = client;
		stack.add(new Card(1, this));
		stack.add(new Card(2, this));
		stack.add(new Card(1, this));
		stack.add(new Card(2, this));
		stack.add(new Card(2, this));
		stack.add(new Card(3, this));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Stack")) {
			stackClicked();
		}
		if (e.getActionCommand().contains("Card on hand")) {
			handClicked(e);
		}
	}

	private void handClicked(ActionEvent e) {
		if (cardSelected) {
			hand.get(selectedHandCardID).setSpecialSelected(false);
			selectedHandCardID = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ", ""));
			hand.get(selectedHandCardID).setSpecialSelected(true);
		}

		if (!cardSelected) {
			selectedHandCardID = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ", ""));
			cardSelected = true;
			hand.get(selectedHandCardID).setSpecialSelected(true);
		}
	}

	public void stackClicked() {
		if (stack.size() > 0) {
			hand.add(stack.get(stack.size() - 1));
			stack.remove(stack.size() - 1);
			send("Karte aufgenommen");
			hand.get(hand.size() - 1).setActionCommand("Card on hand " + (hand.size() - 1));
			frame.handPanel.add(hand.get(hand.size() - 1));
			frame.revalidate();
			frame.repaint();
		} else {
			send("Stack leer");
		}
	}

	public void send(String message) {
		client.send(message);
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(frame.playedPanel) && cardSelected) {
			hand.get(selectedHandCardID).setSpecialSelected(false);
			playCard(selectedHandCardID);
			removeCardFromHand(selectedHandCardID);
			cardSelected = false;
			selectedHandCardID = 99;
		} else {
			System.out.println("selection reset");
			if (cardSelected) {
				hand.get(selectedHandCardID).setSpecialSelected(false);
				selectedHandCardID = 99;
				selectedPlayedCardID = 99;
				cardSelected = false;
			}
		}
	}

	private void playCard(int selectedHandCardID) {
		played.add(hand.get(selectedHandCardID));
		played.get(played.size()-1).setActionCommand("Ausgespielte Karte " + played.get(played.size()-1));
		send("Karte ausgespielt " + hand.get(selectedHandCardID).id);
		frame.playedPanel.add(played.get(played.size()-1));
		frame.revalidate();
		frame.repaint();
	}

	private void removeCardFromHand(int selectedHandCardID) {
		frame.handPanel.remove(hand.get(selectedHandCardID));
		frame.revalidate();
		frame.repaint();
		hand.remove(selectedHandCardID);
		for(int i = selectedHandCardID;i<hand.size();i++){
			hand.get(i).setActionCommand("Card on hand " + i);
		}
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
