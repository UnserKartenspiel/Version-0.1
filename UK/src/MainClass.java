public class MainClass {
	
	public static void main(String[] args) {
		try {
			new Server().start(); // Server
			new Client().start(); // Client
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
