package com.fmapplication.controller;

import com.fmapplication.Main;
import com.fmapplication.service.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField tf_login;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signin;

    /**
     * Get login and password from user.
     * If they match the DB then set isLoggedIn as true and change view to JobsView.
     * Else show alert - wrong login or password.
     */

    @FXML
    void loginActionByMouse(MouseEvent event) throws IOException {

            String login = tf_login.getText();
            String password = pf_password.getText();

            boolean isLoggedIn = loginService.login(login, password);
            if (isLoggedIn) {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                Scene scene = new Scene(parent);
                Main.getPrimaryStage().setScene(scene);
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error");
                error.setContentText("Wrong login or password");
                error.setTitle("You are not logged in");
                error.show();
            }

    }

    @FXML
    void loginAction(KeyEvent event) throws IOException {

        if (event.getCode() == (KeyCode.ENTER)) {
            String login = tf_login.getText();
            String password = pf_password.getText();

            boolean isLoggedIn = loginService.login(login, password);
            if (isLoggedIn) {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                Scene scene = new Scene(parent);
                Main.getPrimaryStage().setScene(scene);
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Error");
                error.setContentText("Wrong login or password");
                error.setTitle("You are not logged in");
                error.show();
            }
        }
    }

    /**
     * Change view to signinView.
     * @param event
     * @throws IOException
     */
    @FXML
    void signinAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/SigninView.fxml"));
        Scene scene = new Scene(parent);
        Main.getPrimaryStage().setScene(scene);
    }

    private LoginService loginService;

    public void initialize() {
        loginService = new LoginService();
    }


}
