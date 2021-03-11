package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MainControllerTest{

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("SzervizSPAN   build:20210225");
        stage.setScene(scene);
        stage.show();
    }
    @Test
    void exitHandleShouldExitProgramOnClick(FxRobot TestRobot) {
        Node FileButton = TestRobot.lookup("Fájl").queryAs(Node.class);
        assertNotNull(FileButton);
        TestRobot.clickOn(FileButton);
        Node ExitButton = TestRobot.lookup("Kilépés").query();
        assertNotNull(ExitButton);
        //TestRobot.clickOn(ExitButton);
    }
    @Test
    void addingCartoDBHandleShouldOpenAddCarDBWindowsOnClick(FxRobot TestRobot) {
        Node AutoButton = TestRobot.lookup("Jármű").queryAs(Node.class);
        assertNotNull(AutoButton);
        TestRobot.clickOn(AutoButton);
        Node AddButton = TestRobot.lookup("Új jármű hozzáadása...").query();
        assertNotNull(AddButton);
        TestRobot.clickOn(AddButton);
        Node Addlabel = TestRobot.lookup("Jármű hozzáadása").query();
        assertNotNull(Addlabel);
    }

    @Test
    void myCardButtonHandleShouldOpenSignCardOnClick(FxRobot TestRobot) {
        Node HelpButton = TestRobot.lookup("Segítség").queryAs(Node.class);
        assertNotNull(HelpButton);
        TestRobot.clickOn(HelpButton);
        Node CardButton = TestRobot.lookup("Névjegy").query();
        assertNotNull(CardButton);
        TestRobot.clickOn(CardButton);
        Node Emaillabel = TestRobot.lookup("arvai.szabii@gmail.com").query();
        assertNotNull(Emaillabel);
    }

    @Test
    void searchCarDBHandleShouldOpenDBSearchWindowOnClick(FxRobot TestRobot) {
        Node AutoButton = TestRobot.lookup("Jármű").queryAs(Node.class);
        assertNotNull(AutoButton);
        TestRobot.clickOn(AutoButton);
        Node SearchButton = TestRobot.lookup("#SearchCar").query();
        assertNotNull(SearchButton);
        TestRobot.clickOn(SearchButton);
        Node Searchlabel = TestRobot.lookup("Keresés").query();
        assertNotNull(Searchlabel);
    }


}