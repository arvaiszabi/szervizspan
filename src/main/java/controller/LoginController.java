package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DBaseManager;
import model.Person;

public class LoginController implements Initializable {
    
    @FXML private TextField UserNameField = new TextField();
    @FXML private TextField PasswordField = new TextField();
    @FXML private Button LoginButton = new Button();
    Alert alarm = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    protected void AuthorizationHandle(ActionEvent event) throws IOException {
        Person LogForm = new Person(UserNameField.getText(), PasswordField.getText());
        if(DBaseManager.UserSearch(LogForm.getUsername()).equals(LogForm.getPassword()) && !LogForm.getPassword().equals(""))
        {
            alarm.setTitle("Információ!");
            alarm.setHeaderText("Siker!");
            alarm.setContentText("Sikeres bejelentkezés!");
            alarm.showAndWait();
            Stage login = (Stage) LoginButton.getScene().getWindow();
            login.close();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
            Stage mainFrame = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            mainFrame.setTitle("SzervizSPAN   build:20210424");
            mainFrame.setScene(scene);
            mainFrame.show();
        }

        else {
            alarm.setAlertType(Alert.AlertType.ERROR);
            alarm.setTitle("Hiba!");
            alarm.setHeaderText("Hiba a bejelentkezés során!");
            alarm.setContentText("Hibás felhasználónév és/vagy jelszó!");
            alarm.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserNameField.setPromptText("Írja be felhasználónevét...");
        PasswordField.setPromptText("Írja be jelszavát...");
        LoginButton.disableProperty().bind(Bindings.isEmpty(UserNameField.textProperty()));
        alarm.setResizable(true);
        alarm.onShownProperty().addListener(e -> {
            Platform.runLater(() -> alarm.setResizable(false));
        });
    }

    public void AddNewAccount(ActionEvent actionEvent) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddAccount.fxml"));
            Stage accadd = new Stage();
            accadd.initStyle(StageStyle.UNDECORATED);
            accadd.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            accadd.setScene(scene);
            accadd.show();
    }
}
