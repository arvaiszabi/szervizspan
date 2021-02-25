package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MyCardController implements Initializable {

    @FXML private Button CardBackButton = new Button();

    @FXML public void CardBackButtonHandle(ActionEvent actionEvent) {
        Stage mycard = (Stage) CardBackButton.getScene().getWindow();
        mycard.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
