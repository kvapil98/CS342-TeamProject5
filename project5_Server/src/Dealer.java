import java.util.ArrayList;
import java.util.Collections;

public class Dealer extends Card{
	ArrayList<Card> Deck = new ArrayList<Card>();
	
	Dealer(){
		createDeck();
		shuffle();
	}
	
	
	public void createDeck(){
		for(SuperHero hero : SuperHero.values()) {
				Card tempCard = new Card(hero);
				tempCard.setValueOfCard(hero);
				Deck.add(tempCard);
		}
	}
	
	public void shuffle() {
		Collections.shuffle(Deck);
	}
	

//	public ArrayList<Card> dealHand(ArrayList<Card> hand){
//		Collections.shuffle(pitchDeck.Deck);
//		Card handCard;
//		for(int i=0; i < 6; i++) {
//			handCard = pitchDeck.Deck.get(0);
//			pitchDeck.Deck.remove(0);
//			hand.add(handCard);
//		}
//		
//		return hand;
//	}
	
	public void newDeck() {
		Deck.clear();
		createDeck();
	}
}