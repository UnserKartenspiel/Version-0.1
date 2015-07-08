import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class CardBackside extends JButton{
	
	public CardBackside(){
		setSize(100, 140);
		setPreferredSize(new Dimension(100,140));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(getClass().getResource("imgresources/back.bmp")),0,0,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
