package main;

import entity.Technician;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;
    private static Technician loggedInTechnician;

    public static Technician getLoggedInTechnician() {
        return loggedInTechnician;
    }

    public static void setLoggedInTechnician(Technician technician) {
        Main.loggedInTechnician = technician;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Computer Aided Facilities Management");
        primaryStage.setScene(new Scene(root, 609, 521));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}



