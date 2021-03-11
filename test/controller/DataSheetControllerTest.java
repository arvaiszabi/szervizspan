package controller;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class DataSheetControllerTest {

    private static Gepjarmu testcar = new Gepjarmu("Gipsz Jakab", "+0020000000", "TEST3", "CAR", "AAA-123", "Nem jó!", false);

    @Start
    public void Start (Stage stage) throws IOException {
        DBaseManager.DBaseCarAdd(testcar);
        DataContainer pack = DataContainer.getINSTANCE();
        pack.setGepjarmu(DBaseManager.DBaseSearch("TEST3").get(0));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DataSheet.fxml"));
        Stage datasheet = new Stage();
        datasheet.initStyle(StageStyle.UNDECORATED);
        datasheet.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        datasheet.setScene(scene);
        datasheet.show();
    }

    @Test
    void modifyButtonHandle(FxRobot TestRobot) {
        Button UnlockTest = TestRobot.lookup("#UnlockButton").query();
        assertNotNull(UnlockTest);
        TestRobot.clickOn(UnlockTest);
        Button ModifyTest = TestRobot.lookup("#ModifyButton").query();
        assertNotNull(ModifyTest);
        TextField ManuTest = TestRobot.lookup("#ManuField").query();
        TestRobot.clickOn(ManuTest).write("+TEST");
        TestRobot.clickOn(ModifyTest);
        assertFalse(DBaseManager.DBaseSearch("TEST3+TEST").isEmpty());
        DeleteTestRecord();
    }

    @Test
    void backButtonHandleShouldCloseWindowOnCLick(FxRobot TestRobot) throws Exception {
        Button ExitButton = TestRobot.lookup("#BackButton").query();
        assertNotNull(ExitButton);
        TestRobot.clickOn(ExitButton);
        DeleteTestRecord();
    }

    @Test
    void unlockButtonHandle(FxRobot TestRobot) {
        TextField OwnerTest = TestRobot.lookup("#OwnerField").query();
        assertNotNull(OwnerTest);
        TextField ConTest = TestRobot.lookup("#ContactField").query();
        assertNotNull(ConTest);
        TextField Manutest = TestRobot.lookup("#ManuField").query();
        assertNotNull(Manutest);
        TextField TypeTest = TestRobot.lookup("#TypeField").query();
        assertNotNull(TypeTest);
        TextField FaultTest = TestRobot.lookup("#FaultField").query();
        assertNotNull(FaultTest);
        Button UnlockTest = TestRobot.lookup("#UnlockButton").query();
        assertNotNull(UnlockTest);
        RadioButton InProgTest = TestRobot.lookup("#InProgressRadio").query();
        assertNotNull(InProgTest);
        RadioButton ReadyTest = TestRobot.lookup("#ReadyRadio").query();
        assertNotNull(ReadyTest);
        TestRobot.clickOn(UnlockTest);
        DeleteTestRecord();
    }

    private static void DeleteTestRecord(){
        DBaseManager.DBaseDelete(testcar.getRendszam());
    }
}