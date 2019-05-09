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
	Button  card1, card2, card3, card4, card5, start, newGame;
	//Button card1 = new Button("card1");
	BorderPane pane = new BorderPane();
	Text playerNum = new Text();
	Text played1 = new Text();
	Text played2 = new Text();
	boolean setPlayed = true; //bounce between texts
	Text roundWinner = new Text();
	Text challenge = new Text();
	int cardOne, cardTwo, cardThree, cardFour, cardFive;
	Image player1Img, player2Img;
	ImageView player1Played, player2Played;
	int cardsHit = 0;
	
	private Parent createContent( Stage primaryStage) {
		card1 = new Button("card1");
		card2 = new Button("card2");
		card3 = new Button("card3");
		card4 = new Button("card4");
		card5 = new Button("card5");
		start = new Button("Start Random");
		newGame = new Button("New Game");
		newGame.setVisible(false);
		
		//temp images
		player1Img = new Image("images/ironMan.jpg");
		player1Played = new ImageView(player1Img);
		player1Played.setFitWidth(200);
		player1Played.setFitHeight(130);
		player1Played.setVisible(false);
		
		//temp images
		player2Img = new Image("images/ironMan.jpg");
		player2Played = new ImageView(player1Img);
		player2Played.setFitWidth(200);
		player2Played.setFitHeight(130);
		player2Played.setVisible(false);
		
		card1.setOnAction(e->{
			card1.setDisable(true);
			String hero = (String) card1.getUserData();
			String msg = "played," + hero + "," + conn.clientNumber;
			disableCards();
			cardOne++;
			cardsHit++;
			try {
				//conn.send(msg);
				if(cardsHit == 5) {
					cardsHit = 0;
					conn.send("newCards," + conn.clientNumber);
				}
				conn.send("cardNum,0,"+ conn.clientNumber);
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
			disableCards();
			cardTwo++;
			cardsHit++;
			try {
				//conn.send(msg);
				if(cardsHit == 5) {
					cardsHit = 0;
					conn.send("newCards," + conn.clientNumber);
				}
				conn.send("cardNum,1,"+ conn.clientNumber);
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
			disableCards();
			cardThree++;
			cardsHit++;
			try {
				if(cardsHit == 5) {
					cardsHit = 0;
					conn.send("newCards," + conn.clientNumber);
				}
				conn.send("cardNum,2,"+ conn.clientNumber);
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
			disableCards();
			cardFour++;
			cardsHit++;
			try {
				//conn.send(msg);
				if(cardsHit == 5) {
					cardsHit = 0;
					conn.send("newCards," + conn.clientNumber);
				}
				conn.send("cardNum,3,"+ conn.clientNumber);
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
			disableCards();
			cardFive++;
			cardsHit++;
			try {
				//conn.send(msg);
				if(cardsHit == 5) {
					cardsHit = 0;
					conn.send("newCards," + conn.clientNumber);
				}
				conn.send("cardNum,4,"+ conn.clientNumber);
				conn.send(msg);
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
		
		newGame.setOnAction(e->{
			try {
				start.setDisable(false);
				conn.send("New Game");
				//conn.send("newCards," + conn.clientNumber);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Did not send new game");
			}
			
		});
		
		
		cards = new HBox(card1, card2, card3, card4, card5);
		VBox root = new VBox(10, cards);
		root.setPrefSize(600, 600);
		pane.setCenter(root);
		cards.setAlignment(Pos.CENTER);
		VBox temp = new VBox(10, playerNum, challenge, played1, player1Played, played2, player2Played, roundWinner,start, newGame);
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
					String attack = tokens[2];
					String defense = tokens[3];
					String special = tokens[4];
					String setText = name + "\nAttack: " + attack + "\nDefense: " + defense + "\nSpecial: " + special;
//					start.setDisable(false);
//					player1Played.setVisible(false);
//					player2Played.setVisible(false);
//					roundWinner.setVisible(false);
//					newGame.setVisible(false);
					//String setData = name + "," + attack + "," + defense + "," + special;
					//System.out.println(name);
					
					switch(cardSet){
					   case 0:
						   card1.setText(setText);
						   card1.setUserData(name);
						   String path= getPath(name);
						   Image img = new Image(path);
						   ImageView heroImg = new ImageView(img);
						   heroImg.setFitWidth(200);
						   heroImg.setFitHeight(130);
						   card1.setGraphic(heroImg);
						   card1.setDisable(true);
						   cardSet++;
						   break; 
					   
					   case 1:
						   card2.setText(setText);
						   card2.setUserData(name);
						   String path0= getPath(name);
						   Image img0 = new Image(path0);
						   ImageView heroImg0 = new ImageView(img0);
						   heroImg0.setFitWidth(200);
						   heroImg0.setFitHeight(130);
						   card2.setGraphic(heroImg0);
						   card2.setDisable(true);
						   cardSet++;
						   break;
					    
					   case 2:
						   card3.setText(setText);
						   card3.setUserData(name);
						   String path1 = getPath(name);
						   Image img1 = new Image(path1);
						   ImageView heroImg1 = new ImageView(img1);
						   heroImg1.setFitWidth(200);
						   heroImg1.setFitHeight(130);
						   card3.setGraphic(heroImg1);
						   card3.setDisable(true);
						   cardSet++;
						   break; 
						      
					   case 3:
						   card4.setText(setText);
						   card4.setUserData(name);
						   String path2= getPath(name);
						   Image img2 = new Image(path2);
						   ImageView heroImg2 = new ImageView(img2);
						   heroImg2.setFitWidth(200);
						   heroImg2.setFitHeight(130);
						   card4.setGraphic(heroImg2);
						   card4.setDisable(true);
						   cardSet++;
						   break;
						      
					   case 4:
						   card5.setText(setText);
						   card5.setUserData(name);
						   String path3= getPath(name);
						   Image img3 = new Image(path3);
						   ImageView heroImg3 = new ImageView(img3);
						   heroImg3.setFitWidth(200);
						   heroImg3.setFitHeight(130);
						   card5.setGraphic(heroImg3);
						   card5.setDisable(true);
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
					String[] tokens = data.toString().split(",");
					String pathImg = getPath(tokens[1]);
					String msg = tokens[0] + "Attack: " + tokens[2] + " Defense: " + tokens[3] + " Special: " + tokens[4];
					
					if(setPlayed) {
						played1.setText(msg);
						player1Img = new Image(pathImg);
						player1Played.setImage(player1Img);
						player1Played.setVisible(true);
						
						setPlayed = false;
					}else {
						played2.setText(msg);
						
						player2Img = new Image(pathImg);
						player2Played.setImage(player2Img);
						player2Played.setVisible(true);
						setPlayed = true;
					}
				}
				
				if(data.toString().startsWith("round")) {
					String[] tokens = data.toString().split(",");
					roundWinner.setText(tokens[1]);
					roundWinner.setVisible(true);
					start.setDisable(false);
				}
				
				if(data.toString().startsWith("challenge")) {
					String[] tokens = data.toString().split(",");
					challenge.setText(tokens[1]);
					player1Played.setVisible(false);
					player2Played.setVisible(false);
					roundWinner.setVisible(false);
					played1.setText("");
					played2.setText("");
					if(cardOne == 0) {
						card1.setDisable(false);
					}
					if(cardTwo == 0) {
						card2.setDisable(false);
					}
					if(cardThree == 0) {
						card3.setDisable(false);
					}
					if(cardFour == 0) {
						card4.setDisable(false);
					}
					if(cardFive == 0) {
						card5.setDisable(false);
					}
					
					if((cardOne != 0) && (cardTwo != 0) && (cardThree != 0) && (cardFour != 0) && (cardFive != 0)) {
						cardOne = 0;
						cardTwo = 0;
						cardThree = 0;
						cardFour = 0;
						cardFive = 0;
					}
				}

				
				if(data.toString().startsWith("disableRestart")) {
					start.setDisable(true);
				}
				
				if(data.toString().startsWith("disableCards")) {
					card1.setDisable(true);
					card2.setDisable(true);
					card3.setDisable(true);
					card4.setDisable(true);
					card5.setDisable(true);
				}
				
				if(data.toString().startsWith("winner")){
					String[] tokens = data.toString().split(",");
					roundWinner.setText(tokens[1]);
					roundWinner.setVisible(true);
					newGame.setVisible(true);
					start.setDisable(true);
				}
				
				if(data.toString().startsWith("reset")) {
					newGame.setVisible(false);
					start.setDisable(false);
					roundWinner.setVisible(false);
					player1Played.setVisible(false);
					player2Played.setVisible(false);
					challenge.setText("");
					played1.setText("");
					played2.setText("");
					cardOne = 0;
					cardTwo = 0;
					cardThree = 0;
					cardFour = 0;
					cardFive = 0;
				}
				
			});
		});
	}
	
	public String getPath(String name) {
		String hero = name.intern();
		if(hero == "IronMan") {
			return "images/ironMan.jpg";
		}
		else if(hero == "AgentVenom"){
			return "images/agentvenom.jpg";
		}
		else if(hero == "Thor"){
			return "images/thor.jpg";
		}
		else if(hero == "Vision"){
			return "images/vision.jpg";
		}
		else if(hero == "Hulk"){
			return "images/hulk.jpg";
		}
		else if(hero == "Superman"){
			return "images/superman.jpg";
		}
		else if(hero == "BlackAdam"){
			return "images/blackadam.jpg";
		}
		else if(hero == "Brainiac"){
			return "images/brainiac.jpg";
		}
		else if(hero == "Deathstroke"){
			return "images/deathstroke.jpg";
		}
		else if(hero == "DrDoom"){
			return "images/drdoom.jpg";
		}
		else if(hero == "Galactus"){
			return "images/galactus.jpg";
		}
		else if(hero == "LaughingBatman"){
			return "images/laughingbatman.jpg";
		}
		else if(hero == "MarquisOfDeath"){
			return "images/marquisofdeath.jpg";
		}
		else if(hero == "RedHood"){
			return "images/redhood.jpg";
		}
		else if(hero == "RedHulk"){
			return "images/redhulk.jpg";
		}
		else if(hero == "RedSon"){
			return "images/redson.jpg";
		}
		else if(hero == "ReverseFlash"){
			return "images/reverseflash.jpg";
		}
		else if(hero == "SilverSurfer"){
			return "images/silversurfer.jpg";
		}
		else if(hero == "Ultraman"){
			return "images/ultraman.jpg";
		}
		else if(hero == "Batman"){
			return "images/batman.png";
		}
		else if(hero == "Spiderman"){
			return "images/spiderman.jpg";
		}
		else if(hero == "Venom"){
			return "images/venom.jpg";
		}
		else if(hero == "Loki"){
			return "images/loki.jpg";
		}
		else if(hero == "Ultron"){
			return "images/ultron.jpg";
		}
		else if(hero == "WhiteLantern"){
			return "images/whitelantern.jpg";
		}
		else if(hero == "GreenLantern"){
			return "images/greenlatern.jpg";
		}
		else if(hero == "CaptainAmerica"){
			return "images/captainamerica.jpg";
		}
		else if(hero == "LexLuthor"){
			return "images/lexluthor.jpg";
		}
		else if(hero == "Magneto"){
			return "images/magneto.jpg";
		}
		else if(hero == "Joker"){
			return "images/joker.jpg";
		}
		else if(hero == "Flash"){
			return "images/flash.jpg";
		}
		else if(hero == "Wolverine"){
			return "images/wolverine.jpg";
		}
		else if(hero == "BlackPanther"){
			return "images/blackpanther.jpg";
		}
		else if(hero == "Aquaman"){
			return "images/aquaman.jpg";
		}
		else if(hero == "Antman"){
			return "images/antman.jpg";
		}
		else if(hero == "Thanos"){
			return "images/thanos.jpg";
		}
		else if(hero == "Doomsday"){
			return "images/doomsday.png";
		}
		else if(hero == "CaptainMarvel"){
			return "images/captainmarvel.jpg";
		}
		else if(hero == "Deadpool"){
			return "images/deadpool.jpg";
		}
		else if(hero == "ScareCrow"){
			return "images/scarecrow.jpg";
		}
		else if(hero == "Darkseid"){
			return "images/darkseid.png";
		}
		else if(hero == "Bane"){
			return "images/bane.png";
		}
		else if(hero == "Catwoman"){
			return "images/catwoman.jpg";
		}
		else if(hero == "BlackWidow"){
			return "images/blackwidow.jpg";
		}
		else if(hero == "WonderWoman"){
			return "images/wonderwoman.jpg";
		}
		else if(hero == "Carnage"){
			return "images/carnage.jpg";
		}
		else if(hero == "Saitama"){
			return "images/saitama.jpg";
		}
		else if(hero == "Vegeta"){
			return "images/vegeta.png";
		}
		else if(hero == "Goku"){
			return "images/goku.png";
		}
		else if(hero == "Shazam"){
			return "images/shazam.jpg";
		}
		else if(hero == "DrManhattan"){
			return "images/drmanhattan.jpg";
		}
		else if(hero == "Storm"){
			return "images/storm.jpg";
		}
		else if(hero == "ProfessorX"){
			return "images/professorx.jpg";
		}
		else if(hero == "MartianManhunter"){
			return "images/martianmanhunter.jpg";
		}
		else if(hero == "Raven"){
			return "images/raven.jpg";
		}
		else if(hero == "Michealangelo"){
			return "images/michealangelo.jpg";
		}
		else if(hero == "Leonardo"){
			return "images/leonardo.jpg";
		}
		else if(hero == "Raphael"){
			return "images/raphael.png";
		}
		else if(hero == "Donatello"){
			return "images/donatello.png";
		}
		else {
			return null;
		}
	}
	public void disableCards() {
		card1.setDisable(true);
		card2.setDisable(true);
		card3.setDisable(true);
		card4.setDisable(true);
		card5.setDisable(true);
	}
}
