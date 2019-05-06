import java.util.ArrayList;

public class Player {
	ArrayList<Card> hand = new ArrayList<Card>();
	Card played;
	boolean playedCard = false;
	int threadNum;
	int points;
	
	Player(int num){
		this.threadNum = num;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
}
