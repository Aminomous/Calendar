package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import databaseConnector.DataSource;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Appointment;
import databaseConnector.AppointmentService;
import models.Months;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InputAppointmentController {

    @FXML
    private TextField dateInput, timeInput, titleInput;

    @FXML
    private TextArea descriptionInput;

    @FXML
    private Button okButton, cancelButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox<String> repeatOption;

    private Appointment appointment;
    private String currentDate;
    private ArrayList<Appointment> appointments;
    private DataSource service;
    private String titleName;


    @FXML
    protected void initialize() {
        this.descriptionInput.setScrollLeft(2);
        this.descriptionInput.setScrollTop(2);
        service = new AppointmentService();
        initRepeatOption();

    }

    @FXML
    protected void createAppointment() {

        appointment = new Appointment(
                service.getLatestId(),
                getDatePicker().getValue().format(DateTimeFormatter.ofPattern("yyyy/MMMM/dd")),
                timeInput.getText(),
                titleInput.getText(),
                descriptionInput.getText(),
                repeatOption.getValue(),
                repeatOption.getValue().equals("None")?0+"":String.valueOf(service.getLatestId()+1)
        );

        if (titleName == "Add Appointment") {
            service.addAppointment(appointment);

        } else {
            service.updateAppointment(appointment);
        }
        redirectPanel();

    }

    @FXML
    private void redirectPanel() {
        okButton.getScene().getWindow().hide();
    }

    public void setCurrentDate(String date) {
        if (!date.equals("EMPTY")) {
            currentDate = date;

            getDatePicker().setValue(
                    LocalDate.of(
                            Integer.parseInt(currentDate.substring(0, 4)),
                            monthStrToInt(currentDate.substring(5, currentDate.length() - 3)),
                            Integer.parseInt(currentDate.substring(currentDate.length() - 2))
                    )
            );
            currentDate = "";
        }
    }

    private int monthStrToInt(String month) {
        return Months.monthsKeyString.get(month);
    }

    private void initRepeatOption() {
        repeatOption.getItems().clear();
        repeatOption.getItems().add("None");
        repeatOption.getItems().add("Every day");
        repeatOption.getItems().add("Every week");
        repeatOption.getItems().add("Every month");
        repeatOption.getItems().add("Every year");
        repeatOption.getSelectionModel().selectFirst();
    }

    public void setAppointment(Appointment app) {
        this.appointment = app;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public TextField getTimeInput() {
        return timeInput;
    }

    public void setTimeInput(String timeInput) {
        this.timeInput.setText(timeInput);
    }

    public TextField getTitleInput() {
        return titleInput;
    }

    public void setTitleInput(String titleInput) {
        this.titleInput.setText(titleInput);
    }

    public TextArea getDescriptionInput() {
        return descriptionInput;
    }

    public void setDescriptionInput(String descriptionInput) {
        this.descriptionInput.setText(descriptionInput);
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public void setDatePickerDisable() {
        datePicker.setDisable(true);
    }

    public void setChoiceBoxDisable() {
        repeatOption.setDisable(true);
    }

    public void setTitleName(String name) {
        titleName = name;
    }
}
