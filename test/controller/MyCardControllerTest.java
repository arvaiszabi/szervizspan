package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MyCardControllerTest {

    @Start
    public void start(Stage stage) throws Exception  {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MyCard.fxml"));
        Stage cardshow = new Stage();
        cardshow.initStyle(StageStyle.UNDECORATED);
        cardshow.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        cardshow.setTitle("Névjegy");
        cardshow.setScene(scene);
        cardshow.show();
    }
    @Test
    void cardBackButtonHandleShouldCloseMyCardWindow(FxRobot TestRobot) {
        Button BackButton = TestRobot.lookup("#CardBackButton").query();
        assertNotNull(BackButton);
        TestRobot.clickOn(BackButton);
    }
}