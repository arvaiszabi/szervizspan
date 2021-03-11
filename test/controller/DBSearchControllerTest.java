package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.DBaseManager;
import model.Gepjarmu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.matcher.control.TableViewMatchers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class DBSearchControllerTest {

    private static Gepjarmu testcar = new Gepjarmu("Gipsz Jakab", "+0020000000", "TEST1", "CAR", "AAA-123", "Nem jó!", false);

    @Start
    public void Start (Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DBSearch.fxml"));
        Stage searchtable = new Stage();
        searchtable.initStyle(StageStyle.UNDECORATED);
        searchtable.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        DBaseManager.DBaseCarAdd(testcar);
        //searchtable.setTitle("Keresés az adatbázisban");
        searchtable.setScene(scene);
        searchtable.show();
    }

    @Test
    void backButtonHandleShouldCloseSearchWindow(FxRobot TestRobot) {
        Button BackButton = TestRobot.lookup("#BackButton").query();
        assertNotNull(BackButton);
        TestRobot.clickOn(BackButton);
    }

    @Test
    void dataOpenButtonHandleShouldOpenCarDataSheet(FxRobot TestRobot) {
        TableView TView = TestRobot.lookup("#SearchTableView").query();
        Button TestSheet = TestRobot.lookup("#DataOpen").query();
        assertNotNull(TestSheet);
        Button StartSearch = TestRobot.lookup("#SearchButton").query();
        assertNotNull(StartSearch);
        assertTrue(StartSearch.isDisabled());
        TextField ToSearch = TestRobot.lookup("#SearchField").query();
        assertNotNull(ToSearch);
        TestRobot.clickOn(ToSearch).write("TEST1");
        TestRobot.clickOn(StartSearch);
        Node node = TestRobot.lookup("#ManuCol").nth(getIndexofTest()).query();
        TestRobot.clickOn(node);
        TestRobot.clickOn(TestSheet);
        assertNotNull(TestRobot.lookup("Adatlap").query());
    }

    @Test
    void allfromDBHandleShouldReturnWithAllRecordsFromDBase(FxRobot TestRobot) {
        Button ToListAll = TestRobot.lookup("#AllFromDBButton").query();
        TableView TView = TestRobot.lookup("#SearchTableView").query();
        assertNotNull(ToListAll);
        assertNotNull(TView);
        int DBaseCount = DBaseManager.DBaseSearch("LISTALL").size();
        TestRobot.clickOn(ToListAll);
        assertEquals(DBaseCount, TView.getItems().size());

    }

    @AfterAll
    static void deleteButtonHandleShouldDeleteSelectedObjectFromDBase(FxRobot TestRobot) {
        TableView TView = TestRobot.lookup("#SearchTableView").query();
        Button StartSearch = TestRobot.lookup("#SearchButton").query();
        TextField ToSearch = TestRobot.lookup("#SearchField").query();
        assertNotNull(ToSearch);
        TestRobot.clickOn(ToSearch).write("TEST1");
        TestRobot.clickOn(StartSearch);
        Node node = TestRobot.lookup("#ManuCol").nth(getIndexofTest()).query();
        TestRobot.clickOn(node);
        Button Delete = TestRobot.lookup("#DeleteButton").query();
        assertNotNull(Delete);
        TestRobot.clickOn(Delete);
        ArrayList<Gepjarmu> Check = DBaseManager.DBaseSearch(testcar.getGyarto());
        assertTrue(Check.isEmpty());
    }

    private static int getIndexofTest()
    {
        FxRobot TestRobot = new FxRobot();
        TableView TView = TestRobot.lookup("#SearchTableView").query();
        ArrayList<Gepjarmu> autok = new ArrayList<>(TView.getItems());
        int index = 1;
        for(; index < autok.size(); ++index)
        {
            Gepjarmu auto = autok.get(index);
            if(auto.getGyarto().equals("TEST1"))
                break;
        }
        return index;
    }
}