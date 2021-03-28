package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DBaseManager;
import model.Person;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)

class AddAccountControllerTest {

    private Person TestUser = new Person("Tester", "GetMeIn");

    @Start
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddAccount.fxml"));
        Stage caradd = new Stage();
        caradd.initStyle(StageStyle.UNDECORATED);
        caradd.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        caradd.setScene(scene);
        caradd.show();
    }

    @Test
    @Order(2)
    void addingAccountHandle(FxRobot TestRobot) {
        Button AccAdd = TestRobot.lookup("#AddAccount").query();
        assertNotNull(AccAdd);
        assertTrue(AccAdd.isDisabled());
        TextField UserTest = TestRobot.lookup("#UserName").query();
        assertNotNull(UserTest);
        CheckBox ShowPass = TestRobot.lookup("#ShowPass").query();
        assertNotNull(ShowPass);
        assertFalse(ShowPass.isSelected());
        PasswordField Password = TestRobot.lookup("#MaskedPassWord").query();
        assertNotNull(Password);
        PasswordField PasswordTwice = TestRobot.lookup("#PassWordTwice").query();
        assertNotNull(PasswordTwice);
        assertTrue(PasswordTwice.isDisabled());
        TextField ShowedPassword = TestRobot.lookup("#PassWord").query();
        assertTrue(!ShowedPassword.isVisible());
        TestRobot.clickOn(UserTest).write(TestUser.getUsername());
        TestRobot.clickOn(Password).write(TestUser.getPassword());
        assertFalse(PasswordTwice.isDisabled());
        TestRobot.clickOn(PasswordTwice).write(TestUser.getPassword());
        PasswordField SuperUser = TestRobot.lookup("#SuperVisor").query();
        TestRobot.clickOn(SuperUser).write("notvalid");
        assertFalse(AccAdd.isDisabled());
        TestRobot.clickOn(ShowPass);
        assertTrue(ShowedPassword.isVisible());
        assertTrue(ShowedPassword.getText().equals(Password.getText()));
        assertTrue(!Password.isVisible() && !PasswordTwice.isVisible());
        TestRobot.clickOn(AccAdd);
        assertTrue(DBaseManager.UserSearch(UserTest.getText()).equals(""));
    }

    @Test
    @Order(1)
    void closeReg(FxRobot TestRobot) {
        Button ExitButton = TestRobot.lookup("#BackButton").query();
        assertNotNull(ExitButton);
    }
}