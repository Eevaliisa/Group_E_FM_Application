package controller;

import dao.JobDAOImpl;
import entity.Job;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.Objects;
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

    @FXML
    void SaveNewJobButtonClicked(ActionEvent event) {

    }

    @FXML
    void SearchJobToDeleteButtonClicked(ActionEvent event) {

    }

    @FXML
    void acceptJobButtonClicked(ActionEvent event) {

    }

    @FXML
    void deleteJobButtonClicked(ActionEvent event) {

    }

    @FXML
    void sendQueryToAdminButtonClicked(ActionEvent event) {

    }

    @FXML
    void updateJobButtonClicked(ActionEvent event) {

    }

    JobDAOImpl jobDAO = new JobDAOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        labelForAddJobChoiceBox.setText("");
        choiceBoxAddJobCategory.getItems().addAll("Electrical", "Mechanical", "Plumbing", "Other");

        labelSearchByToDeleteJob.setText("");
        choiceBoxDeleteJob.getItems().addAll("ID", "Category", "Equipment name", "Status");


        fillTable();

    }

    public void fillTable() {
        List<Job> jobList = jobDAO.getAllPendingJobs();
        ObservableList<Job> jobObservableList = FXCollections.observableArrayList(jobList);




        tableAvailableColumnID.setCellValueFactory(new PropertyValueFactory<>("jobID"));
        tableAvailableColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableAvailableColumnEquipment.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        tableAvailableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("jobLocation"));
        tableAvailableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));





        tableviewAvailableJobs.setItems(jobObservableList);
        tableviewAvailableJobs.refresh();
    }

    @FXML
    void acceptJob(MouseEvent event) {
        if (Objects.isNull(tableviewAvailableJobs.getSelectionModel())
                || Objects.isNull(tableviewAvailableJobs.getSelectionModel().getSelectedItem())) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("ERROR");
            error.setContentText("Please select job for accept");
            error.setTitle("No selected job!");
            error.show();
            return;
        }

        int id = tableviewAvailableJobs.getSelectionModel().getSelectedItem().getJobID();
        Job job = jobDAO.getJobByID(id);
        job.setTechnicianID(Main.getLoggedInTechnician().getTechnicianID());
        job.setJobStatus("in progress");
        job.isAccepted();
        jobDAO.updateJob(job);

        fillTable();
    }

    public void addNewJob(MouseEvent event) {

        if (textAddJobEquipment.getText().isEmpty() ||
                textAddJobLocation.getText().isEmpty() ||
                textAddJobDescription.getText().isEmpty() ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Fill all fields");
            alert.setTitle("Job is not added");
            alert.show();
        } else {
            Job job = new Job(choiceBoxAddJobCategory.getValue().toString(), textAddJobEquipment.getText(), textAddJobLocation.getText(), textAddJobDescription.getText());
            job.setJobStatus("pending");
            jobDAO.addNewJob(job);
            fillTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success!");
            alert.setTitle("New job added.");
            alert.show();
        }
    }
}


