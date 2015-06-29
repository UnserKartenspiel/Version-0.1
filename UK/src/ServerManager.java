import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerManager {
	
		public void init(){
			try {

				new Server().start();	// Server
				new Client().start();	// Client

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	class Server extends Thread{

		ServerSocket 	_ServerSocket	= null;
	    Socket 			_ClientSocket 	= null;
	    PrintWriter 	_out 			= null;
	    BufferedReader 	_in 			= null;		

		Server() throws Exception{
			_ServerSocket = new ServerSocket(4711);
		}

		public void run(){
			while(true){
				try {

					_ClientSocket 	= _ServerSocket.accept();														// Warte auf Verbindung
		            _out 			= new PrintWriter(_ClientSocket.getOutputStream(), true);						// Ausgabestrom
		            _in 			= new BufferedReader(new InputStreamReader(_ClientSocket.getInputStream()));	// Eingabestrom

					while(true){
						String incoming = _in.readLine();
						if(incoming.equals("exit")) {
							break;
						} // if
						else{
							System.out.println("Serveressage received " + incoming);
						} // else

					} //while

				break;		

				} catch (IOException e){
					System.out.println("FehlerrverSocket.accept()");
				}//catch

			}//while
		}

}
