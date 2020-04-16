package controller;

import dao.JobDAOImpl;
import entity.Job;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.util.ResourceBundle;

public class MainViewController implements Initializable {


    @FXML
    private Tab tabJobsList;

    @FXML
    private Button buttonAcceptJob;

    @FXML
    private TableView<Job> tableviewAvailableJobs;

    @FXML
    private TableColumn<?, ?> tableAvailableColumnID;

    @FXML
    private TableColumn<?, ?> tableAvailableColumnCategory;

    @FXML
    private TableColumn<?, ?> tableAvailableColumnEquipment;

    @FXML
    private TableColumn<?, ?> tableAvailableColumnLocation;

    @FXML
    private TableColumn<?, ?> tableAvailableColumnDescription;

    @FXML
    private Tab tabUpdateJob;

    @FXML
    private TableView<?> tableviewUpdateJob;

    @FXML
    private TableColumn<?, ?> tableUpdateColumnCategory;

    @FXML
    private TableColumn<?, ?> tableUpdateColumnEquipment;

    @FXML
    private TableColumn<?, ?> tableUpdateColumnLocation;

    @FXML
    private TableColumn<?, ?> tableUpdateColumnDescription;

    @FXML
    private TableColumn<?, ?> tableUpdateColumnStatus;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Tab tabAddJob;

    @FXML
    private ChoiceBox choiceBoxAddJobCategory;

    @FXML
    private TextField textAddJobEquipment;

    @FXML
    private TextField textAddJobLocation;

    @FXML
    private TextArea textAddJobDescription;

    @FXML
    private Button buttonAddJob;

    @FXML
    private Label labelForAddJobChoiceBox;

    @FXML
    private Tab tabDeleteJob;

    @FXML
    private ChoiceBox choiceBoxDeleteJob;

    @FXML
    private Label labelSearchByToDeleteJob;

    @FXML
    private ListView<?> listToDeleteJob;

    @FXML
    private TextField textFieldToDeleteJob;

    @FXML
    private Button buttonDeleteJob;

    @FXML
    private Button buttonSearchJobToDelete;

    @FXML
    private Tab tabJobsInProgress;

    @FXML
    private ListView<?> listJobsInProgress;

    @FXML
    private Tab tabRecentJobs;

    @FXML
    private Tab tabContactAdmin;

    @FXML
    private TextField textFieldContactAdminName;

    @FXML
    private TextField textFieldContactAdminID;

    @FXML
    private TextField textFieldContactAdminSubject;

    @FXML
    private TextArea textFieldContactAdminDescription;

    @FXML
    private Button buttonContactAdmin;

    JobDAOImpl jobDAO = new JobDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelForAddJobChoiceBox.setText("");
        choiceBoxAddJobCategory.getItems().addAll("Electrical", "Mechanical", "Plumbing", "Other");

        labelSearchByToDeleteJob.setText("");
        choiceBoxDeleteJob.getItems().addAll("ID", "Category", "Equipment name", "Status");



    }
}


