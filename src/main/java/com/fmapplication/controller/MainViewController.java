package com.fmapplication.controller;

import com.fmapplication.Main;
import com.fmapplication.entity.Equipment;
import com.fmapplication.service.EquipmentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MainViewController {

    @FXML
    private TableView<Equipment> tbl_equipment;

    @FXML
    private TableColumn<Equipment, Integer> col_number;

    @FXML
    private TableColumn<Equipment, String> col_job_title;

    @FXML
    private TableColumn<Equipment, LocalDate> col_start;

    @FXML
    private TableColumn<Equipment, LocalDate> col_end;

    @FXML
    private TableColumn<Equipment, Double> col_rate;

    @FXML
    private TableColumn<Equipment, String> col_created_by;

    @FXML
    private TableColumn<Equipment, String> col_accepted_by;

    @FXML
    private TableColumn<Equipment, String> col_status;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_export;

    @FXML
    private Button btn_show_stats;

    @FXML
    private Button btn_study;

    @FXML
    private Button btn_add_new;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_view_details;

    @FXML
    private Button btn_accept_job;


    @FXML
    void logoutAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(parent);
        Main.getPrimaryStage().setScene(scene);

        Main.setLoggedInUser(null);
    }

    @FXML
    void addNewAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/NewEquipmentView.fxml"));
        Scene scene = new Scene(parent);
        Main.getPrimaryStage().setScene(scene);
    }

    @FXML
    void deleteAction(MouseEvent event) {

        if (Objects.isNull(tbl_equipment.getSelectionModel())
                || Objects.isNull(tbl_equipment.getSelectionModel().getSelectedItem())) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("ERROR");
            error.setContentText("Please select Item before clicking Delete");
            error.setTitle("No selected item!");
            error.show();
            return;
        }

            int id = tbl_equipment.getSelectionModel().getSelectedItem().getId();
            Equipment equipment = new Equipment(id);
            equipmentService.delete(equipment);

            fillTable();
        }


    @FXML
    void acceptJob(MouseEvent event) {
        if (Objects.isNull(tbl_equipment.getSelectionModel())
                || Objects.isNull(tbl_equipment.getSelectionModel().getSelectedItem())) {

            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("ERROR");
            error.setContentText("Please select job for accept");
            error.setTitle("No selected job!");
            error.show();
            return;
        }

        int id = tbl_equipment.getSelectionModel().getSelectedItem().getId();
        Equipment equipment = new Equipment(id);
        equipment.setAcceptedBy(Main.getLoggedInUser().getName());
        equipment.setStatus("In work");
        equipmentService.updateAfterAccept(equipment);

        fillTable();
    }







    EquipmentService equipmentService = new EquipmentService();



    public void initialize() {
        fillTable();

    }

    public void fillTable() {
        List<Equipment> equipmentList = equipmentService.getAll();
        ObservableList<Equipment> equipmentObservableList = FXCollections.observableArrayList(equipmentList);

        Integer number = 1;
        if (equipmentObservableList.size()>0) {
            for (Equipment equipment : equipmentObservableList) {
                equipment.setNumber(number);
                number++;
            }
        }


        col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_job_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        col_end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        col_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_created_by.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        col_accepted_by.setCellValueFactory(new PropertyValueFactory<>("acceptedBy"));




        tbl_equipment.setItems(equipmentObservableList);
        tbl_equipment.refresh();
    }











}
