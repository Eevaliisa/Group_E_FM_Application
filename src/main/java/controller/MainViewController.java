package controller;

import dao.JobDAOImpl;
import entity.Job;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.net.URL;
import java.util.ArrayList;
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
    private TableView<Job> tableviewUpdateJob;

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
    private TableView<Job> tableviewDeleteJob;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnID;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnCategory;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnEquipment;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnLocation;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnDescription;

    @FXML
    private TableColumn<?, ?> tableDeleteColumnStatus;

    @FXML
    private TextField textFieldToDeleteJob;

    @FXML
    private Button buttonDeleteJob;

    @FXML
    private Button buttonSearchJobToDelete;

    @FXML
    private Tab tabJobsInProgress;

    @FXML
    private TableView<Job> tableJobsInProgress;

    @FXML
    private TableColumn<?, ?> tableInProgressColumnID;

    @FXML
    private TableColumn<?, ?> tableInProgressColumnCategory;

    @FXML
    private TableColumn<?, ?> tableInProgressColumnEquipment;

    @FXML
    private TableColumn<?, ?> tableInProgressColumnDescription;

    @FXML
    private TableColumn<?, ?> tableInProgressColumnStatus;


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
        fillInProgressTable();

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

    public void fillInProgressTable() {
        List<Job> jobInProgressList = jobDAO.getAllInProgressJobs();
        ObservableList<Job> jobObservableList = FXCollections.observableArrayList(jobInProgressList);

        tableInProgressColumnID.setCellValueFactory(new PropertyValueFactory<>("jobID"));
        tableInProgressColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableInProgressColumnEquipment.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        tableInProgressColumnDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
        tableInProgressColumnStatus.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));

        tableJobsInProgress.setItems(jobObservableList);
        tableJobsInProgress.refresh();
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
        fillInProgressTable();
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
            alert.setHeaderText("New job added");
            alert.setTitle("Success!");
            alert.show();
        }
    }


    private List<Job> jobsListDeletion = new ArrayList<>();

    @FXML
    void fillDeleteTable(MouseEvent event) {
        jobsListDeletion.clear();
        String serachBy = choiceBoxDeleteJob.getValue().toString();
        switch (serachBy) {
            case "ID" :
                jobsListDeletion.add(jobDAO.getJobByID(Integer.parseInt(textFieldToDeleteJob.getText())));
                break;

            case "Category" :
                jobsListDeletion.addAll(jobDAO.getAllJobsByCategory(textFieldToDeleteJob.getText()));
                break;

            case "Equipment name" :
                jobsListDeletion.addAll(jobDAO.getAllJobsByEquipment(textFieldToDeleteJob.getText()));
                break;

            case "Status" :
                jobsListDeletion.addAll(jobDAO.getAllJobsByStatus(textFieldToDeleteJob.getText()));
                break;
        }


        fillDeleteTable();


    }

    void fillDeleteTable() {
        ObservableList<Job> jobObservableList = FXCollections.observableArrayList(jobsListDeletion);


        tableDeleteColumnID.setCellValueFactory(new PropertyValueFactory<>("jobID"));
        tableDeleteColumnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableDeleteColumnEquipment.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        tableDeleteColumnDescription.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
        tableDeleteColumnLocation.setCellValueFactory(new PropertyValueFactory<>("jobLocation"));
        tableDeleteColumnStatus.setCellValueFactory(new PropertyValueFactory<>("jobStatus"));

        tableviewDeleteJob.setItems(jobObservableList);
        tableviewDeleteJob.refresh();

    }

    @FXML
    void deleteJob(MouseEvent event) {

        if (Objects.isNull(tableviewDeleteJob.getSelectionModel())
                || Objects.isNull(tableviewDeleteJob.getSelectionModel().getSelectedItem())) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("ERROR");
            error.setContentText("Please select Item before clicking Delete");
            error.setTitle("No selected item!");
            error.show();
            return;
        }


        Job job = tableviewDeleteJob.getSelectionModel().getSelectedItem();
        jobDAO.deleteJob(job);
        jobsListDeletion.remove(job);
        fillDeleteTable();
        fillInProgressTable();
        fillTable();
    }


}


