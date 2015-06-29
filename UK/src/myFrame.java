import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;

public class myFrame extends JFrame {
	TextArea _Eingabe;
	TextArea _Ausgabe;
	JButton _Send;
	PrintWriter _out;
	BufferedReader _in;
	ActionListener _x;

	myFrame(String Titel, PrintWriter out, BufferedReader in) {
		_out = out;
		_in = in;
		init(Titel);
	}

	void init(String Titel) {
		setLocation(400, 400);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setTitle(Titel);
		_Eingabe = new TextArea();
		_Ausgabe = new TextArea();
		_Send = new JButton();
		_x = new Action(_out, _in, _Eingabe);
		_Send.setText("Abschicken");
		_Send.setActionCommand("Send");
		_Send.addActionListener(_x);
		this.setLayout(new BorderLayout());
		Container unten = new Container();
		unten.setLayout(new BorderLayout());
		unten.add(_Eingabe, BorderLayout.CENTER);
		unten.add(_Send, BorderLayout.EAST);
		this.add(unten, BorderLayout.SOUTH);
		this.add(_Ausgabe, BorderLayout.CENTER);
	}

	public void addAusgabe(String add) {
		String temp = _Ausgabe.getText();
		temp += add;
		_Ausgabe.setText(temp);
	}
}

class Action implements ActionListener {
	PrintWriter _out;
	BufferedReader _in;
	TextArea _Eingabe;

	Action(PrintWriter out, BufferedReader in, TextArea Text) {
		_out = out;
		_in = in;
		_Eingabe = Text;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Send")) {
			String Ausgabe = _Eingabe.getText() + "\n";
			_out.print(Ausgabe);
			_out.flush();
		}
	}
}