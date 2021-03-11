package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.DBaseManager;
import model.DataContainer;
import model.Gepjarmu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AddCarDBControllerTest {

    private static Gepjarmu testcar = new Gepjarmu("Gipsz Jakab", "+0020000000", "TEST2", "CAR", "ABA-123", "Nem jó!", false);

    @Start
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddCarDB.fxml"));
        Stage caradd = new Stage();
        caradd.initStyle(StageStyle.UNDECORATED);
        caradd.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        //caradd.setTitle("Új jármű hozzáadása...");
        caradd.setScene(scene);
        caradd.show();
    }
        @Order(2)
        @ParameterizedTest
        @ValueSource(strings = {"ABC123", "ABC 123", "a1b-234", "abc-1a3", "ABC", "ABC0123"})
        void plateFormatCheckShouldReturnFalseForStrings (String string){
            assertFalse(AddCarDBController.PlateFormatCheck(string), "Rendszám: " + string);
        }
       @Order(1)
        @Test
        void addingCartoDBHandle (FxRobot TestRobot) {
            assertTrue(DBaseManager.DBaseSearch("TEST2").isEmpty());
            TextField OwnerTest = TestRobot.lookup("#OwnerField").query();
            assertNotNull(OwnerTest);
            TestRobot.clickOn(OwnerTest).write(testcar.getNev());
            TextField ConTest = TestRobot.lookup("#ContactField").query();
            assertNotNull(ConTest);
            TestRobot.clickOn(ConTest).write(testcar.getKontakt());
            TextField PlateTest = TestRobot.lookup("#PlateField").query();
            assertNotNull(PlateTest);
            TestRobot.clickOn(PlateTest).write(testcar.getRendszam());
            TextField Manutest = TestRobot.lookup("#ManufacturerField").query();
            assertNotNull(Manutest);
            TestRobot.clickOn(Manutest).write(testcar.getGyarto());
            TextField TypeTest = TestRobot.lookup("#TypeField").query();
            assertNotNull(TypeTest);
            TestRobot.clickOn(TypeTest).write(testcar.getTipus());
            TextField FaultTest = TestRobot.lookup("#FaultField").query();
            assertNotNull(FaultTest);
            TestRobot.clickOn(FaultTest).write(testcar.getHiba());
            Button TestAdd = TestRobot.lookup("#AddingCarButton").query();
            assertNotNull(TestAdd);
            TestRobot.clickOn(TestAdd);
            assertFalse(DBaseManager.DBaseSearch(testcar.getRendszam()).isEmpty());
            DBaseManager.DBaseDelete(testcar.getRendszam());
        }
        @Order(3)
        @Test
        void backButtonHandle (FxRobot TestRobot) {
        Button ExitButton = TestRobot.lookup("#BackButton").query();
        assertNotNull(ExitButton);
        }

    }