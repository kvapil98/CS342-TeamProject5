import java.util.ArrayList;

public class Player {
	ArrayList<Card> hand = new ArrayList<Card>();
	Card played;
	int threadNum;
	
	Player(int num){
		this.threadNum = num;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
}
