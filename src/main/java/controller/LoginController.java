package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import model.Person;

public class LoginController implements Initializable {
    
    @FXML private TextField UserNameField = new TextField();
    @FXML private TextField PasswordField = new TextField();
    @FXML private Button LoginButton = new Button();

    @FXML
    private void AuthorizationHandle(ActionEvent event) throws IOException {
        Person LogForm = new Person(UserNameField.getText(), PasswordField.getText());
        TextClear();
        if(LogForm.getUsername().equals("admin") && LogForm.getPassword().equals("admin"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Információ!");
            alert.setHeaderText("Siker!");
            alert.setContentText("Sikeres bejelentkezés!");
            alert.showAndWait();Stage login = (Stage) LoginButton.getScene().getWindow();
            login.close();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
            Stage mainFrame = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            mainFrame.setTitle("SzervizSPAN   build:20210225");
            mainFrame.setScene(scene);
            mainFrame.show();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba!");
            alert.setHeaderText("Hiba a bejelentkezés során!");
            alert.setContentText("Hibás felhasználónév és/vagy jelszó!");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserNameField.setPromptText("Írja be felhasználónevét...");
        PasswordField.setPromptText("Írja be jelszavát...");
        LoginButton.disableProperty().bind(Bindings.isEmpty(UserNameField.textProperty()));
    }
    private void TextClear()
    {
        UserNameField.clear();
        PasswordField.clear();
    }
}
