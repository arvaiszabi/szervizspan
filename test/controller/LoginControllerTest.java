package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {

    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("SzervizSPAN - Bejelentkezés");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void authorizationHandleShouldSendAlertOnBadLogin(FxRobot TestRobot) {
        TextField TestUser = TestRobot.lookup("#UserNameField").query();
        assertNotNull(TestUser);
        TextField TestPwd = TestRobot.lookup("#PasswordField").query();
        assertNotNull(TestPwd);
        Button TestLogin = TestRobot.lookup("#LoginButton").query();
        assertTrue(TestLogin.isDisabled());
        TestRobot.clickOn(TestUser).write("Testing");
        assertFalse(TestLogin.isDisabled());
        TestRobot.clickOn(TestLogin);
        Node TestDialog = TestRobot.lookup(".dialog-pane").query();
        assertTrue(TestRobot.lookup("Hiba a bejelentkezés során!").query().isVisible());
    }
}