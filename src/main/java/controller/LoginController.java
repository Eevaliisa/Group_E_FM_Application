package controller;

import entity.Technician;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.Hibernate;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    Technician technician = new Technician();

    @FXML
    void registerButtonClicked(ActionEvent event) {

        loadRegisterScene();

    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        Session session = Hibernate.getSessionFactory().openSession();

        String username = textUsername.getText();
        String password = textPassword.getText();

        try {
            session.beginTransaction();
            String select = "FROM Technician t WHERE t.firstName = :userName AND t.id= :password";
            Query query = session.createQuery(select);
            query.setParameter("userName", username);
            query.setParameter("password", password);

            technician = (Technician) query.getSingleResult();
            Main.setLoggedInTechnician(technician);

            if (technician != null) {
                //!results.isEmpty()
                //System.out.println(results);
                loadMainScene();
            } else {
                System.out.println("login failed");
            }
        }catch (NoResultException no) {
            infoBox("Please enter correct username and password", "Login failed", null);
        } catch (Exception ex) {
            System.out.println(ex);
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    private void loadMainScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainView.fxml"));
            Parent root = loader.load();
            MainViewController mainViewController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void loadRegisterScene() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/registerNewUser.fxml"));
                Parent root = loader.load();
                RegisterNewUserController registerNewUserController = loader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception ex) {
                System.out.println(ex);
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
