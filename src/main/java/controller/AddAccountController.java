package controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DBaseManager;
import model.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAccountController implements Initializable {


    private Alert alarm = new Alert(Alert.AlertType.WARNING);


    @FXML
    private Button AddAccount = new Button();
    @FXML
    private Button BackButton = new Button();
    @FXML
    private Label PassRequestRep = new Label();
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
        PassRequestRep.visibleProperty().bind(ShowPass.selectedProperty().not());
        PassWordTwice.disableProperty().bind(MaskedPassWord.textProperty().isEmpty());
    }

    public void AddingAccountHandle(ActionEvent actionEvent) {
        if(DBaseManager.UserSearch(UserName.getText()).equals("") &&
                DBaseManager.UserSearch("Supervisor").equals(SuperVisor.getText()) &&
                !UserName.getText().equals(""))
        {
            if(!ShowPass.isSelected() && MaskedPassWord.getText().equals(PassWordTwice.getText()) && !MaskedPassWord.getText().equals(""))
                AccountAdd();
            else if (ShowPass.isSelected() && !PassWord.getText().equals(""))
                AccountAdd();
        }
        else
            Fault();
        }

    public void CloseReg(ActionEvent actionEvent) {
        Stage Registration = (Stage) BackButton.getScene().getWindow();
        Registration.close();
    }

    private void TextClear(){
        UserName.clear();
        PassWord.clear();
        PassWordTwice.clear();
        SuperVisor.clear();
        MaskedPassWord.clear();
    }

    private void AccountAdd(){
        Person NewAccount = new Person(UserName.getText(), PassWord.getText());
        DBaseManager.NewAccountAdd(NewAccount);
        alarm.setAlertType(Alert.AlertType.INFORMATION);
        alarm.setHeaderText("Információ");
        alarm.setContentText("Sikeres regisztráció! Most már bejelentkezhet!");
        alarm.show();
        TextClear();
    }

    private void Fault(){
        alarm.setAlertType(Alert.AlertType.ERROR);
        alarm.setHeaderText("Hiba!");
        if(UserName.getText().equals(""))
            alarm.setContentText("Adjon meg egy felhasználónevet!");
        else if((!ShowPass.isSelected() && MaskedPassWord.getText().equals("")) || (ShowPass.isSelected() && PassWord.getText().equals("")))
            alarm.setContentText("Írjon be egy jelszót!");
        else if(!ShowPass.isSelected() && !MaskedPassWord.getText().equals(PassWordTwice.getText()))
            alarm.setContentText("A beírt jelszavak nem egyeznek!");
        else if(!UserName.getText().equals(""))
            alarm.setContentText("Már létezik felhasználó ezzel a névvel!");
        else
            alarm.setContentText("Hibás Supervisor jelszó!");
        alarm.show();
    }
}
