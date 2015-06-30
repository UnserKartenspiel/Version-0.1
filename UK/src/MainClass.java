import java.io.PrintWriter;
import java.util.ArrayList;

public class MainClass implements communicationListener{
	
	Server server;
	Client client;
	Boolean isServer = true;
	
	ArrayList<Card> stack = new ArrayList();
	ArrayList<Card> myDeck = new ArrayList();
	
	Monster monster1 = new Monster(1,"Feuerenzo",100,200);
	
	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.initNetwork();
		main.init();
	}
	
	public void initNetwork(){
		try {
			server = new Server(this);
			server.start();// Server
			
			client = new Client(this);
			client.start(); // Client
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	private void init() {
		
		stack.add(monster1);
		stack.add(monster1);
		stack.add(monster1);
		stack.add(monster1);
		
		getCard();
		
	}

	public void getCard(){
		myDeck.add(stack.get(stack.size()-1));
		stack.remove(stack.size()-1);
		int id = stack.get(stack.size()-1).id;
		if(isServer){
			if(id<10){
				send("000"+Integer.toString(id));
			}else{
				send("00"+Integer.toString(id));
			}
		}else{
			if(id<10){
				send("010"+Integer.toString(id));
			}else{
				send("01"+Integer.toString(id));
			};
		}
		
	}
	
	
	
	
	
	public void send(String message){
		if(isServer){
			server.send(message);
		}else{
			client.send(message);
		}
	}

	@Override
	public void messageReceived(String message) {
		if(isServer){
			System.out.println("debug");
		}else{
			
		}
	}
	

}

interface communicationListener{
	public void messageReceived(String message);
}
