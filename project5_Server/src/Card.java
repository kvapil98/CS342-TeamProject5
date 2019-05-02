enum SuperHero{
	Superman,
	Batman,
	Spiderman,
	Hulk,
	Thor
}

enum Rank{
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING,
	ACE
}

public class Card {
	SuperHero hero;
	private Rank rank;
	int attack;
	int defense;
	String name;
	//image will be a function on client side
	
	Card(){};
	
	Card(SuperHero hero){
		this.hero = hero;
	}
	
	public void setRank(Rank rank){
		this.rank = rank;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public void setHero(SuperHero hero) {
		this.hero = hero;
	}
	
//	public Suit getSuit() {
//		return this.suit;
//	}
//	
//	public int getValueOfCard() {
//		return this.cardValue;
//	}
	
	public void setValueOfCard(SuperHero hero) {
		if(hero == SuperHero.Superman) {
			this.attack = 10;
			this.defense = 10;
			this.name = "Superman";
		}
		else if(hero == SuperHero.Batman) {
			this.attack = 1;
			this.defense = 11;
			this.name = "Batman";
		}
		else if(hero == SuperHero.Spiderman) {
			this.attack = 2;
			this.defense = 12;
			this.name = "Spiderman";
		}
		else if(hero == SuperHero.Hulk) {
			this.attack = 3;
			this.defense = 13;
			this.name = "Hulk";
		}
		else if(hero == SuperHero.Thor) {
			this.attack = 4;
			this.defense = 14;
			this.name = "Thor";
		}
	}
	
//	public int getRankValue() {
//		return this.rankValue;
//	}
	
	public String rankLetter() {
		if(rank == Rank.TWO) {
			return "2";
		}
		if(rank == Rank.THREE) {
			return "3";
		}
		if(rank == Rank.FOUR) {
			return "4";
		}
		if(rank == Rank.FIVE) {
			return "5";
		}
		if(rank == Rank.SIX) {
			return "6";
		}
		if(rank == Rank.SEVEN) {
			return "7";
		}
		if(rank == Rank.EIGHT) {
			return "8";
		}
		if(rank == Rank.NINE) {
			return "9";
		}
		if(rank == Rank.TEN) {
			return "10";
		}
		if(rank == Rank.JACK) {
			return "J";
		}
		if(rank == Rank.QUEEN) {
			return "Q";
		}
		if(rank == Rank.KING) {
			return "K";
		}
		if(rank == Rank.ACE) {
			return "A";
		}
		
		return "0";
	}
	
//	public String suitLetter() {
//		if(suit == Suit.CLUBS) {
//			return "C";
//		}
//		if(suit == Suit.DIAMONDS) {
//			return "D";
//		}
//		if(suit == Suit.HEARTS) {
//			return "H";
//		}
//		if(suit == Suit.SPADES) {
//			return "S";
//		}
//		return "A";
//	}
}