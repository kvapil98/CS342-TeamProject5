import java.util.ArrayList;
import java.util.Random;

public class Game {
	ArrayList<Player> players = new ArrayList<Player>();
	int challenger1 = 0;
	int challenger2 = 0;
	
	
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	
	public int compare(Card play1, Card play2) {
		if(play1.attack > play2.attack) {
			return 1;
		}else {
			return 2;
		}
	}
	
	public void randomize() {
		int numRange = players.size() + 1;
		while(challenger1 == 0) {
			challenger1 = new Random().nextInt(numRange);
		}
		challenger2 = new Random().nextInt(numRange);
		while(challenger1 == challenger2 || challenger2 == 0) {
			challenger2 = new Random().nextInt(numRange);
		}
	}
}
