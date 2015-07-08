import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;



public class CardManager {
	
	ArrayList<Card> deck = new ArrayList();
	ActionListener al;
	private Random rnd;
	List list;
	
	public CardManager(ActionListener al){
		this.al = al;
		rnd = new Random();
		
		SAXBuilder builder = new SAXBuilder();
		
		try {
			Document document = builder.build(new File("src/cards.xml"));
			Element root = document.getRootElement();
			list = root.getChildren("Card");
			
			for(int i = 0;i < list.size();i++){
				Element node = (Element) list.get(i);
				
				int id = Integer.parseInt(node.getChildText("id"));
				String name = node.getChildText("name");
				String imgsrc = node.getChildText("img");
				int hp = Integer.parseInt(node.getChildText("hp"));
				int ap = Integer.parseInt(node.getChildText("ap"));
				deck.add(new Card(id,al,imgsrc,name,hp,ap));
			}	
			
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Card rndCardFromDeck(){
		int index = rnd.nextInt(deck.size());
		Card card = deck.get(index);
		System.out.println(card.imgsrc);
		deck.remove(index);
		return card;
	}
	
	public Card createCard(int id){
		String name = "";
		String imgsrc = "";
		int ap = 0;
		int hp = 0;
		
		for(int i = 0;i < list.size();i++){
			Element node = (Element) list.get(i);
			
			if(Integer.parseInt(node.getChildText("id"))==id){
				name = node.getChildText("name");
				imgsrc = node.getChildText("img");
				hp = Integer.parseInt(node.getChildText("hp"));
				ap = Integer.parseInt(node.getChildText("ap"));
				break;
			}
		}	
		return new Card(id,al,imgsrc,name,hp,ap);
	}
}
