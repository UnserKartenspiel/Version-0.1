import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Card extends JButton{
	Border defaultBorder = this.getBorder();
	Border selectedBorder = new LineBorder(Color.BLACK,3);
	int id;
	
	public Card(int id, ActionListener aL){
		this.id  = id;
		setText(Integer.toString(id));
		addActionListener(aL);
		setSize(100, 140);
		setPreferredSize(new Dimension(100,140));
		
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("imgresources/khk.bmp"));
			setIcon(new ImageIcon(img));
		} catch (IOException e) {
			System.out.println("Icon Error");
			e.printStackTrace();
		}
	}
	
	
	
	
	public void setSpecialSelected(Boolean selected){
		System.out.println("debug");
		if(selected){
			setBorder(selectedBorder);
		}else{
			setBorder(defaultBorder);
		}
	}

}
