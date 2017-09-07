package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowDetailController {

    @FXML
    private Button addButton = new Button();

    @FXML
    private Button editButton = new Button();

    @FXML
    private Button backButton = new Button();

    @FXML
    private TextArea showArea = new TextArea();

    @FXML
    private ChoiceBox<Integer> choiceNumber = new ChoiceBox<Integer>();

    private String currentdate;
    private AppointmentService service;
    private ArrayList<Appointment> appointments;


    @FXML
    protected void addItem(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Add Appointment");

            InputAppointmentController controller = loader.getController();
            controller.setCurrentDate(currentdate);
            controller.setDatePickerDisable();

            stage.showAndWait();
            appointments.add(controller.getAppointment());

            showItems();

        } catch (NullPointerException e) {
            appointments.remove(appointments.size()-1);
            System.out.println("CANCEL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void editItem() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene((Parent) loader.load()));
            stage.setTitle("Edit Appointment");

            InputAppointmentController controller = loader.getController();
            controller.setCurrentDate(appointments.get(choiceNumber.getValue()-1).getDate());
            controller.setDatePickerDisable();

            stage.showAndWait();

            appointments.remove(appointments.get(choiceNumber.getValue()-1));
            appointments.add(controller.getAppointment());

            showItems();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fail to proceed.");
            alert.setContentText("Please select some appointment.");
            Optional<ButtonType> result = alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void redirectPanel() {
        this.backButton.getScene().getWindow().hide();
    }

    protected void showItems() {

        String list = String.format("%2s %0$-15s %0$-50s %s", "NO.", "Time", "Title", "Description\n");

        for (int i = 0; i < appointments.size(); i++) {
            list += String.format(String.format("%2d", i + 1) + " " + appointments.get(i).toString() + "\n");
        }
        this.showArea.setText(list);
        initChoiceNumber();
    }

    @FXML
    protected void initialize() {
        this.showArea.setScrollLeft(2.0);
        this.showArea.setScrollTop(2.0);
        this.showArea.setEditable(false);
    }

    private void initChoiceNumber(){
    choiceNumber.getItems().clear();
        for (int i = 0 ; i < appointments.size() ; i++){
            choiceNumber.getItems().add(i+1);
        }
    }

    public void setCurrentDate(String currentdate) {
        this.currentdate = currentdate;
    }

    public void setAppointments(ArrayList<Appointment> app) {
        appointments = app;
    }
}
