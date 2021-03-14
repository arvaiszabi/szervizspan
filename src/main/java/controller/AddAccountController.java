package controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAccountController implements Initializable {


    private Alert alarm = new Alert(Alert.AlertType.WARNING);


    @FXML
    private Button AddAccount = new Button();
    @FXML
    private TextField UserName = new TextField();
    @FXML
    private TextField PassWord = new TextField();
    @FXML
    private PasswordField PassWordTwice = new PasswordField();
    @FXML
    private PasswordField SuperVisor = new PasswordField();
    @FXML
    private PasswordField MaskedPassWord = new PasswordField();
    @FXML
    private CheckBox ShowPass = new CheckBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alarm.setHeaderText("Figyelmeztetés!");
        AddAccount.disableProperty().bind(Bindings.isEmpty(SuperVisor.textProperty()));
        PassWord.setManaged(false);
        PassWord.setVisible(false);
        PassWord.managedProperty().bind(ShowPass.selectedProperty());
        PassWord.visibleProperty().bind(ShowPass.selectedProperty());
        MaskedPassWord.managedProperty().bind(ShowPass.selectedProperty().not());
        MaskedPassWord.visibleProperty().bind(ShowPass.selectedProperty().not());
        PassWord.textProperty().bindBidirectional(MaskedPassWord.textProperty());
        PassWordTwice.visibleProperty().bind(ShowPass.selectedProperty().not());
    }

    public void AddingAccountHandle(ActionEvent actionEvent) {
        String SuperPass = new String();//adatbázisból kell majd kivenni
        ArrayList<Person> IsFree = new ArrayList<>(); // Szabad-e a felhasználó név?
        if(ShowPass.isSelected() && IsFree.isEmpty() && SuperPass.equals(SuperVisor.getText())){
            Person NewAccount = new Person(UserName.getText(), PassWord.getText());
            //továbbítás az adatbázisba még meg kell írni!
            alarm.setAlertType(Alert.AlertType.INFORMATION);
            alarm.setHeaderText("Információ");
            alarm.setContentText("Sikeres regisztráció!");
        }
        else if(!ShowPass.isSelected());

    }
}
