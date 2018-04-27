package pex4_middleearthmonopoly;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLDocumentController {

    private Random rand1 = new Random();
    private Random rand2 = new Random();
    private int dice1, dice2;
    private Human player1 = new Human();
    private Computer compter1 = new Computer();
    private ArrayList<Tiles> tileArray = new ArrayList();
    private Integer playerPos = 0;
    protected int choiceClicks;
    protected boolean buy;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RollButton;

    @FXML
    private TextArea RollBox;

    @FXML
    private TextField Player1MoneyField;

    @FXML
    private TextField Player2MoneyField;

    @FXML
    private ImageView diceImage1;

    @FXML
    private ImageView diceImage2;

    @FXML
    private ImageView ChanceDraw;

    @FXML
    private ImageView ChestDraw;

    @FXML
    private ImageView Player1ImageBox;

    @FXML
    private ImageView Player2ImageBox;

    @FXML
    private ImageView Player1ImageLeaderboard;

    @FXML
    private ImageView Player2ImageLeaderboard;

    @FXML
    private Button MusicButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button resetButton;

    public void setImage(Image choice) {
        Player1ImageBox.setImage(choice);
    }

    @FXML
    void rollDice(ActionEvent event) {
        dice1 = rand1.nextInt(6) + 1;
        dice2 = rand2.nextInt(6) + 1;

        RollBox.setText("Dice 1: " + dice1 + "\nDice 2: " + dice2);
        Image dicePic1 = new Image("Files/dice" + dice1 + ".png");
        Image dicePic2 = new Image("Files/dice" + dice2 + ".png");

        diceImage1.setImage(dicePic1);
        diceImage2.setImage(dicePic2);

        // player.move(dice1, dice2)
        movePlayer(dice1 + dice2);
        assessPurchase();

    }

    @FXML
    void PlaySong(ActionEvent event) {
        String bip = "ConcerningHobbits.mp3";
        Media hit = new Media("file:///C:/Users/Boba%20Rhett/Documents/NetBeansProjects/Pex4_MiddleEarthMonopoly/src/Files/ConcerningHobbits.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }

    void movePlayer(int num) {

//        System.out.println("DEBUG, PLAYER X POSITION ::: " + Player1ImageBox.getLayoutX());
//        System.out.println("DEBUG, PLAYER Y POSITION ::: " + Player1ImageBox.getLayoutY());
        int temp = 0;
        int counter = 0;

/// we have the index of the player within the board from the player's x,y coords
        // now we set the player to his new position, DICEROLL + current position
        while (counter < num) { // counter so that once the player ticks over the array list edge it can be cycled through again
            if (playerPos == 35) {
                playerPos = -1;
            }

            System.out.println("Player position is " + playerPos);
            Player1ImageBox.setLayoutX(tileArray.get(playerPos + 1).getPosX());
            Player1ImageBox.setLayoutY(tileArray.get(playerPos + 1).getPosY());
            System.out.println("Passing through " + tileArray.get(playerPos + 1).getName() + "...  " + tileArray.get(playerPos + 1).getDescription());

            System.out.println("Player position is now " + playerPos);
            playerPos++;
            counter++;

        }
        if (playerPos == 27) { // Go To Jail
            playerPos = 9;
            Player1ImageBox.setLayoutX(tileArray.get(playerPos).getPosX());
            Player1ImageBox.setLayoutY(tileArray.get(playerPos).getPosY());
            player1.setInJail(true);
        }
        //now we handle the popup
        popUp(tileArray.get(playerPos));

        //assessPurchase();
    }

    @FXML
    void quitGame(ActionEvent event) {
        Stage stageCurrent = (Stage) quitButton.getScene().getWindow();
        stageCurrent.close();
    }

    @FXML
    void resetGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        //Scene scene = new Scene(root, screen.getHeight(), screen.getHeight());
        Scene scene = new Scene(root);

        Stage stageBoard = new Stage();
        stageBoard.setScene(scene);
        stageBoard.setTitle("Middle Earth Monopoly");
        stageBoard.show();
        Stage stageCurrent = (Stage) resetButton.getScene().getWindow();
        stageCurrent.close();
    }

    void popUp(Tiles tile) {
        Parent root = null;
        FXMLLoader root1 = null;
        root1 = new FXMLLoader(getClass().getResource("PopupFXML.fxml"));
        try {
            root = (Parent) root1.load();
        } catch (IOException ex) {

        }

        PopupFXMLController controller = root1.<PopupFXMLController>getController();

        Scene scene = new Scene(root);

        Stage stageBoard = new Stage();
        stageBoard.setScene(scene);
        stageBoard.setTitle("Middle Earth Monopoly");
        // we change what is visible based on which tile typer we're dealing with:

        //All tiles need to show their name and Description
        controller.nameLabel.setText(tile.getName());
        controller.descLabel.setText(tile.getDescription());

        if (tile instanceof Property) {
            controller.valueLabel.setText(((Property) tile).getBuyValue().toString());
            controller.rentLabel.setText(((Property) tile).getRentValue().toString());

        } else if (tile instanceof Tax) {
            controller.dontBuy.setText("Continue");
            controller.buy.setVisible(false);
            controller.valueLabel.setText(((Tax) tile).getCost().toString());

        } else if (tile instanceof Chance || tile instanceof Chest) { //both of these are implemented similarily
            controller.buy.setVisible(false);
            controller.dontBuy.setVisible(false);
            controller.valueLabel.setVisible(false);
            controller.rentLabel.setVisible(false);
            
        } 
        stageBoard.show();

        //Stage stageCurrent = (Stage) PlayButton.getScene().getWindow();
    }

    @FXML
    void initialize() {
        assert RollButton != null : "fx:id=\"RollButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert RollBox != null : "fx:id=\"RollBox\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player1MoneyField != null : "fx:id=\"Player1MoneyField\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player2MoneyField != null : "fx:id=\"Player2MoneyField\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert ChanceDraw != null : "fx:id=\"ChanceDraw\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert ChestDraw != null : "fx:id=\"ChestDraw\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player2ImageBox != null : "fx:id=\"Player2ImageBox\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player1ImageBox != null : "fx:id=\"Player1ImageBox\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert MusicButton != null : "fx:id=\"MusicButton\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player1ImageLeaderboard != null : "fx:id=\"Player1ImageLeaderboard\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert Player2ImageLeaderboard != null : "fx:id=\"Player2ImageLeaderboard\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert diceImage1 != null : "fx:id=\"diceImage1\" was not injected: check your FXML file 'FXMLDocument.fxml'.";
        assert diceImage2 != null : "fx:id=\"diceImage2\" was not injected: check your FXML file 'FXMLDocument.fxml'.";

        Player1MoneyField.setText("$" + player1.printMoney());
        Player2MoneyField.setText("$" + compter1.printMoney());

        Image playerPicture = new Image("file:///C:/Users/Boba%20Rhett/Documents/NetBeansProjects/Pex4_MiddleEarthMonopoly/src/Files/PlayerPic" + (choiceClicks + 1) + ".png");
        Player1ImageBox.setImage(playerPicture);

        playerPos = 0;

        // We will build the board now, which can be done in a board class but I've done it here instead, the spaced out ones are the corners
        Tiles goTile1 = new Tiles("Go", "Get $200", 782.0, 789.0);
        tileArray.add(goTile1);

        Property property2 = new Property(200, 40, "Barad-Dur", "The Dark Lord Sauron's throne", 669.5, 789.0);
        tileArray.add(property2);
        Chest chest3 = new Chest("Community Chest", "Card Drawn", 594.5, 789.0);
        tileArray.add(chest3);
        Property property4 = new Property(225, 50, "Minas Morgul", "Fortress of The Witch King of Angmar", 519.5, 789.0);
        tileArray.add(property4);
        Tax taxTile5 = new Tax(200, "Gold tax", "Some savings must be used for army maintenance", 444.5, 789.0);
        tileArray.add(taxTile5);
        Property railroad6 = new Property(200, 25, "Moria Minecarts", "These old rails have been abandoned by the dwarves...", 369.5, 789.0);
        tileArray.add(railroad6);
        Property property7 = new Property(250, 30, "Cirith Ungol", "Controlled by goblins, a valuable fort indeed...", 294.5, 789.0);
        tileArray.add(property7);
        Chance chanceCard8 = new Chance("Chance!", "Get a random card!", 219.5, 789.0);
        tileArray.add(chanceCard8);
        Property property9 = new Property(250, 30, "Orthanc", "Saruman's tower of evil plotting...", 144.5, 789.0);
        tileArray.add(property9);

        Tiles jail10 = new Tiles("You shall pass...jail", "To jail or not to jail...", 32.0, 789.0);
        tileArray.add(jail10);

        Property property11 = new Property(275, 40, "Bywater", "Quaint Shire village home of teh Green Dragon Inn", 32.0, 676.5);
        tileArray.add(property11);
        Property electricProp12 = new Property(150, 90, "Electric Company", "They seem to bill us even here...", 32.0, 601.5);
        tileArray.add(electricProp12);
        Property property13 = new Property(275, 40, "Waymeet", "Town in the Westfarthing of the Shire", 32.0, 526.5);
        tileArray.add(property13);
        Property property14 = new Property(350, 75, "Hobbiton", "Center of the Shire, home of Bilbo and Frodo Baggins", 32.0, 451.5);
        tileArray.add(property14);
        Property railroad15 = new Property(200, 30, "Shire Ferry", "Unless you'd rather swim, I suggest you buy this ferry", 32.0, 376.5);
        tileArray.add(railroad15);
        Property property16 = new Property(325, 90, "Michel Delving", "The largest town in the Shire", 32.0, 301.5);
        tileArray.add(property16);
        Chest chest17 = new Chest("Community chest", "Card Drawn", 32.0, 226.5);
        tileArray.add(chest17);
        Property property18 = new Property(300, 60, "Tuckborough", "Home of the Took clan within the Shire", 32.0, 151.5);
        tileArray.add(property18);

        Tiles parking19 = new Tiles("Free horse parking", "A whole lot of nothing happens here", 32.0, 39.0);
        tileArray.add(parking19);

        Property property20 = new Property(175, 30, "Mirkwood", "Home of Thranduil and Legolas...and spiders", 144.5, 39.0);
        tileArray.add(property20);
        Chance chanceCard21 = new Chance("Chance!", "Get a random card!", 219.5, 39.0);
        tileArray.add(chanceCard21);
        Property property22 = new Property(300, 60, "Gondolin", "Hidden elven city somewhere in Beleriand", 294.5, 39.0);
        tileArray.add(property22);
        Property property23 = new Property(350, 80, "Rivendell", "Elrond's elven city", 369.5, 39.0);
        tileArray.add(property23);
        Property railroad24 = new Property(200, 25, "Minecarts of Erebor", "The Misty Mountains' deep tunnels filled with minecart tracks", 444.5, 39.0);
        tileArray.add(railroad24);
        Property property25 = new Property(375, 100, "Valinor", "Forbidden land of The Valar", 519.5, 39.0);
        tileArray.add(property25);
        Property waterWork26 = new Property(150, 50, "Water works", "Power from the Dam above Isengard", 594.5, 39.0);
        tileArray.add(waterWork26);
        Property property27 = new Property(300, 60, "Eregion", "Realm of the Noldorin elves in the second age", 669.5, 39.0);
        tileArray.add(property27);

        Tiles goJail28 = new Tiles("Go to jail!", "You are taken prisoner!", 782.0, 39.0);
        tileArray.add(goJail28);

        Property property29 = new Property(300, 50, "Osgiliath", "Human fortress near Mordor", 782.0, 151.5);
        tileArray.add(property29);
        Chest chest30 = new Chest("Community chest!", "Drawing card sir!", 782.0, 226.5);
        tileArray.add(chest30);
        Property property31 = new Property(300, 70, "Minas-Tirith", "Throne of the King of Gondor", 782.0, 301.5);
        tileArray.add(property31);
        Property railroad32 = new Property(200, 30, "Isengard Trainyard", "A rail system for assisting mass production", 782.0, 376.5);
        tileArray.add(railroad32);
        Chance chanceCard33 = new Chance("Chance!", "Drawing a card!", 782.0, 451.5);
        tileArray.add(chanceCard33);
        Property property34 = new Property(300, 60, "Dale", "A human city at the foot of the Misty Mountains", 782.0, 526.5);
        tileArray.add(property34);
        Tax luxTax35 = new Tax(100, "Luxury Tax", "The One ring calls out to you...", 782.0, 601.5);
        tileArray.add(luxTax35);
        Property property36 = new Property(300, 75, "Edoras of Rohan", "Seat of power for Rohan", 782.0, 676.5);
        tileArray.add(property36);

    }

    void setBuy(boolean x) {
        buy = x;
        System.out.println("Buying Property...");
    }
    void assessPurchase(){
        if (buy == true){
        player1.setMoney(player1.getMoney() - (((Property) tileArray.get(playerPos)).getBuyValue()));
        player1.addProperty((Property) tileArray.get(playerPos));
        System.out.println("Player 1 has purchased " + tileArray.get(playerPos).getName() +" now with $" + player1.getMoney());
        Player1MoneyField.setText(player1.printMoney());
        
        }
    }
}
