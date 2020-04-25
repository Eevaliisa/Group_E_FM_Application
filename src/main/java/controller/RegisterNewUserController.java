package controller;

import dao.JobDAOImpl;
import dao.TechnicianDAOImpl;
import entity.Technician;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.io.IOException;

public class RegisterNewUserController {

    @FXML
    private AnchorPane anchorPaneRegister;

    @FXML
    private TextField textFieldNewUserID;

    @FXML
    private TextField textFieldNewUserFirstName;

    @FXML
    private TextField textFieldNewUserLastName;

    @FXML
    private TextField textFieldNewUserTrade;

    @FXML
    private TextField textFieldNewUserMobileNo;

    @FXML
    private TextField textFieldNewUserLocation;

    @FXML
    private PasswordField textFieldNewUserPassword;

    @FXML
    private Button buttonRegisterNewUser;

    @FXML
    private Button buttonBackToLogin;

    @FXML
    void backToLoginButtonClicked(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(parent);
        Main.getPrimaryStage().setScene(scene);

    }

    @FXML
    void registerButtonClicked(MouseEvent event) {
        if (isNotCompleted()) {
            showAlertFormNotCompleted();
        } else {
            TechnicianDAOImpl technicianDAO = new TechnicianDAOImpl();
            Technician technician = getFormData();
            if (technicianDAO.isExistingTechnician(technician.getTechnicianID())) {
                showAlertUserAlreadyExists();
            } else {
                technicianDAO.addNewTechnician(technician);
                showAlertAddedUser();
                textFieldNewUserID.clear();
                textFieldNewUserFirstName.clear();
                textFieldNewUserLastName.clear();
                textFieldNewUserLocation.clear();
                textFieldNewUserMobileNo.clear();
                textFieldNewUserTrade.clear();
                textFieldNewUserPassword.clear();
            }
        }

    }



    private Technician getFormData() {
        String id = textFieldNewUserID.getText();
        String password = textFieldNewUserPassword.getText();
        String firstName = textFieldNewUserFirstName.getText();
        String lastName = textFieldNewUserLastName.getText();
        String trade = textFieldNewUserTrade.getText();
        String mobileNumber = textFieldNewUserMobileNo.getText();
        String location = textFieldNewUserLocation.getText();


        return new Technician(id, firstName, lastName, trade, mobileNumber,location,password);
    }

    private boolean isNotCompleted() {
        return "".equals(textFieldNewUserID.getText())
                || ("".equals(textFieldNewUserPassword.getText()))
                || "".equals(textFieldNewUserFirstName.getText())
                || "".equals(textFieldNewUserLastName.getText())
                || "".equals(textFieldNewUserTrade.getText())
                || "".equals(textFieldNewUserMobileNo.getText())
                || "".equals(textFieldNewUserLocation.getText());
    }


    private void showAlertFormNotCompleted() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setTitle("Form is not complete");
        alert.setContentText("Complete the form to add a new user");
        alert.show();
    }

    private void showAlertUserAlreadyExists() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("User already exists.");
        alert.setTitle("Error.");
        alert.setContentText("Try a different login to create a new user.");
        alert.show();
    }

    private void showAlertAddedUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Success!");
        alert.setTitle("New user added.");
        alert.setContentText("Log in to use the app.");
        alert.show();
    }

}
