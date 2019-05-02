
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
	BorderPane pane = new BorderPane();
	
	
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
						for(int i = 0; i<5; i++) {
							int playerNum = conn.numPlayers - 1;
							String msg = "n," + gameDealer.Deck.get(i).name;
							conn.send(msg,playerNum);
						}
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
				
				
			});
		});
	}
}