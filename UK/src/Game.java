public class Game {
	
	public static void main(String args[]){
		   GameClient client = new GameClient("localhost",4444);
		   GameIntelligence gi = new GameIntelligence(client);
		   Frame frame = new Frame(gi);
		   frame.setVisible(true);
		   client.setGi(gi);
		   gi.setFrame(frame);
	   }

}
