package pex4_middleearthmonopoly;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Label nameLabel;

    @FXML
    public Label descLabel;

    @FXML
    public Label valueLabel;

    @FXML
    public Label rentLabel;

    @FXML
    public Button dontBuy;

    @FXML
    public Button buy;

    @FXML
    boolean buyButton(ActionEvent event) throws IOException {
        
        Stage stageCurrent = (Stage) buy.getScene().getWindow();
        FXMLLoader rootBoard = null;
        rootBoard = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = rootBoard.load();
        FXMLDocumentController controller = rootBoard.<FXMLDocumentController>getController();
        controller.setBuy(true);
        stageCurrent.close();
        return true;
        
    }

    @FXML
    void dontBuyButton(ActionEvent event) {
        Stage stageCurrent = (Stage) dontBuy.getScene().getWindow();
        stageCurrent.close();
    }

    @FXML
    void initialize() {
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PopupFXML.fxml'.";
        assert descLabel != null : "fx:id=\"descLabel\" was not injected: check your FXML file 'PopupFXML.fxml'.";
        assert valueLabel != null : "fx:id=\"valueLabel\" was not injected: check your FXML file 'PopupFXML.fxml'.";
        assert rentLabel != null : "fx:id=\"rentLabel\" was not injected: check your FXML file 'PopupFXML.fxml'.";
        assert dontBuy != null : "fx:id=\"dontBuy\" was not injected: check your FXML file 'PopupFXML.fxml'.";
        assert buy != null : "fx:id=\"buy\" was not injected: check your FXML file 'PopupFXML.fxml'.";

    }
}
