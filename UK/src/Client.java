import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
	myFrame frame;
	Socket _Socket = null;
	PrintWriter _out = null;
	BufferedReader _in = null;
	Scanner _keyboard = new Scanner(System.in);

	Client() {
		try {
			_Socket = new Socket("localhost", 4711);
			_out = new PrintWriter(_Socket.getOutputStream(), true);
			_in = new BufferedReader(new InputStreamReader(_Socket.getInputStream()));
			frame = new myFrame("Chat :: Client", _out, _in);
		} catch (Exception e) {
			System.exit(1);
		} // catch
	} // Chatter

	public void run() {
		/*
		 * while(true){ String userInput; System.out.println(
		 * "Info   #: Type exit to close server connection"); userInput =
		 * _keyboard.nextLine(); if(userInput.equals("exit")){
		 * _out.print(userInput+"\n"); _out.flush(); // sende sofort break; }//
		 * if else{ _out.print(userInput+"\n"); _out.flush(); // sende sofort
		 * }// else } // while
		 */
		while (true) {
			String incoming;
			try {
				incoming = _in.readLine();
				frame.addAusgabe(incoming);
			} // try
			catch (IOException e) {
				e.printStackTrace();
			}
		} // while
			// } // run()
	}
}