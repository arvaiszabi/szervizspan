package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBaseManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCarDBController implements Initializable {

    @FXML private Button BackButton = new Button();
    @FXML private TextField OwnerField = new TextField();
    @FXML private TextField ContactField = new TextField();
    @FXML private TextField PlateField = new TextField();
    @FXML private TextField ManufacturerField = new TextField();
    @FXML private TextField TypeField = new TextField();
    @FXML private TextField FaultField = new TextField();

    @FXML public void AddingCartoDBHandle(ActionEvent actionEvent)
    {
        DBaseManager.DBaseCarAdd(OwnerField.getText(),
                ContactField.getText(),
                PlateField.getText(),
                ManufacturerField.getText(),
                TypeField.getText(),
                FaultField.getText()
        );
        TextClear();
    }

    @FXML public void BackButtonHandle(ActionEvent actionEvent) {
        Stage AddCar = (Stage) BackButton.getScene().getWindow();
        AddCar.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private void TextClear()
    {
        OwnerField.clear();
        ContactField.clear();
        PlateField.clear();
        ManufacturerField.clear();
        TypeField.clear();
        FaultField.clear();
    }
}
