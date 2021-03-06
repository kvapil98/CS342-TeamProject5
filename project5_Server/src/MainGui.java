
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MainGui extends Application{
	
	private NetworkConnection  conn = createServer();
	private TextArea messages = new TextArea();
	Dealer gameDealer = new Dealer();
	Game game = new Game();
	BorderPane pane = new BorderPane();
	boolean randomHit = false;
	
	
	private Parent createContent(Stage primaryStage) {
		messages.setPrefHeight(200);
		
		
		VBox root = new VBox(10, messages);
		root.setPrefSize(600, 600);
		
		root.setAlignment(Pos.CENTER);
        //root.setPrefSize(600, 600);
        pane.setCenter(root);
		
		return root;	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Scene scene = new Scene(pane,600,600);
        primaryStage.setScene(scene);
        createContent(primaryStage);
		//primaryStage.setScene(new Scene(createContent(primaryStage)));
		primaryStage.show();
		
	}
	
	@Override
	public void init() throws Exception{
		conn.startConn();
	}
	
	@Override
	public void stop() throws Exception{
		conn.closeConn();
	}
	
	
	private Server createServer() {
		return new Server(data-> {
			Platform.runLater(()->{
				
				if(data.toString().startsWith("cards")) {
					try {
						Player player = new Player(conn.numPlayers - 1);
						int playerNum = conn.numPlayers - 1;
						int attack;
						int defense;
						int special;
						for(int i = 0; i<5; i++) {
							attack = gameDealer.Deck.get(i).attack;
							defense = gameDealer.Deck.get(i).defense;
							special = gameDealer.Deck.get(i).special;
							String msg = "n," + gameDealer.Deck.get(i).name + "," + attack + "," + defense + "," + special;
							conn.send(msg,playerNum);
							player.addCard(gameDealer.Deck.get(i));
						}
						for(int i = 0; i<5; i++) {
							gameDealer.Deck.remove(0);
						}
						game.addPlayer(player);
						//gameDealer.shuffle();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//playersInPlay++;
				}
				
				if(data.toString().startsWith("newCards")) {
					String[] tokens = data.toString().split(",");
					int client = Integer.parseInt(tokens[1]) - 1;
					int attack;
					int defense;
					int special;
					for(int i = 0; i<5; i++) {
						attack = gameDealer.Deck.get(i).attack;
						defense = gameDealer.Deck.get(i).defense;
						special = gameDealer.Deck.get(i).special;
						String msg = "n," + gameDealer.Deck.get(i).name + "," + attack + "," + defense + "," + special;
						try {
							conn.send(msg,client);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					for(int i = 0; i<5; i++) {
						gameDealer.Deck.remove(0);
					}
				}
				
				if(data.toString().startsWith("new player")) {
					int playerNum = conn.numPlayers - 1;
					//String playerNumber = Integer.toString(playerNum);
					String msg = "id," + conn.numPlayers;
					try {
						conn.send(msg, playerNum);
					} catch (Exception e) {
						System.out.println("Did not send player number");
					}	
				}
				
				if(data.toString().startsWith("played")) {
					String[] tokens = data.toString().split(",");
					int index = Integer.parseInt(tokens[2]) - 1;
					int attack = game.players.get(index).played.attack;
					int defense = game.players.get(index).played.defense;
					int special = game.players.get(index).played.special;
					String msg = "Player " + tokens[2] + " played " + tokens[1] + "\n," + tokens[1] + "," + attack + "," + defense +"," + special;
					messages.appendText(msg);
					conn.sendAll(msg);
				}
				
				if(data.toString().startsWith("cardNum")) {
					String[] tokens = data.toString().split(",");
					int playerNum = Integer.parseInt(tokens[2]) - 1;
					int cardNum = Integer.parseInt(tokens[1]);
					game.players.get(playerNum).playedCard = true;
					game.players.get(playerNum).played = game.players.get(playerNum).hand.get(cardNum);
					
					//playersPlayed++; //change when randomizer
				}
				
				if(data.toString().startsWith("random")) {
					game.randomize();
					int challenger = game.challenger1;
					int challengee = game.challenger2;
					
					conn.sendAll("challenge, Defender: Player " + challenger + "   Attacker: Player " + challengee);
					randomHit = true;
					conn.sendToOthers(challenger, challengee, "disableCards");
					conn.sendAll("disableRestart");
					//game.challenger1 = 0;
					//game.challenger2 = 0;
				}
				
				if(data.toString().startsWith("New Game")) {
					gameDealer.newDeck();
					gameDealer.shuffle();
					
					for(int i=0; i< game.players.size(); i++) {
						int attack;
						int defense;
						int special;
						game.players.get(i).points = 0;
						
						for(int j = 0; j<5; j++) {
							attack = gameDealer.Deck.get(j).attack;
							defense = gameDealer.Deck.get(j).defense;
							special = gameDealer.Deck.get(j).special;
							String msg = "n," + gameDealer.Deck.get(j).name + "," + attack + "," + defense + "," + special;
							try {
								conn.send(msg,i);
								conn.send("reset", i);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int k = 0; k<5; k++) {
							gameDealer.Deck.remove(0);
						}
					}
				}
				
				if(randomHit) {
					if(game.players.get(game.challenger1-1).playedCard && game.players.get(game.challenger2-1).playedCard) {
						int gamePoints = 0;
						boolean isWinner = false;
						Card challengerCard = game.players.get(game.challenger1 - 1).played;
						Card challengeeCard = game.players.get(game.challenger2 - 1).played;
						game.players.get(game.challenger1-1).playedCard = false;
						game.players.get(game.challenger2-1).playedCard = false;
						int winner = game.compare(challengerCard, challengeeCard);
						if(winner == 1) {
							winner = game.challenger1;
							game.players.get(game.challenger1-1).points++;
							gamePoints = game.players.get(game.challenger1-1).points;
							if(gamePoints == 3) {
								//send to all the winner
								conn.sendAll("winner,Player " + winner + " has won the War!!");
								isWinner = true;
							}
						}else {
							winner = game.challenger2;
							game.players.get(game.challenger2-1).points++;
							gamePoints = game.players.get(game.challenger2-1).points;
							if(gamePoints == 3) {
								//send to all the winner
								conn.sendAll("winner,Player " + winner + " has won the War!!");
								isWinner = true;
							}
						}
						
						if(!isWinner) {
							conn.sendAll("round,Player " + winner + " has won the battle\nPlayer " + winner + " now has " + gamePoints + " battle points" );
							game.challenger1 = 0;
							game.challenger2 = 0;
							randomHit = false;
						}
					}
				}
				
				
			});
		});
	}
}