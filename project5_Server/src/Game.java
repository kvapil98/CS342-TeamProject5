import java.util.ArrayList;
import java.util.Random;

public class Game {
	ArrayList<Player> players = new ArrayList<Player>();
	int challenger1 = 0;
	int challenger2 = 0;
	
	
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	
	public int compare(Card defender, Card attacker) {
		
		if(attacker.attack > defender.defense) {
			return 2;
		}else if(defender.attack > attacker.defense) {
			return 1;
		}else if(attacker.special > defender.special) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public void randomize() {
		int numRange = players.size() + 1;
		challenger1 = new Random().nextInt(numRange);
		while(challenger1 == 0) {
			challenger1 = new Random().nextInt(numRange);
		}
		challenger2 = new Random().nextInt(numRange);
		while(challenger1 == challenger2 || challenger2 == 0) {
			challenger2 = new Random().nextInt(numRange);
		}
	}
	
	public void reset() {
		challenger1 = 0;
		challenger2 = 0;
	}
}
