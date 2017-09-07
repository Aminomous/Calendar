package controllers;
/**
 * Thanadon Pakawatthippoyom 5810405037
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Appointment;
import models.AppointmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class InputAppointmentController {

    @FXML
    private TextField dateInput = new TextField();

    @FXML
    private TextField timeInput = new TextField();

    @FXML
    private TextField titleInput = new TextField();

    @FXML
    private TextArea descriptionInput = new TextArea();

    @FXML
    private Button okButton = new Button();

    @FXML
    private Button cancelButton = new Button();

    @FXML
    private DatePicker datePicker = new DatePicker();

    private Appointment appointment;
    private String currentDate;
    private ArrayList<Appointment> appointments;
    private AppointmentService service;
    private String titleName;


    @FXML
    protected void initialize() {
        this.descriptionInput.setScrollLeft(2);
        this.descriptionInput.setScrollTop(2);
        service = new AppointmentService();

    }

    @FXML
    protected void createAppointment() {

        appointment = new Appointment(
                service.getLatestId(),
                getDatePicker().getValue().format(DateTimeFormatter.ofPattern("yyyy/MMMM/dd")),
                timeInput.getText(),
                titleInput.getText(),
                descriptionInput.getText()
        );

        if (titleName == "Add Appointment") {
            service.addAppointment(appointment);

        }else{
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
        HashMap<String, Integer> monthFormatChanger = new HashMap<String, Integer>();
        monthFormatChanger.put("January", 1);
        monthFormatChanger.put("February", 2);
        monthFormatChanger.put("March", 3);
        monthFormatChanger.put("April", 4);
        monthFormatChanger.put("May", 5);
        monthFormatChanger.put("June", 6);
        monthFormatChanger.put("July", 7);
        monthFormatChanger.put("August", 8);
        monthFormatChanger.put("September", 9);
        monthFormatChanger.put("October", 10);
        monthFormatChanger.put("November", 11);
        monthFormatChanger.put("December", 12);
        return monthFormatChanger.get(month);
    }

    public void setAppointment(Appointment app) {
        this.appointment = app;
    }

    public Appointment getAppointment(){
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

    public void setDatePickerDisable(){
        datePicker.setDisable(true);
    }

    public void setTitleName(String name){
        titleName = name;
    }
}
