package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;
import models.AppointmentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ShowAppointmentController {

    @FXML
    private Button addButton, editButton, backButton, removeButton;

    @FXML
    private TextArea showArea;

    @FXML
    private ChoiceBox<Integer> choiceNumber;

    private String currentdate;
    private AppointmentService service;
    private ArrayList<Appointment> appointments;
    private String showingType;


    @FXML
    protected void addItem(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addAppointmentPanel.fxml"));

        try {
            stage.initOwner(addButton.getScene().getWindow());
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Appointment");

            InputAppointmentController controller = loader.getController();
            controller.setCurrentDate(currentdate);
            if (!currentdate.equals("EMPTY")) {
                controller.setDatePickerDisable();
            }
            stage.showAndWait();
            appointments.add(controller.getAppointment());

            showItems("NOTSHOWDATE");

        } catch (NullPointerException e) {
            appointments.remove(appointments.size() - 1);
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
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Edit Appointment");

            InputAppointmentController controller = loader.getController();
            controller.setCurrentDate(appointments.get(choiceNumber.getValue() - 1).getDate());
            controller.setDatePickerDisable();
            controller.setChoiceBoxDisable();

            stage.showAndWait();

            if (controller.getAppointment() != null) {
                appointments.remove(appointments.get(choiceNumber.getValue() - 1));
                appointments.add(controller.getAppointment());
            }

            showItems(showingType);

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
    protected void removeItem() {
        Appointment app = appointments.get(choiceNumber.getValue() - 1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        if (app.getRepeatOption().equals("None")) {
            alert.setHeaderText("Remove appointments number " + choiceNumber.getValue());
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.removeAppointmentByDate(app.getDate());
                appointments.remove(choiceNumber.getValue() - 1);
            }
        } else {
            alert.setHeaderText("Remove repeated Appointment");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.removeAppointmentByRepeatID(app.getRepeatId());
                for (int i = appointments.size()-1 ; i >=0  ; i--){
                    if (appointments.get(i).getRepeatId() == app.getRepeatId()){
                        appointments.remove(i);
                    }
                }
            }

        }
        showItems(showingType);
    }

    @FXML
    protected void redirectPanel() {
        this.backButton.getScene().getWindow().hide();
    }

    protected void showItems(String option) {
        showingType = option;
        String list = showingType.equals("SHOWDATE") ? String.format("%2s %-20s %0$-15s %0$-50s %s", "NO.", "Date", "Time", "Title", "Description\n") : String.format("%2s %0$-15s %0$-50s %s", "NO.", "Time", "Title", "Description\n");

        for (int i = 0; i < appointments.size(); i++) {
            list += String.format(String.format("%2d %s\n", i + 1, showingType.equals("SHOWDATE") ? appointments.get(i).getDetailWithDate() : appointments.get(i).getDetailWithoutDate()));
        }

        this.showArea.setText(list);
        initChoiceNumber();
    }

    @FXML
    protected void initialize() {
        this.showArea.setScrollLeft(2.0);
        this.showArea.setScrollTop(2.0);
        this.showArea.setEditable(false);
        service = new AppointmentService();
    }

    private void initChoiceNumber() {
        choiceNumber.getItems().clear();
        for (int i = 0; i < appointments.size(); i++) {
            choiceNumber.getItems().add(i + 1);
        }
    }

    public void setCurrentDate(String currentdate) {
        this.currentdate = currentdate;
    }

    public void setAppointments(ArrayList<Appointment> app) {
        appointments = app;
    }
}
