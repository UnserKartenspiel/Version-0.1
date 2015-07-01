import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Card extends JButton{
	int id;
	
	public Card(int id, ActionListener aL){
		this.id  = id;
		this.setText(Integer.toString(id));
		this.addActionListener(aL);
	}

}
