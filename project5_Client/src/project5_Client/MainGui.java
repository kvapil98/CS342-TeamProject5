package project5_Client;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGui extends Application{
	
	private NetworkConnection  conn =createClient();
	int cardSet = 0;
	HBox cards;
	Button  card1, card2, card3, card4, card5;
	//Button card1 = new Button("card1");
	BorderPane pane = new BorderPane();
	Text playerNum = new Text();
	Text played1 = new Text();
	Text played2 = new Text();
	boolean setPlayed = true;
	
	private Parent createContent( Stage primaryStage) {
		card1 = new Button("card1");
		card2 = new Button("card2");
		card3 = new Button("card3");
		card4 = new Button("card4");
		card5 = new Button("card5");
		
		card1.setOnAction(e->{
			card1.setDisable(true);
			String hero = (String) card1.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		card2.setOnAction(e->{
			card2.setDisable(true);
			String hero = (String) card2.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		card3.setOnAction(e->{
			card3.setDisable(true);
			String hero = (String) card3.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		card4.setOnAction(e->{
			card4.setDisable(true);
			String hero = (String) card4.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		card5.setOnAction(e->{
			card5.setDisable(true);
			String hero = (String) card5.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		
		
		
		cards = new HBox(card1, card2, card3, card4, card5);
		VBox root = new VBox(10, cards);
		root.setPrefSize(600, 600);
		pane.setCenter(root);
		cards.setAlignment(Pos.CENTER);
		VBox temp = new VBox(10, playerNum, played1, played2);
		pane.setTop(temp);
		pane.setBottom(cards);
		return root;	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
//		primaryStage.setScene(new Scene(createContent(primaryStage)));
		Scene scene = new Scene(pane,600,600);
		primaryStage.setScene(scene);
		createContent(primaryStage);
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
	
	
	private Client createClient() {
		return new Client(data -> {
			Platform.runLater(()->{
			
				//set the button texts
				if(data.toString().startsWith("n")) {
					String[] tokens = data.toString().split(",");
					String name = tokens[1];
					
					switch(cardSet){
					   case 0:
						   card1.setText(name);
						   card1.setUserData(name);
						   cardSet++;
						   break; 
					   
					   case 1:
						   card2.setText(name);
						   card2.setUserData(name);
						   cardSet++;
						   break;
					    
					   case 2:
						   card3.setText(name);
						   card3.setUserData(name);
						   cardSet++;
						   break; 
						      
					   case 3:
						   card4.setText(name);
						   card4.setUserData(name);
						   cardSet++;
						   break;
						      
					   case 4:
						   card5.setText(name);
						   card5.setUserData(name);
						   cardSet = 0;
						   break;
					   
					   default : 
						   System.out.println("Reached the default");
					      // Statements
					}
				}
				
				if(data.toString().startsWith("id")) {
					String[] tokens = data.toString().split(",");
					conn.clientNumber = Integer.parseInt(tokens[1]);
					playerNum.setText("You are player " + conn.clientNumber);
				}
				
				
				if(data.toString().startsWith("Player")) {
					if(setPlayed) {
						played1.setText(data.toString());
						setPlayed = false;
					}else {
						played2.setText(data.toString());
						setPlayed = true;
					}
				}
					

				
				if(data.toString().startsWith("p")){
					//System.out.println(data.toString().intern());
					String[] tokens = data.toString().split(",");
				}
				
				
			});
		});
	}
}
