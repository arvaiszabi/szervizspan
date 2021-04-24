package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void ExitHandle(ActionEvent actionEvent) {
        System.exit(0);
    }
    @FXML
    public void AddingCartoDBHandle(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddCarDB.fxml"));
        Stage caradd = new Stage();
        caradd.initStyle(StageStyle.UNDECORATED);
        caradd.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        caradd.setScene(scene);
        caradd.show();
    }
    @FXML
    public void MyCardButtonHandle(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MyCard.fxml"));
        Stage cardshow = new Stage();
        cardshow.initStyle(StageStyle.UNDECORATED);
        cardshow.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        cardshow.setTitle("Névjegy");
        cardshow.setScene(scene);
        cardshow.show();
    }
    @FXML
    public void SearchCarDBHandle(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DBSearch.fxml"));
        Stage searchtable = new Stage();
        searchtable.initStyle(StageStyle.UNDECORATED);
        searchtable.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        searchtable.setScene(scene);
        searchtable.show();
    }
}
