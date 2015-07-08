import java.awt.Color;
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
	Boolean playerReady = false;
	Boolean myTurn = false;
	Boolean running = false;
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
		if (message.equals("Karte aufgenommen")) {
			opponentGetsCard();
		}
		if (message.contains("Played card")) {
			opponentPlaysCard(message);
		}
		if (message.contains("Attack")) {
			getDamaged(message);
		}
		if (message.equals("Ready")) {
			checkReady();
		}
		if (message.equals("Turn complete")) {
			newTurn();
		}
	}

	private void newTurn() {
		myTurn = true;
		for (Card card : played) {
			card.used = false;
		}
		for (Card card : hand) {
			card.used = false;
		}
	}

	private void checkReady() {
		if (playerReady && !running) {
			frame.overlayPane.setVisible(false);
			send("Ready");
			running = true;
		}
		if (running) {
			send("setStartPlayer");
			System.out.println("Wdawdawdawdawdawdawdad");
		}
	}

	private void getDamaged(String message) {
		String tmpString = message.replace("Attack ", "");
		int attackedID = Integer.parseInt(tmpString.substring(tmpString.length() - 1, tmpString.length()));
		int ap = Integer.parseInt(tmpString.substring(0, tmpString.length() - 1));
		played.get(attackedID).hp -= ap;
		if (played.get(attackedID).hp <= 0) {
			removeCardFromPlayed(attackedID);
		}else{
			played.get(attackedID).repaint();
		}
	}

	private void removeCardFromPlayed(int attackedID) {
		frame.playedPanel.remove(played.get(attackedID));
		frame.playedPanel.revalidate();
		frame.playedPanel.repaint();
		for (int i = 0; i < played.size(); i++) {
			played.get(i).setActionCommand("Played card " + i);
		}
	}

	private void opponentPlaysCard(String message) {
		frame.oHandPanel.remove(frame.oHandPanel.getComponentCount() - 1);
		frame.revalidate();
		frame.repaint();
		int id = Integer.parseInt(message.replaceAll("Played card ", ""));
		Card card = cardManager.createCard(id);
		opponentPlayed.add(card);
		opponentPlayed.get(opponentPlayed.size() - 1).setActionCommand("Played oCard " + (opponentPlayed.size() - 1));
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
		if (myTurn) {
			if (e.getActionCommand().equals("Stack")) {
				stackClicked();
			}
			if (e.getActionCommand().contains("Card on hand")) {
				handClicked(e);
			}
			if (e.getActionCommand().contains("Played card")) {
				playedClicked(e);
			}
			if (e.getActionCommand().contains("Played oCard")) {
				oPlayedClicked(e);
			}
			if (e.getActionCommand().equals("Fertig")) {
				endTurn();
			}
		}
	}

	private void endTurn() {
		myTurn = false;
		send("Turn complete");
		if (handCardSelected) {
			handCardSelected = false;
			hand.get(selectedCardID).setSpecialSelected(false);
			selectedCardID = 99;
		}
		if (playedCardSelected) {
			playedCardSelected = false;
			played.get(selectedCardID).setSpecialSelected(false);
			selectedCardID = 99;
		}
	}

	private void oPlayedClicked(ActionEvent e) {
		int oID = Integer.parseInt(e.getActionCommand().replaceAll("Played oCard ", ""));
		if (playedCardSelected) {
			attack(oID);
		}
	}

	private void attack(int oID) {
		int ap = played.get(selectedCardID).ap;
		played.get(selectedCardID).setSpecialSelected(false);
		played.get(selectedCardID).used = true;
		opponentPlayed.get(oID).hp -= ap;
		if(opponentPlayed.get(oID).hp<=0){
			removeCardFromOpponentPlayed(oID);
		}else{
			opponentPlayed.get(oID).repaint();
		}
		playedCardSelected = false;
		selectedCardID = 99;
		send("Attack " + ap + "" + oID);
	}

	private void removeCardFromOpponentPlayed(int oID) {
		frame.oPlayedPanel.remove(opponentPlayed.get(oID));
		frame.oPlayedPanel.revalidate();
		frame.oPlayedPanel.repaint();
		for (int i = 0; i < opponentPlayed.size(); i++) {
			opponentPlayed.get(i).setActionCommand("Played oCard " + i);
		}
	}

	private void playedClicked(ActionEvent e) {
		int id = Integer.parseInt(e.getActionCommand().replaceAll("Played card ", ""));
		if (!played.get(id).used) {
			if (playedCardSelected) {
				hand.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = id;
				played.get(selectedCardID).setSpecialSelected(true);
			}

			if (!playedCardSelected) {
				System.out.println(e.getActionCommand());
				selectedCardID = id;
				playedCardSelected = true;
				played.get(selectedCardID).setSpecialSelected(true);
			}
		}

	}

	private void handClicked(ActionEvent e) {
		int id = Integer.parseInt(e.getActionCommand().replaceAll("Card on hand ", ""));
		if (!hand.get(id).used) {
			if (playedCardSelected) {
				played.get(selectedCardID).setSpecialSelected(false);
				playedCardSelected = false;
			}
			if (handCardSelected) {
				hand.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = id;
				hand.get(selectedCardID).setSpecialSelected(true);
			}

			if (!handCardSelected) {
				selectedCardID = id;
				handCardSelected = true;
				hand.get(selectedCardID).setSpecialSelected(true);
			}
		}
	}

	public void stackClicked() {
		if (stack.size() > 0 && hand.size() < 5) {
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
		if (e.getComponent().equals(frame.overlayPane)) {
			playerIsReady();
		}
		if (e.getComponent().equals(frame.playedPanel) && handCardSelected) {
			hand.get(selectedCardID).setSpecialSelected(false);
			playCard(selectedCardID);
			removeCardFromHand(selectedCardID);
			handCardSelected = false;
			selectedCardID = 99;
		} else {
			if (handCardSelected) {
				hand.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = 99;
				handCardSelected = false;
			}
			if (playedCardSelected) {
				played.get(selectedCardID).setSpecialSelected(false);
				selectedCardID = 99;
				playedCardSelected = false;
			}
		}
	}

	private void playerIsReady() {
		frame.overlayPane.setReady();
		playerReady = true;
		send("Ready");
	}

	private void playCard(int selectedHandCardID) {
		played.add(hand.get(selectedHandCardID));
		played.get(played.size() - 1).setActionCommand("Played card " + (played.size() - 1));
		played.get(played.size() - 1).used = true;
		send("Played card " + hand.get(selectedHandCardID).id);
		frame.playedPanel.add(played.get(played.size() - 1));
		frame.revalidate();
		frame.repaint();
	}

	private void removeCardFromHand(int selectedHandCardID) {
		frame.handPanel.remove(hand.get(selectedHandCardID));
		frame.handPanel.revalidate();
		frame.handPanel.repaint();
		hand.remove(selectedHandCardID);
		for (int i = selectedHandCardID; i < hand.size(); i++) {
			hand.get(i).setActionCommand("Card on hand " + i);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
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
