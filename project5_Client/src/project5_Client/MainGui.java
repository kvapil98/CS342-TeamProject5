package project5_Client;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGui extends Application{
	
	private NetworkConnection  conn =createClient();
	int cardSet = 0;
	HBox cards;
	Button  card1, card2, card3, card4, card5, start;
	//Button card1 = new Button("card1");
	BorderPane pane = new BorderPane();
	Text playerNum = new Text();
	Text played1 = new Text();
	Text played2 = new Text();
	boolean setPlayed = true; //bounce between texts
	Text roundWinner = new Text();
	Text challenge = new Text();
	
	private Parent createContent( Stage primaryStage) {
		card1 = new Button("card1");
		card2 = new Button("card2");
		card3 = new Button("card3");
		card4 = new Button("card4");
		card5 = new Button("card5");
		start = new Button("Start Random");
		
		card1.setOnAction(e->{
			card1.setDisable(true);
			String hero = (String) card1.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			try {
				conn.send(msg);
				conn.send("cardNum,0,"+ conn.clientNumber);
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
				conn.send("cardNum,1,"+ conn.clientNumber);
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
				conn.send("cardNum,2,"+ conn.clientNumber);
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
				conn.send("cardNum,3,"+ conn.clientNumber);
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
				conn.send("cardNum,4,"+ conn.clientNumber);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send card");
			}
		});
		
		start.setOnAction(e->{
			try {
				conn.send("random");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send random");
			}
		});
		
		
		cards = new HBox(card1, card2, card3, card4, card5);
		VBox root = new VBox(10, cards);
		root.setPrefSize(600, 600);
		pane.setCenter(root);
		cards.setAlignment(Pos.CENTER);
		VBox temp = new VBox(10, playerNum, challenge, played1, played2, roundWinner,start);
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
						   //String path= getPath();
//						   String path= "images/ironMan.png";
//						   Image img = new Image(path);
//						   ImageView heroImg = new ImageView(img);
//						   heroImg.setFitWidth(200);
//						   heroImg.setFitHeight(150);
//						   card1.setGraphic(heroImg);
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
				
				if(data.toString().startsWith("round")) {
					String[] tokens = data.toString().split(",");
					roundWinner.setText(tokens[1]);
				}
				
				if(data.toString().startsWith("challenge")) {
					String[] tokens = data.toString().split(",");
					challenge.setText(tokens[1]);
				}

				
				if(data.toString().startsWith("p")){
					//System.out.println(data.toString().intern());
					String[] tokens = data.toString().split(",");
				}
				
				
			});
		});
	}
	
	public String getPath() {
		//String hero = name.intern();
//		if(hero == "IronMan") {
//			return "Images/IronMan.jpg";
//		}else {
//			return null;
//		}
		return "Images/IronMan.jpg";
	}
}
