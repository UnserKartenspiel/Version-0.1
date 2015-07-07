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
	
	CardManager cardManager = new CardManager(this);
	
	ArrayList<Card> stack = new ArrayList();
	ArrayList<Card> hand = new ArrayList();
	ArrayList<Card> played = new ArrayList();
	ArrayList<Card> opponentPlayed = new ArrayList();
	Boolean handCardSelected = false;
	Boolean playedCardSelected = false;
	int selectedCardID = 99; // Keine Handkarte ausgewählt
	
	public GameIntelligence(GameClient client) {
		this.client = client;
		stack.add(cardManager.rndCardFromDeck());
		stack.add(cardManager.rndCardFromDeck());
		stack.add(cardManager.rndCardFromDeck());
		stack.add(cardManager.rndCardFromDeck());
		stack.add(cardManager.rndCardFromDeck());
		stack.add(cardManager.rndCardFromDeck());
	}
	
	
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
		Card card = cardManager.createCard(id);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Stack")) {
			stackClicked();
		}
		if (e.getActionCommand().contains("Card on hand")) {
			handClicked(e);
		}
		if (e.getActionCommand().contains("Played card")){
			playedClicked(e);
		}
	}

	private void playedClicked(ActionEvent e) {
		if (playedCardSelected) {
			hand.get(selectedCardID).setSpecialSelected(false);
			selectedCardID = Integer.parseInt(e.getActionCommand().replaceAll("Played card ", ""));
			played.get(selectedCardID).setSpecialSelected(true);
		}

		if (!playedCardSelected) {
			System.out.println(e.getActionCommand());
			selectedCardID = Integer.parseInt(e.getActionCommand().replaceAll("Played card ", ""));
			playedCardSelected = true;
			played.get(selectedCardID).setSpecialSelected(true);
		}
	}


	private void handClicked(ActionEvent e) {
		if(playedCardSelected){
			played.get(selectedCardID).setSpecialSelected(false);
			playedCardSelected = false;
		}
		if (handCardSelected) {
			hand.get(selectedCardID).setSpecialSelected(false);
			selectedCardID = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ", ""));
			hand.get(selectedCardID).setSpecialSelected(true);
		}

		if (!handCardSelected) {
			selectedCardID = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ", ""));
			handCardSelected = true;
			hand.get(selectedCardID).setSpecialSelected(true);
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
		if (e.getComponent().equals(frame.playedPanel) && handCardSelected) {
			hand.get(selectedCardID).setSpecialSelected(false);
			playCard(selectedCardID);
			removeCardFromHand(selectedCardID);
			handCardSelected = false;
			selectedCardID = 99;
		}
		else {
			System.out.println("selection reset");
			if (handCardSelected) {
				hand.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = 99;
				handCardSelected = false;
			}
			if (playedCardSelected){
				played.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = 99;
				playedCardSelected = false;
			}
		}
	}

	private void playCard(int selectedHandCardID) {
		played.add(hand.get(selectedHandCardID));
		played.get(played.size()-1).setActionCommand("Played card " + (played.size()-1));
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
