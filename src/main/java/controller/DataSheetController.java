package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBaseManager;
import model.DataContainer;
import model.Gepjarmu;

import java.net.URL;
import java.util.ResourceBundle;

public class DataSheetController implements Initializable {
    @FXML Button BackButton = new Button();
    @FXML Button ModifyButton = new Button();
    @FXML Label PlateLabel = new Label();
    @FXML TextField OwnerField = new TextField();
    @FXML TextField ContactField = new TextField();
    @FXML TextField ManuField = new TextField();
    @FXML TextField TypeField = new TextField();
    @FXML TextField FaultField = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataContainer pack = DataContainer.getINSTANCE();
        OwnerField.setText(pack.getGepjarmu().getNev());
        ContactField.setText(pack.getGepjarmu().getKontakt());
        ManuField.setText(pack.getGepjarmu().getGyarto());
        TypeField.setText(pack.getGepjarmu().getTipus());
        FaultField.setText(pack.getGepjarmu().getHiba());
        PlateLabel.setText(pack.getGepjarmu().getRendszam());
        ModifyButton.setDisable(true);
        OwnerField.setDisable(true);
        ContactField.setDisable(true);
        ManuField.setDisable(true);
        TypeField.setDisable(true);
        FaultField.setDisable(true);
    }
    @FXML
    public void ModifyButtonHandle(ActionEvent actionEvent) {
        Gepjarmu modjarmu = new Gepjarmu(OwnerField.getText(),
                                         ContactField.getText(),
                                         ManuField.getText(),
                                         TypeField.getText(),
                                         PlateLabel.getText(),
                                         FaultField.getText());
        DBaseManager.CarDBUpdate(modjarmu);
        Alert Alarm = new Alert(Alert.AlertType.INFORMATION);
        Alarm.setHeaderText("Módosítás");
        Alarm.setContentText("Adatlap sikeresen módosítva!");
        Alarm.show();
    }
    @FXML
    public void BackButtonHandle(ActionEvent actionEvent) {
        Stage sheet = (Stage) BackButton.getScene().getWindow();
        sheet.close();
    }
    @FXML
    public void UnlockButtonHandle(ActionEvent actionEvent) {
        ModifyButton.setDisable(false);
        OwnerField.setDisable(false);
        ContactField.setDisable(false);
        ManuField.setDisable(false);
        TypeField.setDisable(false);
        FaultField.setDisable(false);
    }
}