package controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DataContainer;
import model.DBaseManager;
import model.Gepjarmu;

public class DBSearchController implements Initializable {

    @FXML private Button BackButton = new Button();
    @FXML private Button DataOpen = new Button();
    @FXML private Button DeleteButton = new Button();
    @FXML private Button SearchButton = new Button();
    @FXML private TextField SearchField = new TextField();
    //Kereső lista beállítása
    @FXML private TableView<Gepjarmu> SearchTableView = new TableView<>();
    //Oszlopok beállítása
    @FXML private TableColumn<Gepjarmu, String> OwnerCol;
    @FXML private TableColumn<Gepjarmu, String> ContactCol;
    @FXML private TableColumn<Gepjarmu, String> ManuCol;
    @FXML private TableColumn<Gepjarmu, String> TypeCol;
    @FXML private TableColumn<Gepjarmu, String> PlateCol;
    @FXML private TableColumn<Gepjarmu, String> FaultCol;
    @FXML private TableColumn<Gepjarmu, String> StatCol;

    private Alert Alarm = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SearchButton.disableProperty().bind(Bindings.isEmpty(SearchField.textProperty()));
        SearchField.setPromptText("Mire keres?");
        //Kereső lista oszlopainak beállítása--> oszlopnév --> objektum melyik 'tulajdonsága' van megjelenítve
        OwnerCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Nev"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Kontakt"));
        ManuCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Gyarto"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Tipus"));
        PlateCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Rendszam"));
        FaultCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Hiba"));
        StatCol.setCellValueFactory(new PropertyValueFactory<Gepjarmu, String>("Kesz"));
        SearchTableView.setPlaceholder(new Label("Nincs megjeleníthető eredmény!"));
        Alarm.setHeaderText("Információ");
        DataOpen.disableProperty().bind(Bindings.isEmpty(SearchTableView.getSelectionModel().getSelectedItems()));
        DeleteButton.disableProperty().bind(Bindings.isEmpty(SearchTableView.getSelectionModel().getSelectedItems()));
        Alarm.setResizable(true);
        Alarm.onShownProperty().addListener(e -> {
            Platform.runLater(() -> Alarm.setResizable(false));
        });
    }
    @FXML
    public void BackButtonHandle(ActionEvent actionEvent) {
        Stage searchstage = (Stage) BackButton.getScene().getWindow();
        searchstage.close();
    }
    @FXML
    public void SearchButtonHandle(ActionEvent actionEvent) {
        ArrayList<Gepjarmu> Result = DBaseManager.DBaseSearch(SearchField.getText());
        //SearchField.clear();
        if(Result.isEmpty()) //Ha üres a visszaadott ArrayList --> nincs az adatbázisban a keresendő kifejezés
        {
            Alarm.setContentText("Nincs megjeleníthető eredmény!");
            Alarm.show();
        }

            ObservableList<Gepjarmu> Eredmeny = FXCollections.observableArrayList(Result);
            SearchTableView.setItems(Eredmeny);

    }
    @FXML
    public void DataOpenButtonHandle(ActionEvent actionEvent) throws IOException {
        Gepjarmu selected = SearchTableView.getSelectionModel().getSelectedItem();
        DataContainer pack = DataContainer.getINSTANCE();
        pack.setGepjarmu(selected);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DataSheet.fxml"));
        Stage datasheet = new Stage();
        datasheet.initStyle(StageStyle.UNDECORATED);
        datasheet.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        datasheet.setScene(scene);
        datasheet.show();
    }
    @FXML
    public void AllfromDBHandle(ActionEvent actionEvent) {
        SearchTableView.getItems().clear();
        ArrayList<Gepjarmu> Result = DBaseManager.DBaseSearch("LISTALL");
        ObservableList<Gepjarmu> Eredmeny = FXCollections.observableArrayList(Result);
        if(Eredmeny.isEmpty()){
            Alarm.setContentText("Az adatbázis jelenleg üres!");
            Alarm.show();
        }
        else
        SearchTableView.setItems(Eredmeny);
    }
    @FXML
    public void DeleteButtonHandle(ActionEvent actionEvent) {
        Gepjarmu selected = SearchTableView.getSelectionModel().getSelectedItem();
        DBaseManager.DBaseDelete(selected.getRendszam());
        Alarm.setContentText("Adatlap sikeresen törölve!");
        Alarm.show();
        if(!SearchField.getText().equals(""))
            SearchButtonHandle(new ActionEvent());
        else
            AllfromDBHandle(new ActionEvent());
    }
}
