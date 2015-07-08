import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OverlayPane extends JPanel {
	
	String text = "Zum Starten Klicken";
	
	public OverlayPane(MouseListener ml) {
        setOpaque(false);
        setBackground(new Color(255, 0, 0, 50));
        addMouseListener(ml);
        setFocusTraversalKeysEnabled(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",0,30));
        g.drawString(text,getSize().width/2-100, getSize().height/2);
    }
    
    public void setReady(){
    	setBackground(new Color(0,255,0,50));
    	text = "Warte auf Spieler";
    	repaint();
    }

}
