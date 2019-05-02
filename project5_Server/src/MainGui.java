
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
	int playersPlayed = 0; //need to change to implement random challenging
	
	
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
						for(int i = 0; i<5; i++) {
							int playerNum = conn.numPlayers - 1;
							String msg = "n," + gameDealer.Deck.get(i).name;
							conn.send(msg,playerNum);
							player.addCard(gameDealer.Deck.get(i));
						}
						game.addPlayer(player);
						gameDealer.shuffle();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//playersInPlay++;
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
					String msg = "Player " + tokens[2] + " played " + tokens[1] + "\n";
					messages.appendText(msg);
					conn.sendAll(msg);
				}
				
				if(data.toString().startsWith("cardNum")) {
					String[] tokens = data.toString().split(",");
					int playerNum = Integer.parseInt(tokens[2]) - 1;
					int cardNum = Integer.parseInt(tokens[1]);
					game.players.get(playerNum).hand.get(cardNum).played = true;
					game.players.get(playerNum).played = game.players.get(playerNum).hand.get(cardNum);
					playersPlayed++; //change when randomizer
				}
				
				if(data.toString().startsWith("random")) {
					game.randomize();
					int challenger = game.challenger1;
					int challengee = game.challenger2;
					
					conn.sendAll("challenge, player " + challenger + " is playing against " + challengee);
					game.challenger1 = 0;
					game.challenger2 = 0;
				}
				
				
				//temp for testing
				if(playersPlayed == 2) {
					
					int winner = game.compare(game.players.get(0).played, game.players.get(1).played);
					conn.sendAll("round,The winner is player " + winner);
					playersPlayed = 0;
				}
				
				
			});
		});
	}
}