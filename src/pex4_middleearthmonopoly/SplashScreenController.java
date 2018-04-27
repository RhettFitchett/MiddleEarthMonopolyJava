package pex4_middleearthmonopoly;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SplashScreenController {

    private int choiceClicks = 0;
    private Image playerPicture;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button PlayButton;

    @FXML
    private ImageView CharacterChoice;

    @FXML
    private Button switchCharButton;

    @FXML
    void PlayGameButtonHandler(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader root1 = null;
        root1 = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        try {
            root = (Parent) root1.load();
        } catch (IOException ex) {

        }
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        //Scene scene = new Scene(root, screen.getHeight(), screen.getHeight());

        FXMLDocumentController controller = root1.<FXMLDocumentController>getController();
        if (choiceClicks != 0) {
            controller.setImage(playerPicture);
        }
        Scene scene = new Scene(root);

        Stage stageBoard = new Stage();
        stageBoard.setScene(scene);
        stageBoard.setTitle("Middle Earth Monopoly");
        stageBoard.show();
        Stage stageCurrent = (Stage) PlayButton.getScene().getWindow();
        stageCurrent.close();
    }

    @FXML
    void switchCharacter(ActionEvent event) {
        choiceClicks++;
        if (choiceClicks == 3) {
            choiceClicks = 0;
        }
        playerPicture = new Image("file:///C:/Users/Boba%20Rhett/Documents/NetBeansProjects/Pex4_MiddleEarthMonopoly/src/Files/PlayerPic" + (choiceClicks + 1) + ".png");
        //CharacterChoice.
        CharacterChoice.setImage(playerPicture);

    }

    @FXML
    void initialize() {
        assert PlayButton != null : "fx:id=\"PlayButton\" was not injected: check your FXML file 'SplashScreen.fxml'.";
        assert CharacterChoice != null : "fx:id=\"CharacterChoice\" was not injected: check your FXML file 'SplashScreen.fxml'.";
        assert switchCharButton != null : "fx:id=\"switchCharButton\" was not injected: check your FXML file 'SplashScreen.fxml'.";

    }
}
