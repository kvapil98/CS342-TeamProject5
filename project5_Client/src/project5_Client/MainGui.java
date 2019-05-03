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
		   //String path3= getPath("images/ironMan.jpg");
//		   Image img3 = new Image("images/ironMan.jpg");
//		   ImageView heroImg3 = new ImageView(img3);
//		   heroImg3.setFitWidth(200);
//		   heroImg3.setFitHeight(130);
//		   card1.setGraphic(heroImg3);
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
					String attack = tokens[2];
					String defense = tokens[3];
					String special = tokens[4];
					String setText = name + "\nAttack: " + attack + "\nDefense: " + defense + "\nSpecial: " + special;
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
			return "images/batman.jpg";
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
			return "images/greenlantern.jpg";
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
}
