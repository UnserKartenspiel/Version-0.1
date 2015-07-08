import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.Popup;

public class Card extends JButton{
	Border defaultBorder = this.getBorder();
	Border selectedBorder = new LineBorder(Color.BLACK,3);
	int id;
	String imgsrc;
	int ap = 0;
	int hp = 0;
	String name;
	Boolean used = false;
	Image img;
	Image background;
	
	public Card(int id, ActionListener aL, String imgsrc, String name, int hp, int ap){
		this.id  = id;
		this.imgsrc = imgsrc;
		this.name = name;
		this.hp = hp;
		this.ap = ap;
		if(imgsrc.equals("")){
			imgsrc = "imgresources/default.bmp";
		}
		addActionListener(aL);
		setSize(100, 140);
		setPreferredSize(new Dimension(100,140));
		try {
			img = ImageIO.read(getClass().getResource(imgsrc));
			background = ImageIO.read(getClass().getResourceAsStream("imgresources/background.bmp"));
		} catch (IOException e) {
			System.out.println("Icon Error");
			e.printStackTrace();
		}
		
	}
	
	
	public void setSpecialSelected(Boolean selected){
		if(selected){
			setBorder(selectedBorder);
		}else{
			setBorder(defaultBorder);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(background, 0, 0, null);
		g2.drawImage(img,10,20, null);
		g2.drawString(name,10,17);
		g2.drawString("HP: "+hp+" AP: "+ap, 10, 95);
	}
}
