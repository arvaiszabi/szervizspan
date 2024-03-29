package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DBaseManager;
import model.Gepjarmu;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddCarDBController implements Initializable {

    @FXML private Button BackButton = new Button();

    @FXML private TextField OwnerField = new TextField();
    @FXML private TextField ContactField = new TextField();
    @FXML private TextField PlateField = new TextField();
    @FXML private TextField ManufacturerField = new TextField();
    @FXML private TextField TypeField = new TextField();
    @FXML private TextField FaultField = new TextField();

    private Alert Alarm = new Alert(Alert.AlertType.WARNING);

    @FXML public void AddingCartoDBHandle(ActionEvent actionEvent)
    {
        ArrayList<Gepjarmu> SamePlates = DBaseManager.DBaseSearch(PlateField.getText());
        Alarm.setHeaderText("Figyelmeztetés!");
        if(OwnerField.getText().equals("") |
                ContactField.getText().equals("") |
                PlateField.getText().equals("") |
                ManufacturerField.getText().equals("") |
                TypeField.getText().equals(""))
        {
            Alarm.setContentText("Kérem töltse ki a mezőket!");
            Alarm.show();
        }
        else if(SamePlates.isEmpty() && PlateFormatCheck(PlateField.getText()))
        {
            Gepjarmu jarmu = new Gepjarmu(
                    OwnerField.getText(),
                    ContactField.getText(),
                    ManufacturerField.getText(),
                    TypeField.getText(),
                    PlateField.getText(),
                    FaultField.getText(),
                    false);
            DBaseManager.DBaseCarAdd(jarmu);
            Alarm.setAlertType(Alert.AlertType.INFORMATION);
            Alarm.setHeaderText("Információ");
            Alarm.setContentText("Adatok sikeresen rögzítve!");
            Alarm.show();
            TextClear();
        }
        else if(!SamePlates.isEmpty())
        {
            Alarm.setContentText("Ezzel a rendszámmal már szerepel gépjármű \naz adatbázisban!");
            Alarm.show();
        }
        else
        {
            Alarm.setContentText("A rendszám formátuma nem megfelelő (helyes: ABC-123)!");
            Alarm.show();
        }
    }

    @FXML public void BackButtonHandle(ActionEvent actionEvent) {
        Stage AddCar = (Stage) BackButton.getScene().getWindow();
        AddCar.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OwnerField.setPromptText("A jármű tulajdonosának a neve");
        ContactField.setPromptText("A tulajdonos elérhetősége (pl.:telefon, email)");
        ManufacturerField.setPromptText("A jármű gyártója");
        TypeField.setPromptText("A jármű típusa");
        PlateField.setPromptText("A jármű rendszáma (pl.: ABC-123)");
        FaultField.setPromptText("Hiba leírása...");
        Alarm.setResizable(true);
        Alarm.onShownProperty().addListener(e -> {
            Platform.runLater(() -> Alarm.setResizable(false));
        });
    }

    protected static boolean PlateFormatCheck(String Rendszam)
    {
        if(Rendszam.length() != 7)
            return false;
        for(int i = 0; i < 3; ++i)
        {
            if(!Character.isLetter(Rendszam.charAt(i)))
                return false;
        }
        if(0 != Character.valueOf(Rendszam.charAt(3)).compareTo('-'))
            return false;
        for(int i = 4; i <= 6; ++i)
        {
            if(!Character.isDigit(Rendszam.charAt(i)))
                return false;
        }
        return true;
    }

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
