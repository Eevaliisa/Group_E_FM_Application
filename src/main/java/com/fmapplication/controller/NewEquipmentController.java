package com.fmapplication.controller;

import com.fmapplication.Main;
import com.fmapplication.entity.Equipment;
import com.fmapplication.service.EquipmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NewEquipmentController {
    @FXML
    private TextField eq_title;

    @FXML
    private TextArea eq_desc;

    @FXML
    private DatePicker eq_start;

    @FXML
    private DatePicker eq_end;

    @FXML
    private TextField eq_rate;

    @FXML
    private Button btn_add_new;



    private EquipmentService equipmentService = new EquipmentService();


    public void addNewEquipment(MouseEvent event) {

        if (eq_title.getText().isEmpty() ||
                eq_desc.getText().isEmpty() ||
                eq_start.getValue() == null ||
                eq_rate.getText().isEmpty() ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Fill all fields");
            alert.setTitle("Equipment is not filled");
            alert.show();
        } else {
            Equipment equipment = new Equipment(eq_title.getText(), eq_desc.getText(), eq_start.getValue(), eq_end.getValue(), Double.parseDouble(eq_rate.getText()), Main.getLoggedInUser().getId());
            equipment.setStatus("Waiting for accept");
            equipment.setCreatedBy(Main.getLoggedInUser().getName());
            equipmentService.save(equipment);

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
                Scene scene = new Scene(parent);
                Main.getPrimaryStage().setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void toMainAction(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
        Scene scene = new Scene(parent);
        Main.getPrimaryStage().setScene(scene);
    }






}
