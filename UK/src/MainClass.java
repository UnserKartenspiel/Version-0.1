import java.io.PrintWriter;

public class MainClass {
	
	Server server;
	Client client;
	
	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.init();
	}
	
	public void init(){
		try {
			server = new Server();
			server.start();// Server
			
			client = new Client();
			client.start(); // Client
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void send(int i){
		server.send(i);
	}
	
	

}
